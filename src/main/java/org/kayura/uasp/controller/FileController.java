/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.executor.FileUploadProvider;
import org.kayura.uasp.models.UploadItem;
import org.kayura.uasp.po.FileFolder;
import org.kayura.uasp.po.FileShare;
import org.kayura.uasp.service.FileService;
import org.kayura.uasp.vo.FileContentUpdate;
import org.kayura.uasp.vo.FileDownload;
import org.kayura.uasp.vo.FileListItem;
import org.kayura.uasp.vo.FileUpload;
import org.kayura.uasp.vo.FileUploadResult;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.MapUtils;
import org.kayura.utils.StringUtils;
import org.kayura.web.BaseController;
import org.kayura.web.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件与图片的上传下载控制器类.
 * 
 * @author liangxia@live.com
 */
@Controller
public class FileController extends BaseController {

	private static final Log logger = LogFactory.getLog(FileController.class);

	@Autowired
	private FileUploadProvider uploadProvider;

	@Autowired
	private FileService fileService;

	public FileController() {
		this.setViewRootPath("views/file/");
	}

	/**
	 * 文件上传请求地址.
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile[] files, Map<String, Object> map, UploadItem ui) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult r) {

				LoginUser user = getLoginUser();

				// 创建文件上传存储信息.
				FileUpload fu = new FileUpload();
				fu.setAllowChange(ui.getAllowChange());
				fu.setBizId(ui.getBizId());
				fu.setFolderId(ui.getFolderId());
				fu.setCategory(ui.getCategory());
				fu.setTags(ui.getTags());
				fu.setTenantId(user.getTenantId());
				fu.setUploaderId(user.getUserId());
				fu.setUploaderName(user.getDisplayName());
				fu.setIsEncrypt(ui.getIsEncrypt());

				// 循环处理所有上传的文件.
				List<Object> resultList = new ArrayList<Object>();

				for (MultipartFile file : files) {

					if (!file.isEmpty()) {
						try {

							long a1 = System.currentTimeMillis();

							// 设置上传的文件信息.
							fu.setSerial(ui.getSerial());
							fu.setFileName(file.getOriginalFilename());
							fu.setPostfix(FilenameUtils.getExtension(fu.getFileName()));
							fu.setFileSize(file.getSize());
							fu.setContentType(file.getContentType());
							fu.setLogicPath(uploadProvider.getLogicPath());

							// 请求加密文件内容.
							byte[] fileContent = new byte[0];
							if (fu.getIsEncrypt()) {
								fu.setSalt(KeyUtils.random());
								fileContent = aesEncrypt(file.getBytes(), fu.getSalt());
							}

							// 计算文件字节的 MD5 码与存储路径.
							if (!fu.getAllowChange()) {
								String rawString = fu.getContentType() + fu.getFileSize() + fu.getIsEncrypt();
								fu.setMd5(DigestUtils.md5Hex(rawString));
								// fu.setMd5(DigestUtils.md5Hex(fileContent));
							}

							// 保存数据至数据库.
							Result<FileUploadResult> gr = fileService.upload(fu);
							FileUploadResult ur = gr.getData();

							// 如果没有该文件记录,将保存一份新文件至磁盘.
							if (ur.getNewFile()) {

								String absPath = uploadProvider.convertAbsolutePath(fu.getLogicPath());
								File writeFile = new File(absPath, ur.getFileId());

								if (fu.getIsEncrypt()) {
									FileUtils.writeByteArrayToFile(writeFile, fileContent);
								} else {
									file.transferTo(writeFile);
								}
							}

							// 生成上传文件的返回结果项.
							Map<Object, Object> item = new HashMap<Object, Object>();
							item.put("frId", ur.getFrId());
							item.put("fileSize", fu.getFileSize());
							item.put("fileName", fu.getFileName());
							item.put("postfix", fu.getPostfix());
							item.put("spendTime", System.currentTimeMillis() - a1);

							resultList.add(item);

						} catch (Exception e) {
							logger.error("上传文件时发生异常。", e);
							r.setError(e.getMessage(), e);
						}
					}
				}

				r.setData(resultList);
			}
		});

		return this.viewResult("upload");
	}

	/**
	 * 文件下载请求地址.
	 */
	@RequestMapping(value = "/file/get", method = RequestMethod.GET)
	public void getFile(@RequestParam("id") List<String> ids, HttpServletRequest req, HttpServletResponse res) {

		Result<List<FileDownload>> r = fileService.download(ids);
		if (r.isSucceed()) {

			List<FileDownload> fdlst = r.getData();
			if (fdlst.size() == 0) {

				outText(res, "你要下载的文件不存在。");
				return;
			}

			try {

				String tempPath = uploadProvider.getTempPath();

				File downFile = null;
				String fileName = null;

				if (fdlst.size() > 1) {

					String rawString = String.join("|",
							fdlst.stream().map(c -> c.getFrId()).collect(Collectors.toList()));

					downFile = new File(tempPath, DigestUtils.md5Hex(rawString) + ".zip");
					if (!downFile.exists()) {

						OutputStream os = new BufferedOutputStream(new FileOutputStream(downFile));
						ZipOutputStream zout = new ZipOutputStream(os);

						byte[] buf = new byte[1024 * 32];
						int len;

						for (FileDownload fd : fdlst) {

							String absPath = uploadProvider.convertAbsolutePath(fd.getLogicPath());
							File srcFile = new File(absPath, fd.getFileId());

							if (fd.getIsEncrypted()) {

								File aesFile = new File(tempPath, srcFile.getName());
								if (!aesFile.exists()) {
									byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
									fileContent = aesDecrypt(fileContent, fd.getSalt());
								}

								srcFile = aesFile;
							}

							if (srcFile.isFile() && srcFile.exists()) {

								ZipEntry entry = new ZipEntry(fd.getFileName());
								zout.putNextEntry(entry);

								BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
								while ((len = bis.read(buf)) > 0) {
									zout.write(buf, 0, len);
								}
								bis.close();
								zout.closeEntry();
							}
						}

						zout.finish();
						zout.close();
					}

					fileName = "合并下载" + fdlst.size() + "个文件_" + DateUtils.now("yyyyMMddHHmmss") + ".zip";
					res.setContentType("application/octet-stream");

				} else if (fdlst.size() == 1) {

					FileDownload fd = fdlst.get(0);

					String absPath = uploadProvider.convertAbsolutePath(fd.getLogicPath());
					File srcFile = new File(absPath, fd.getFileId());

					if (fd.getIsEncrypted()) {

						File aesFile = new File(tempPath, srcFile.getName());
						if (!aesFile.exists()) {
							byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
							fileContent = aesDecrypt(fileContent, fd.getSalt());
						}
						downFile = aesFile;
					} else {
						downFile = srcFile;
					}

					fileName = fd.getFileName();
					res.setContentType(fd.getContentType());
				}

				// 设置下载文件名.
				fileName = fileName.replace(" ", "_");
				String agent = req.getHeader("USER-AGENT");
				if (agent.indexOf("Trident") != -1) {
					fileName = URLEncoder.encode(fileName, "UTF8");
				} else if (agent.indexOf("Mozilla") != -1) {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}

				res.setHeader("Content-Length", String.valueOf(downFile.length()));
				res.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

				InputStream is = new FileInputStream(downFile);
				OutputStream os = res.getOutputStream();

				byte[] b = new byte[1024 * 32];
				int length;
				while ((length = is.read(b)) > 0) {
					os.write(b, 0, length);
				}

				os.close();
				is.close();
			} catch (Exception e) {
				logger.error(e);
				outText(res, "文件下载时发生异常。" + e);
			}
		} else {
			logger.error(r.getMessage());
			outText(res, "您请求下载的文件记录不存在。");
		}
	}

	@RequestMapping(value = "/file/update", method = RequestMethod.POST)
	public void fileUpdate(MultipartFile file, Map<String, Object> map, String id) {

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult r) {
				if (file != null && !file.isEmpty()) {

					try {

						List<String> ids = new ArrayList<String>();
						ids.add(id);

						Result<List<FileDownload>> rd = fileService.download(ids);
						if (rd.isSucceed()) {

							FileDownload fd = rd.getData().stream().findFirst().get();
							if (fd.getAllowChange()) {

								byte[] fileContent = new byte[0];
								if (fd.getIsEncrypted()) {
									fileContent = aesEncrypt(file.getBytes(), fd.getSalt());
								}

								FileContentUpdate fcu = new FileContentUpdate();
								fcu.setFileId(fd.getFileId());
								fcu.setContentType(file.getContentType());

								String absPath = uploadProvider.convertAbsolutePath(fd.getLogicPath());
								File writeFile = new File(absPath, fd.getFileId());

								if (fd.getIsEncrypted()) {
									FileUtils.writeByteArrayToFile(writeFile, fileContent);
									fcu.setFileSize(Long.valueOf("" + fileContent.length));
								} else {
									file.transferTo(writeFile);
									fcu.setFileSize(file.getSize());
								}

								fileService.updateContent(fcu);
							} else {
								r.setFalied("此文件不允许修改。");
							}
						}

					} catch (Exception e) {
						logger.error("更新文件内容时发生异常。", e);
						r.setError(e.getMessage(), e);
					}
				} else {
					r.setFalied("没有上传文件内容。");
				}
			}
		});
	}

	@RequestMapping(value = "/file/list", method = RequestMethod.GET)
	public String fileList(HttpServletRequest req, HttpServletResponse res) {

		return this.viewResult("list");
	}

	// 文件管理模块.

	@RequestMapping(value = "/file/manager", method = RequestMethod.GET)
	public ModelAndView manager() {

		LoginUser u = this.getLoginUser();

		ModelAndView mv = this.view("manager");
		mv.addObject("hasRoot", u.hasRoot());
		mv.addObject("hasAdmin", u.hasAdmin());

		return mv;
	}

	public static final String FOLDERTYPE_MANAGE = "MANAGE";
	public static final String FOLDERTYPE_SELECT = "SELECT";
	public static final String NODEID_SPLIT = "_";

	/**
	 * 
	 */
	@RequestMapping(value = "/file/folders", method = RequestMethod.POST)
	public void folderTree(Map<String, Object> map, String t, String id) {

		LoginUser user = getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String type;
				if (StringUtils.isEmpty(t)) {
					type = FOLDERTYPE_MANAGE;
				} else {
					type = t.toUpperCase();
				}

				List<TreeNode> rootNode = new ArrayList<TreeNode>();

				if (!StringUtils.isEmpty(id)) {

					if (id.length() == 32) {

						Result<List<FileFolder>> r = fileService.findChildFolders(id);
						if (r.isSucceed()) {

							List<FileFolder> childFolders = r.getData();
							for (FileFolder f : childFolders) {

								TreeNode n = new TreeNode();
								n.setId(f.getFolderId());
								n.setText(f.getName());
								n.setState(TreeNode.STATE_OPEN);
								n.setIconCls("icon-folder");
								rootNode.add(n);
							}
						}
					}
				} else {

					Result<List<FileFolder>> r = fileService.findFolders(user.getUserId(), user.hasRoot());
					if (r.isSucceed()) {

						List<FileFolder> folders = r.getData();

						// 添加 [系统文件夹]
						List<FileFolder> sysFolders = folders.stream().filter(
								c -> c.getHidden() == false && c.getTenantId() == null && c.getParentId() == null)
								.collect(Collectors.toList());

						if (FOLDERTYPE_MANAGE.equals(type) && (user.hasRoot() || !sysFolders.isEmpty())
								|| (FOLDERTYPE_SELECT.equals(type) && user.hasRoot())) {

							TreeNode sysNode = new TreeNode();
							sysNode.setId(FileFolder.SYSFOLDER);
							sysNode.setText("系统文件夹");
							sysNode.setIconCls("icon-book");
							rootNode.add(sysNode);

							for (FileFolder f : sysFolders) {

								TreeNode n = new TreeNode();
								n.setId(f.getFolderId());
								n.setText(f.getName());
								n.setState(TreeNode.STATE_OPEN);
								n.setIconCls("icon-folder");
								sysNode.getChildren().add(n);

								appendChildFolders(n, folders);
							}

							if (user.hasRoot()) {
								TreeNode nc = new TreeNode();
								nc.setId(FileFolder.NOTCLASSIFIED);
								nc.setText("未归类");
								nc.setIconCls("icon-folder");
								sysNode.getChildren().add(nc);
							}
						}

						if (!user.hasRoot()) {

							// 添加 [我的文件夹]
							List<FileFolder> myFolders = folders.stream()
									.filter(c -> c.getTenantId() != null && c.getCreatorId() != null
											&& c.getGroupId() == null && c.getParentId() == null)
									.collect(Collectors.toList());

							TreeNode myNode = new TreeNode();
							myNode.setId(FileFolder.MYFOLDER);
							myNode.setText("我的文件夹");
							myNode.setIconCls("icon-book");
							rootNode.add(myNode);

							for (FileFolder f : myFolders) {

								TreeNode n = new TreeNode();
								n.setId(f.getFolderId());
								n.setText(f.getName());
								n.setIconCls("icon-folder");
								n.setState(TreeNode.STATE_OPEN);
								myNode.getChildren().add(n);

								appendChildFolders(n, folders);
							}

							TreeNode nc = new TreeNode();
							nc.setId(FileFolder.NOTCLASSIFIED);
							nc.setText("未归类");
							nc.setIconCls("icon-folder");
							myNode.getChildren().add(nc);

							// 添加 [我的群组]
							List<String> groups = folders.stream().filter(c -> c.getGroupId() != null).map(m -> {
								return m.getGroupId() + "#" + m.getGroupName();
							}).distinct().collect(Collectors.toList());

							if (!groups.isEmpty()) {

								TreeNode groupNode = new TreeNode();
								groupNode.setId(FileFolder.MYGROUP);
								groupNode.setText("我的群组");
								groupNode.setIconCls("icon-book");
								rootNode.add(groupNode);

								for (String g : groups) {

									String gid = g.split("#")[0];
									String gname = g.split("#")[1];

									TreeNode gn = new TreeNode();
									gn.setId(FileFolder.GROUPITEM + NODEID_SPLIT + gid);
									gn.setText(gname);
									gn.setIconCls("icon-group");
									groupNode.getChildren().add(gn);

									List<FileFolder> myGroups = folders.stream().filter(c -> c.getGroupId() != null
											&& c.getGroupId().equals(gid) && c.getParentId() == null)
											.collect(Collectors.toList());

									for (FileFolder f : myGroups) {

										TreeNode n = new TreeNode();
										n.setId(f.getFolderId());
										n.setText(f.getName());
										n.setState(TreeNode.STATE_OPEN);
										n.setIconCls("icon-folder");
										gn.getChildren().add(n);

										appendChildFolders(n, folders);
									}
								}
							}

							// 添加 [同事的分享]
							List<FileShare> shares = fileService
									.findFileShares(user.getUserId(), FileShare.SHARETYPES_FOLDER).getData();
							if (FOLDERTYPE_MANAGE.equals(type) && !shares.isEmpty()) {

								List<String> sharers = shares.stream().map(m -> {
									return m.getSharerId() + "#" + m.getSharerName();
								}).distinct().collect(Collectors.toList());

								TreeNode shareNode = new TreeNode();
								shareNode.setId(FileFolder.MYSHARE);
								shareNode.setText("同事的分享");
								shareNode.setIconCls("icon-book");
								rootNode.add(shareNode);

								for (String s : sharers) {

									String sharerId = s.split("#")[0];
									String sharerName = s.split("#")[1];

									TreeNode gn = new TreeNode();
									gn.setId(FileFolder.SHARER + NODEID_SPLIT + sharerId);
									gn.setText(sharerName);
									gn.setIconCls("icon-user");
									shareNode.getChildren().add(gn);

									List<FileShare> sharelist = shares.stream()
											.filter(c -> c.getSharerId().equals(sharerId)).collect(Collectors.toList());
									for (FileShare f : sharelist) {

										TreeNode n = new TreeNode();
										n.setId(f.getFolderId());
										n.setText(f.getFolderName());
										n.setState(TreeNode.STATE_OPEN);
										n.setIconCls("icon-folder");
										gn.getChildren().add(n);
									}
								}
							}
						}
					}
				}

				// 添加以返回结果.
				ps.setData(rootNode);
			}
		});

	}

	void appendChildFolders(TreeNode node, List<FileFolder> folders) {

		List<FileFolder> childs = folders.stream()
				.filter(c -> c.getParentId() != null && c.getParentId().equals(node.getId()))
				.collect(Collectors.toList());
		if (!childs.isEmpty()) {

			for (FileFolder f : childs) {

				TreeNode n = new TreeNode();
				n.setId(f.getFolderId());
				n.setText(f.getName());
				n.setState(TreeNode.STATE_OPEN);
				n.setIconCls("icon-folder");
				node.getChildren().add(n);

				appendChildFolders(n, folders);
			}
		}
	}

	@RequestMapping(value = "/file/folder/new", method = RequestMethod.GET)
	public ModelAndView createFolder(String pid, String pname) {

		ModelAndView mv = this.view("folderedit");

		FileFolder model = new FileFolder();

		if (StringUtils.isEmpty(pid)) {
			model.setParentId(null);
		} else if (FileFolder.SYSFOLDER.equals(pid)) {
			model.setParentId(null);
		} else if (FileFolder.MYFOLDER.equals(pid)) {
			model.setParentId(null);
		} else if (pid.startsWith(FileFolder.GROUPITEM)) {
			String[] vs = pid.split(NODEID_SPLIT);
			model.setParentId(null);
			model.setGroupId(vs[1]);
		} else if (pid.length() == 32) {
			model.setParentId(pid);
		}

		model.setHidden(false);
		model.setParentName(pname);

		mv.addObject("model", model);
		return mv;
	}

	@RequestMapping(value = "/file/folder/edit", method = RequestMethod.GET)
	public ModelAndView updateFolder(String id) {

		ModelAndView mv = null;

		Result<FileFolder> r = fileService.getFolderById(id);
		if (r.isSucceed()) {
			mv = this.view("folderedit");
			mv.addObject("model", r.getData());
		} else {
			mv = this.errorPage(r.getMessage(), "");
		}

		return mv;
	}

	@RequestMapping(value = "/file/folder/remove", method = RequestMethod.POST)
	public void removeFolder(Map<String, Object> map, @RequestParam("id") String folderId) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = fileService.removeFolder(folderId);
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/file/folder/move", method = RequestMethod.POST)
	public void moveFolder(Map<String, Object> map, @RequestParam("id") List<String> ids, String folderId) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = fileService.moveToFolder(ids, folderId, user.getUserId());
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/file/folder/copy", method = RequestMethod.POST)
	public void copyFolder(Map<String, Object> map, @RequestParam("id") List<String> ids, String folderId) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = fileService.copyToFolder(ids, folderId, user.getUserId());
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/file/folder/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveFolder(Map<String, Object> map, FileFolder model) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				if (StringUtils.isEmpty(model.getFolderId())) {

					model.setTenantId(user.getTenantId());
					model.setCreatorId(user.getUserId());
				}

				GeneralResult r = fileService.saveFolder(model);
				if (r.isSucceed()) {
					ps.setCode(Result.SUCCEED);
					ps.setData(MapUtils.make("id", r.get("folderId")));
				} else {
					ps.setResult(r);
				}
			}
		});

		return json(map);
	}

	@RequestMapping(value = "/file/folder/select", method = RequestMethod.GET)
	public ModelAndView selectFolder(String sid) {

		ModelAndView mv = this.view("folderselect");
		mv.addObject("sid", sid);
		return mv;
	}

	@RequestMapping(value = "/file/remove", method = RequestMethod.POST)
	public void removeFiles(Map<String, Object> map, @RequestParam("id") List<String> ids) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = fileService.removeFiles(ids, user.getUserId(), false);
				ps.setResult(r);
			}
		});
	}

	@RequestMapping(value = "/file/find", method = RequestMethod.POST)
	public void findFiles(HttpServletRequest req, Map<String, Object> map, String folderId) {

		// folderId 值有几种类型：
		// 0F2D7BE1E02011E59888D8CB8A43F8DD 文件夹ID
		// SHARER#18C5BD7D457647ECB3F688D4ECF1C0C8 共享人ID
		// null 未定义
		// NOTCLASSIFIED 我的文件夹->未分类

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);

				if (!StringUtils.isEmpty(folderId) && (folderId.equals(FileFolder.NOTCLASSIFIED)
						|| folderId.startsWith("SHARER" + NODEID_SPLIT) || folderId.length() == 32)) {

					String id = null;
					String uploaderId = null;
					Result<PageList<FileListItem>> r = null;

					if (folderId.startsWith("SHARER" + NODEID_SPLIT)) {

						String sharerId = folderId.split(NODEID_SPLIT)[1];
						r = fileService.findFilesByShare(sharerId, user.getUserId(), pp);
					} else {

						if (folderId.equals("NOTCLASSIFIED")) {
							id = "NULL";
							uploaderId = user.getUserId();
						} else {
							id = folderId;
						}

						r = fileService.findFilesByFolder(id, uploaderId, pp);
					}

					if (r.isSucceed()) {

						PageList<FileListItem> items = r.getData();
						for (FileListItem i : items.getRows()) {
							i.setIsUploader(user.getUserId().equals(i.getUploaderId()));
						}
						ps.setData(ui.genPageData(items));
					} else {
						ps.addMessage(r.getMessage());
					}

				} else {

					PageList<FileListItem> items = new PageList<FileListItem>(pp);
					ps.setCode(Result.SUCCEED);
					ps.setData(ui.genPageData(items));
				}
			}

		});

	}

	// 私有方法集.

	void outText(HttpServletResponse res, String text) {

		res.setContentType("text/html;charset=UTF-8");
		res.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			OutputStream os = res.getOutputStream();
			os.write(text.getBytes("UTF-8"));
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 对字节进行 AES 加密.
	 * 
	 * @param rawBytes 原始字节内容.
	 * @param encryptKey 私有密钥.
	 * @return 返回加密后的字节.
	 * @throws Exception
	 */
	byte[] aesEncrypt(byte[] rawBytes, String encryptKey) throws Exception {

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(encryptKey.getBytes("UTF8")));

		Cipher cipher = Cipher.getInstance("AES");
		Key key = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] encValue = cipher.doFinal(rawBytes);
		return encValue;
	}

	/**
	 * 对节进行 AES 解密.
	 * 
	 * @param encBytes 加密后的字节内容.
	 * @param decryptKey 私有密钥.
	 * @return 返回解密后的字节.
	 * @throws Exception
	 */
	byte[] aesDecrypt(byte[] encBytes, String decryptKey) throws Exception {

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(decryptKey.getBytes("UTF8")));

		Cipher cipher = Cipher.getInstance("AES");
		Key key = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] rawValue = cipher.doFinal(encBytes);
		return rawValue;
	}

}

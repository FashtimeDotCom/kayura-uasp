/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.utils.KeyUtils;
import org.kayura.type.Result;
import org.kayura.uasp.models.UploadItem;
import org.kayura.uasp.service.FileService;
import org.kayura.uasp.vo.FileDownload;
import org.kayura.uasp.vo.FileListItem;
import org.kayura.uasp.vo.FileUpload;
import org.kayura.uasp.vo.FileUploadResult;
import org.kayura.web.BaseController;
import org.kayura.uasp.executor.FileUploadProvider;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		this.setViewRootPath("views/");
	}

	@RequestMapping(value = "/file/upload", method = RequestMethod.GET)
	public String fileUpload() {

		return this.viewResult("file/upload");
	}

	/**
	 * 文件上传请求地址.
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile[] files, Map<String, Object> map, UploadItem ui) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult r) {

				// 创建文件上传存储信息.
				FileUpload fu = new FileUpload();
				fu.setAllowChange(ui.getAllowChange());
				fu.setBizId(ui.getBizId());
				fu.setCategory(ui.getCategory());
				fu.setTags(ui.getTags());
				fu.setUploaderId(ui.getUploaderId());
				fu.setUploaderName(ui.getUploaderName());
				fu.setIsEncrypt(ui.getIsEncrypt());

				// 循环处理所有上传的文件.
				Integer i = 0;
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
								String rawString = fu.getContentType() + fu.getFileSize() + fu.getPostfix()
										+ fu.getIsEncrypt();
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
							FileListItem item = new FileListItem();
							item.setFrId(ur.getFrId());
							item.setFileSize(fu.getFileSize());
							item.setFileName(fu.getFileName());
							item.setPostfix(fu.getPostfix());
							item.setSpendTime(System.currentTimeMillis() - a1);

							r.add((i++).toString(), item);

						} catch (Exception e) {
							logger.error("上传文件时发生异常。", e);
							r.setError(e.getMessage(), e);
						}
					}
				}

				// ...
			}
		});

		return this.viewResult("file/upload");
	}

	/**
	 * 文件下载请求地址.
	 */
	@RequestMapping(value = "/file/get", method = RequestMethod.GET)
	public void getFile(String id, HttpServletRequest req, HttpServletResponse res) {

		Result<FileDownload> r = fileService.download(id);
		if (r.isSucceed()) {

			FileDownload fd = r.getData();
			try {

				String absPath = uploadProvider.convertAbsolutePath(fd.getLogicPath());
				File readFile = new File(absPath, fd.getFileId());
				byte[] fileContent = FileUtils.readFileToByteArray(readFile);
				if (fileContent.length > 0) {

					// 若是加密的文件需要先解密.
					if (fd.getIsEncrypted()) {
						fileContent = aesDecrypt(fileContent, fd.getSalt());
					}

					res.setContentType(fd.getContentType());
					res.setHeader("Content-Length", String.valueOf(fileContent.length));

					// 设置下载文件名.
					String agent = req.getHeader("USER-AGENT");
					String fileName = fd.getFileName().replace(' ', '+');
					if (agent.indexOf("Trident") != -1) {
						fileName = URLEncoder.encode(fd.getFileName(), "UTF8");
					} else if (agent.indexOf("Mozilla") != -1) {
						fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
					}
					res.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

					OutputStream os = res.getOutputStream();
					os.write(fileContent);
					os.close();
				} else {
					outText(res, "未能到读取到您请求下载的文件。");
				}
			} catch (Exception e) {
				logger.error(e);
				outText(res, "文件下载时发生异常。");
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
						Result<FileDownload> rd = fileService.download(id);
						if (rd.isSucceed()) {
							FileDownload fd = rd.getData();

							byte[] fileContent = new byte[0];
							if (fd.getIsEncrypted()) {
								fileContent = aesEncrypt(file.getBytes(), fd.getSalt());
							}

							String absPath = uploadProvider.convertAbsolutePath(fd.getLogicPath());
							File writeFile = new File(absPath, fd.getFileId());

							if (fd.getIsEncrypted()) {
								FileUtils.writeByteArrayToFile(writeFile, fileContent);
							} else {
								file.transferTo(writeFile);
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

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

	@RequestMapping(value = "/file/list", method = RequestMethod.GET)
	public String fileList(HttpServletRequest req, HttpServletResponse res) {

		return this.viewResult("file/list");
	}

	/**
	 * 对字节进行 AES 加密.
	 * 
	 * @param rawBytes
	 *            原始字节内容.
	 * @param encryptKey
	 *            私有密钥.
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
	 * @param encBytes
	 *            加密后的字节内容.
	 * @param decryptKey
	 *            私有密钥.
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

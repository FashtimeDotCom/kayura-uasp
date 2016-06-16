/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.explorer.util.XmlUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.kayura.activiti.vo.BpmModelVo;
import org.kayura.activiti.vo.TaskVo;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.tags.easyui.types.TreeNode;
import org.kayura.tags.types.FormAttribute;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Paginator;
import org.kayura.type.Result;
import org.kayura.uasp.po.BizForm;
import org.kayura.uasp.service.BpmService;
import org.kayura.utils.StringUtils;
import org.kayura.web.controllers.ActivitiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * BpmController
 *
 * @author liangxia@live.com
 */
@Controller
public class BpmController extends ActivitiController {

	@Autowired
	private BpmService readerBpmService;

	@Autowired
	private BpmService writerBpmService;

	@RequestMapping(value = "/modeler", method = RequestMethod.GET)
	public ModelAndView modeler() {

		ModelAndView mv = view("views/activiti/modeler");
		return mv;
	}

	@RequestMapping(value = "/bpm/biz/list", method = RequestMethod.GET)
	public ModelAndView bizList() {

		ModelAndView mv = view("views/bpm/biz-list");
		return mv;
	}

	@RequestMapping(value = "/bpm/biz/find", method = RequestMethod.POST)
	public void findBizList(Map<String, Object> map, HttpServletRequest req, String keyword) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);
				Result<PageList<BizForm>> r = readerBpmService.findBizForms(user.getTenantId(), keyword, pp);
				if (r.isSucceed()) {
					PageList<BizForm> pageList = r.getData();
					ps.setData(ui.putData(pageList));
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/biz/new", method = RequestMethod.GET)
	public ModelAndView createBizForm() {

		BizForm entity = new BizForm();
		ModelAndView mv = view("views/bpm/biz-edit", entity);
		return mv;
	}

	@RequestMapping(value = "/bpm/biz/edit", method = RequestMethod.GET)
	public ModelAndView editBizForm(String id) {

		ModelAndView mv;
		Result<BizForm> r = readerBpmService.getBizFormsById(id);
		if (r.isSucceed()) {
			BizForm entity = r.getData();
			mv = view("views/bpm/biz-edit", entity);
		} else {
			mv = this.error(r);
		}
		return mv;
	}

	@RequestMapping(value = "/bpm/biz/navtree", method = RequestMethod.POST)
	public void bizNavTree(Map<String, Object> map) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				List<TreeNode> nodes = new ArrayList<TreeNode>();
				TreeNode root = new TreeNode("ROOT", "所有表单定义");
				root.setState(TreeNode.STATE_OPEN);
				root.addAttr("key", "");

				Result<List<BizForm>> r = readerBpmService.loadBizForms(user.getTenantId());
				if (r.isSucceed()) {

					List<BizForm> list = r.getData();
					for (BizForm b : list) {

						TreeNode bizNode = new TreeNode(b.getId(), b.getDisplayName());
						bizNode.addAttr("key", b.getProcessKey());

						// 设计 type=0
						TreeNode designNode = new TreeNode("DESIGN#" + b.getId(), "设计");
						designNode.addAttr("key", b.getProcessKey());
						designNode.addAttr("type", 0);
						bizNode.addNode(designNode);

						// 发布 type=1
						TreeNode releaseNode = new TreeNode("RELEASE#" + b.getId(), "发布");
						releaseNode.addAttr("key", b.getProcessKey());
						releaseNode.addAttr("type", 1);
						bizNode.addNode(releaseNode);

						// 挂起 type=2
						TreeNode suspendNode = new TreeNode("SUSPEND#" + b.getId(), "挂起");
						suspendNode.addAttr("key", b.getProcessKey());
						suspendNode.addAttr("type", 2);
						bizNode.addNode(suspendNode);

						root.addNode(bizNode);
					}
				}

				nodes.add(root);
				ps.setData(nodes);
			}
		});
	}

	@RequestMapping(value = "/bpm/biz/save", method = RequestMethod.POST)
	public void saveBizForm(Map<String, Object> map, BizForm model) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				GeneralResult r = null;

				if (StringUtils.isEmpty(model.getId())) {

					LoginUser user = getLoginUser();

					model.setStatus(BizForm.STATUS_DESIGN);
					model.setTenantId(user.getTenantId());

					r = writerBpmService.insertBizForm(model);
				} else {

					r = writerBpmService.updateBizForm(model);
				}

				if (r != null) {
					ps.setResult(r);
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/biz/remove", method = RequestMethod.POST)
	public void removeBizForm(Map<String, Object> map, HttpServletRequest req, String ids) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String[] idList = ids.split(",");
				for (String id : idList) {
					writerBpmService.deleteBizForm(id);
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/task/list", method = RequestMethod.GET)
	public ModelAndView taskList() {

		ModelAndView mv = view("views/bpm/task-list");
		mv.addObject("jsid", RandomStringUtils.randomAlphabetic(4));
		return mv;
	}

	@RequestMapping(value = "/task/find", method = RequestMethod.POST)
	public void findTaskList(Map<String, Object> map, HttpServletRequest req, String keyword) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);

				TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(user.getUsername())
						.taskNameLike("%" + keyword + "%");

				long count = taskQuery.count();
				List<Task> list = taskQuery.listPage(pp.getOffset(), pp.getLimit());

				PageList<TaskVo> pageList = new PageList<TaskVo>(TaskVo.fromTasks(list), new Paginator(count));
				ps.setData(ui.putData(pageList));
			}
		});
	}

	@RequestMapping(value = "/bpm/task/claim", method = RequestMethod.POST)
	public void taskClaim(Map<String, Object> map, HttpServletRequest req, String id) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {
				taskService.claim(id, user.getUsername());
			}
		});
	}

	@RequestMapping(value = "/bpm/task/read", method = RequestMethod.GET)
	public ModelAndView taskRead(String id) {

		TaskFormData formData = formService.getTaskFormData(id);
		List<FormAttribute> props = convertFormData(formData);

		ModelAndView mv = view("views/bpm/task-form");
		mv.addObject("model", formData);
		mv.addObject("props", props);

		return mv;
	}

	@RequestMapping(value = "/bpm/task/handler", method = RequestMethod.POST)
	public void taskHandler(Map<String, Object> map, HttpServletRequest req, String id) {

		LoginUser user = this.getLoginUser();

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				TaskFormData formData = formService.getTaskFormData(id);

				List<FormProperty> formProperties = formData.getFormProperties();
				Map<String, String> formValues = new HashMap<String, String>();
				for (FormProperty formProperty : formProperties) {
					if (formProperty.isWritable()) {
						String value = req.getParameter(formProperty.getId());
						formValues.put(formProperty.getId(), value);
					}
				}

				identityService.setAuthenticatedUserId(user.getUsername());
				formService.submitTaskFormData(id, formValues);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private List<FormAttribute> convertFormData(FormData formData) {

		List<FormAttribute> props = new ArrayList<FormAttribute>();
		for (FormProperty formProperty : formData.getFormProperties()) {

			FormAttribute fa = new FormAttribute(formProperty.getId(), formProperty.getName(), formProperty.getValue(),
					formProperty.getType().getName());
			fa.setDatePattern(Objects.toString(formProperty.getType().getInformation("datePattern")));
			fa.setItems((Map<String, String>) formProperty.getType().getInformation("values"));
			fa.setReadable(formProperty.isReadable());
			fa.setWriteable(formProperty.isWritable());
			fa.setRequired(formProperty.isRequired());

			props.add(fa);
		}
		return props;
	}

	@RequestMapping(value = "/bpm/proc/list", method = RequestMethod.GET)
	public ModelAndView processList() {

		return view("views/bpm/proc-list");
	}

	@RequestMapping(value = "/bpm/proc/inst", method = RequestMethod.GET)
	public ModelAndView processInst() {

		return view("views/bpm/proc-inst");
	}

	@RequestMapping(value = "/bpm/proc/find", method = RequestMethod.POST)
	public void findProcessList(Map<String, Object> map, HttpServletRequest req,
			@RequestParam(value = "t", required = false) Integer type, String key, String keyword) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);

				if (type == null || (type != 0 && type != 1 && type != 2)) {
					PageList<BpmModelVo> pageList = new PageList<BpmModelVo>(new ArrayList<BpmModelVo>(),
							new Paginator(0));
					ps.setData(ui.putData(pageList));

				} else {

					if (type == 0) {
						ModelQuery query = repositoryService.createModelQuery().modelTenantId(user.getTenantId());
						if (!StringUtils.isEmpty(key)) {
							query.modelKey(key);
						}

						long size = query.count();
						List<Model> list = query.listPage(pp.getOffset(), pp.getLimit());

						PageList<BpmModelVo> pageList = new PageList<BpmModelVo>(BpmModelVo.fromModels(list),
								new Paginator(size));
						ps.setData(ui.putData(pageList));
					} else if (type == 1 || type == 2) {

						ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery()
								.processDefinitionTenantId(user.getTenantId());
						if (!StringUtils.isEmpty(key)) {
							query.processDefinitionKey(key);
						}

						if (type == 1) {
							query.active();
						} else {
							query.suspended();
						}

						long size = query.count();
						List<ProcessDefinition> list = query.listPage(pp.getOffset(), pp.getLimit());

						PageList<BpmModelVo> pageList = new PageList<BpmModelVo>(BpmModelVo.fromDefinitions(list),
								new Paginator(size));
						ps.setData(ui.putData(pageList));
					}
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/proc/remove", method = RequestMethod.POST)
	public void deleteProcess(Map<String, Object> map, HttpServletRequest req, String ids) {

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				String[] idList = ids.split(",");
				for (String id : idList) {
					repositoryService.deleteDeployment(id, true);
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/proc/import", method = RequestMethod.POST)
	public void importModel(Map<String, Object> map, String key, MultipartFile file) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				try {
					String fileName = file.getOriginalFilename();
					if (fileName.endsWith(".bpmn20.xml") || fileName.endsWith(".bpmn")) {

						XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
						InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(file.getBytes()),
								"UTF-8");
						XMLStreamReader xtr = xif.createXMLStreamReader(in);
						BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

						if (bpmnModel.getMainProcess() == null || bpmnModel.getMainProcess().getId() == null) {
							ps.setError("导入的模型无效。");
						} else {

							if (bpmnModel.getLocationMap().isEmpty()) {
								ps.setError("导入的模型无效。");
							} else {

								String processName = bpmnModel.getMainProcess().getName();
								if (StringUtils.isEmpty(processName)) {
									processName = bpmnModel.getMainProcess().getId();
								}

								Model model = repositoryService.newModel();

								ObjectNode metaInfo = new ObjectMapper().createObjectNode();
								metaInfo.put("name", processName);
								metaInfo.put("revision", 1);

								model.setMetaInfo(metaInfo.toString());
								model.setName(processName);
								model.setTenantId(user.getTenantId());
								model.setKey(key);

								repositoryService.saveModel(model);

								ObjectNode editorNode = new BpmnJsonConverter().convertToJson(bpmnModel);

								repositoryService.addModelEditorSource(model.getId(),
										editorNode.toString().getBytes("utf-8"));
							}
						}
					} else {
						ps.setError("导入的模型无效。");
					}
				} catch (Exception ex) {
					ps.setError("导入的模型无效。", ex);
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/proc/deploy", method = RequestMethod.POST)
	public void deploy(Map<String, Object> map, MultipartFile file) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String fileName = file.getOriginalFilename();
				try {
					InputStream inputStream = file.getInputStream();
					String extension = FilenameUtils.getExtension(fileName);
					DeploymentBuilder deployment = repositoryService.createDeployment();
					deployment.tenantId(user.getTenantId()).name("").category("");
					if (extension.equals("zip") || extension.equals("bar")) {
						ZipInputStream zipInputStream = new ZipInputStream(inputStream);
						deployment.addZipInputStream(zipInputStream);
					} else {
						deployment.addInputStream(fileName, inputStream);
					}
					deployment.deploy();

					ps.setSuccess("流程定义布署成功。");
				} catch (Exception e) {
					logger.error(e.getMessage());
					ps.setSuccess("流程定义布署失败。" + e.getMessage());
				}
			}
		});
	}

	@RequestMapping(value = "/bpm/proc/edit", method = RequestMethod.GET)
	public ModelAndView editProcess(String id) {

		ModelAndView mv = view("views/bpm/proc-edit");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/bpm/proc/res")
	@ResponseBody
	public void importProcess(HttpServletResponse response, String id, @RequestParam("t") Integer resType)
			throws IOException {

		LoginUser user = this.getLoginUser();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionTenantId(user.getTenantId()).processDefinitionId(id).singleResult();
		String resName = pd.getResourceName();
		if (resType == 2) {
			resName = pd.getDiagramResourceName();
		}
		InputStream stream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resName);

		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = stream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	@RequestMapping(value = "/bpm/proc/form/start", method = RequestMethod.GET)
	public ModelAndView startForm(String id) {

		StartFormData formData = formService.getStartFormData(id);
		List<FormAttribute> props = convertFormData(formData);

		ModelAndView mv = view("views/bpm/start-form");
		mv.addObject("model", formData);
		mv.addObject("props", props);

		return mv;
	}

	@RequestMapping(value = "/bpm/proc/start", method = RequestMethod.POST)
	public void startProcess(Map<String, Object> map, HttpServletRequest request, String processDefinitionId) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				StartFormData formData = formService.getStartFormData(processDefinitionId);
				List<FormProperty> formProperties = formData.getFormProperties();
				Map<String, String> formValues = new HashMap<String, String>();

				for (FormProperty formProperty : formProperties) {
					if (formProperty.isWritable()) {
						String value = request.getParameter(formProperty.getId());
						formValues.put(formProperty.getId(), value);
					}
				}

				identityService.setAuthenticatedUserId(user.getUsername());
				formService.submitStartFormData(processDefinitionId, formValues);
			}
		});
	}
}

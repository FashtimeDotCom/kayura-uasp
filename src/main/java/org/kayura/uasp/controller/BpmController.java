/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FilenameUtils;
import org.kayura.activiti.convert.VoConvert;
import org.kayura.activiti.vo.ProcessDefinitionVo;
import org.kayura.activiti.vo.TaskVo;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.tags.types.FormAttribute;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Paginator;
import org.kayura.utils.KeyUtils;
import org.kayura.web.controllers.ActivitiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * BpmController
 *
 * @author liangxia@live.com
 */
@Controller
@RequestMapping("/bpm")
public class BpmController extends ActivitiController {

	@RequestMapping(value = "/task/list", method = RequestMethod.GET)
	public ModelAndView taskList() {

		ModelAndView mv = view("views/bpm/task-list");
		String jsid = "j_" + KeyUtils.randomA(8);
		mv.addObject("jsid", jsid);
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

				PageList<TaskVo> pageList = new PageList<TaskVo>(VoConvert.toTasks(list), new Paginator(count));
				ps.setData(ui.genPageData(pageList));
			}
		});
	}

	@RequestMapping(value = "/task/claim", method = RequestMethod.POST)
	public void taskClaim(Map<String, Object> map, HttpServletRequest req, String id) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {
				taskService.claim(id, user.getUsername());
			}
		});
	}

	@RequestMapping(value = "/task/read", method = RequestMethod.GET)
	public ModelAndView taskRead(String id) {

		TaskFormData formData = formService.getTaskFormData(id);
		List<FormAttribute> props = convertFormData(formData);

		ModelAndView mv = view("views/bpm/task-form");
		mv.addObject("model", formData);
		mv.addObject("props", props);

		return mv;
	}

	@RequestMapping(value = "/task/handler", method = RequestMethod.POST)
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

	@RequestMapping(value = "/proc/list", method = RequestMethod.GET)
	public ModelAndView processList() {

		return view("views/bpm/proc-list");
	}

	@RequestMapping(value = "/proc/inst", method = RequestMethod.GET)
	public ModelAndView processInst(){

		return view("views/bpm/proc-inst");
	}

	@RequestMapping(value = "/proc/find", method = RequestMethod.POST)
	public void findProcessList(Map<String, Object> map, HttpServletRequest req, String keyword) {

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);
				List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(pp.getOffset(),
						pp.getLimit());
				PageList<ProcessDefinitionVo> pageList = new PageList<ProcessDefinitionVo>(
						VoConvert.toProcessDefinitions(list), new Paginator(list.size()));
				ps.setData(ui.genPageData(pageList));
			}
		});
	}

	@RequestMapping(value = "/proc/remove", method = RequestMethod.POST)
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

	@RequestMapping(value = "/proc/deploy", method = RequestMethod.POST)
	public void deploy(Map<String, Object> map, MultipartFile file) {

		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				String fileName = file.getOriginalFilename();
				try {
					InputStream inputStream = file.getInputStream();
					String extension = FilenameUtils.getExtension(fileName);
					DeploymentBuilder deployment = repositoryService.createDeployment();
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

	@RequestMapping(value = "/proc/edit", method = RequestMethod.GET)
	public ModelAndView editProcess(String id) {

		ModelAndView mv = view("views/bpm/proc-edit");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/proc/res")
	@ResponseBody
	public void importProcess(HttpServletResponse response, String id, @RequestParam("t") Integer resType)
			throws IOException {

		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
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

	@RequestMapping(value = "/proc/form/start", method = RequestMethod.GET)
	public ModelAndView startForm(String id) {

		StartFormData formData = formService.getStartFormData(id);
		List<FormAttribute> props = convertFormData(formData);

		ModelAndView mv = view("views/bpm/start-form");
		mv.addObject("model", formData);
		mv.addObject("props", props);

		return mv;
	}

	@RequestMapping(value = "/proc/start", method = RequestMethod.POST)
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

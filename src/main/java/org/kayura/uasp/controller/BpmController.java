/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.kayura.activiti.convert.VoConvert;
import org.kayura.activiti.vo.ProcessDefinitionVo;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Paginator;
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

		return view("views/bpm/task-list");
	}

	@RequestMapping(value = "/process/list", method = RequestMethod.GET)
	public ModelAndView processList() {

		return view("views/bpm/process-list");
	}

	@RequestMapping(value = "/process/find", method = RequestMethod.POST)
	public void findProcessList(Map<String, Object> map, HttpServletRequest req, String keyword) {

		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {

				PageParams pp = ui.getPageParams(req);
				List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(pp.getOffset(),
						pp.getLimit());
				PageList<ProcessDefinitionVo> pageList = new PageList<ProcessDefinitionVo>(VoConvert.to(list),
						new Paginator(list.size()));
				ps.setData(ui.genPageData(pageList));
			}
		});
	}

	@RequestMapping(value = "/process/deploy", method = RequestMethod.POST)
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

	@RequestMapping(value = "/process/edit", method = RequestMethod.GET)
	public ModelAndView editProcess(String id) {

		ModelAndView mv = view("views/bpm/process-edit");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/process/res")
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

	@RequestMapping(value = "/process/form/start", method = RequestMethod.GET)
	public ModelAndView startForm(String id) {

		StartFormData formData = formService.getStartFormData(id);
		ModelAndView mv = view("views/bpm/process-form-start");
		mv.addObject("model", formData);

		return mv;
	}

	@RequestMapping(value = "/process/start", method = RequestMethod.POST)
	public void startProcess(Map<String, Object> map, HttpServletRequest request, String processDefinitionId) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {

			@Override
			public void invoke(PostResult ps) {

				StartFormData formData = formService.getStartFormData(processDefinitionId);
				List<FormProperty> formProperties = formData.getFormProperties();
				Map<String, String> formValues = new HashMap<String, String>();

				for (FormProperty formProperty : formProperties) {

					String value = request.getParameter(formProperty.getId());
					formValues.put(formProperty.getId(), value);
				}

				identityService.setAuthenticatedUserId(user.getUsername());
				formService.submitStartFormData(processDefinitionId, formValues);
			}
		});

	}
}

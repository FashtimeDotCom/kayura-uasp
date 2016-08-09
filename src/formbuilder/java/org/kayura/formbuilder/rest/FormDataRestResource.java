package org.kayura.formbuilder.rest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormData;
import org.kayura.formbuilder.model.MapContent;
import org.kayura.formbuilder.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormDataRestResource {

	@Autowired
	protected FormDataService formDataService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected RuntimeService runtimeService;

	@RequestMapping(value = "/form/start/submit", method = RequestMethod.POST)
	public void submitAutoForm(HttpServletRequest request, FormData formData, String receivers, String tenantId) {

		Map<String, Object> variables = new HashMap<String, Object>();

		MapContent fields = new MapContent();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String element = parameterNames.nextElement();
			if (element.startsWith("$F_")) {
				String argName = element.substring(3);
				variables.put(argName, request.getParameter(element));
				fields.put(argName, request.getParameter(element));
			}
		}

		if (StringUtils.isEmpty(formData.getTitle())) {
			formData.setTitle(formData.getFormName());
		}

		formData.setContent(fields);
		formDataService.insertFormData(formData);

		variables.put("title", formData.getTitle());
		variables.put("candidateUsers", receivers);

		identityService.setAuthenticatedUserId(formData.getCreator());
		runtimeService.startProcessInstanceByKeyAndTenantId(formData.getProcessKey(), formData.getDataId(), variables,
				tenantId);
	}

	@RequestMapping(value = "/form/approve/submit", method = RequestMethod.POST)
	public void submitApprove() {

	}

}

package org.kayura.web.controllers;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public abstract class ActivitiController extends BaseController {

	protected IdentityService identityService;
	protected TaskService taskService;
	protected RepositoryService repositoryService;
	protected FormService formService;
	protected RuntimeService runtimeService;
	protected HistoryService historyService;

	public ActivitiController() {

		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		runtimeService = engine.getRuntimeService();
		identityService = engine.getIdentityService();
		taskService = engine.getTaskService();
		repositoryService = engine.getRepositoryService();
		formService = engine.getFormService();
		historyService = engine.getHistoryService();
	}

}

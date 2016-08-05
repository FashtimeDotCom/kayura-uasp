package org.kayura.formbuilder.controller;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormModel;
import org.kayura.formbuilder.service.FormModelService;
import org.kayura.formbuilder.vo.FormViewTempletes;
import org.kayura.type.Result;
import org.kayura.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormBuilderController extends BaseController {

	@Autowired
	private FormViewTempletes viewTempletes;

	@Autowired
	private FormModelService formModelService;

	public String getViewTemplete(String viewKey) {

		if (StringUtils.isEmpty(viewKey)) {
			return viewTempletes.first();
		} else {
			return viewTempletes.get(viewKey);
		}
	}

	@RequestMapping(value = "/formbuilder/mobile", method = RequestMethod.GET)
	public ModelAndView mobileIndex(String modelId) {

		ModelAndView mv = this.view("views/formbuilder/mobile");
		mv.addObject("modelId", modelId);
		return mv;
	}

	@RequestMapping(value = "/formbuilder/{formKey}/create", method = RequestMethod.GET)
	public ModelAndView createFormModel(@PathVariable String formKey, String tenantId) {

		FormModel formModel = new FormModel();
		formModel.setFormKey(formKey);
		formModel.setTenantId(tenantId);

		ModelAndView mv = this.view("views/formbuilder/model-edit");
		mv.addObject("model", formModel);
		return mv;
	}

	@RequestMapping(value = "/formbuilder/{modelId}/edit", method = RequestMethod.GET)
	public ModelAndView editFormModel(@PathVariable String modelId) {

		Result<FormModel> r = formModelService.selectFormModel(modelId, null, null, null, null);
		if (r.isSucceed()) {
			FormModel formModel = r.getData();
			ModelAndView mv = this.view("views/formbuilder/model-edit");
			mv.addObject("model", formModel);
			return mv;
		}
		return null;
	}

	@RequestMapping(value = "/form/new", method = RequestMethod.GET)
	public ModelAndView createNewForm(String formKey, String code, String view) {

		Result<FormModel> r = formModelService.selectFormModel(null, null, formKey, code, FormModel.STATUS_RUN);
		if (r.isSucceed()) {
			ModelAndView mv = this.view(getViewTemplete(view));
			mv.addObject("model", r.getData());
			return mv;
		}

		return null;
	}

	@RequestMapping(value = "/form/edit", method = RequestMethod.GET)
	public ModelAndView editForm(String id, String view) {

		ModelAndView mv = this.view(getViewTemplete(view));
		return mv;
	}

}

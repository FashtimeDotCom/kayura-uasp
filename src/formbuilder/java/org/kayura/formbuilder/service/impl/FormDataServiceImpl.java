package org.kayura.formbuilder.service.impl;

import org.kayura.formbuilder.model.FormData;
import org.kayura.formbuilder.model.FormDataContent;
import org.kayura.formbuilder.service.FormDataService;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;

public class FormDataServiceImpl implements FormDataService {

	@Override
	public Result<PageList<FormData>> selectFormDatas(String tenantId, String formKey, String modelCode, String modelId,
			String creator, String keyword, PageParams pageParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<FormData> selectFormDataById(String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult insertFormData(FormData formData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateFormDataInfo(FormData formData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult updateFormDataContent(String dataId, FormDataContent content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralResult deleteFormDataById(String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

}

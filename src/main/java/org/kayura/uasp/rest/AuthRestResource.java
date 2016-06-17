package org.kayura.uasp.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.Role;
import org.kayura.uasp.service.AuthorityService;
import org.kayura.web.rest.RestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class AuthRestResource extends RestResource {

	@Autowired
	private AuthorityService readerAuthorityService;

	@Autowired
	private AuthorityService writerAuthorityService;

	@RequestMapping(value = "roles/find", method = RequestMethod.GET)
	public void findRoles(Map<String, Object> map, HttpServletRequest req, String keyword) {

		LoginUser user = this.getLoginUser();
		postExecute(map, new PostAction() {
			@Override
			public void invoke(PostResult ps) {
				PageParams pageParams = ui.getPageParams(req);
				Result<PageList<Role>> r = readerAuthorityService.findRoles(user.getTenantId(), keyword, pageParams);
				ps.setResult(r.getCode(), r.getMessage(), ui.putData(r.getData()));
			}
		});
	}

}

/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author liangxia@live.com
 */
public class LoginUser extends User {

	private static final long serialVersionUID = 4002208269317073182L;

	public static final String ROLE_USER = "USER";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_ROOT = "ROOT";

	private String userId;
	private String identityId;
	private Map<String, Object> identities = new HashMap<String, Object>();
	private String tenantId;
	private String salt;
	private String displayName;
	private List<Integer> privileges = new ArrayList<Integer>();

	public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Object getIdentity() {
		return identities.get(this.identityId);
	}

	public Object getIdentity(String identityId) {
		return identities.get(identityId);
	}

	public boolean hasAnyRole(String... roles) {

		Collection<GrantedAuthority> grantedAuthorityList = getAuthorities();
		for (String role : roles) {
			for (GrantedAuthority authority : grantedAuthorityList) {
				if (role.equals(authority.getAuthority())) {
					return true;
				}
			}
		}

		return false;
	}

	public Boolean hasRoot() {
		return hasAnyRole(ROLE_ROOT);
	}

	public Boolean hasAdmin() {
		return hasAnyRole(ROLE_ADMIN);
	}

	public Boolean hasUser() {
		return hasAnyRole(ROLE_USER);
	}

	public List<Integer> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Integer> privileges) {

		if (privileges != null) {
			for (Integer i : privileges) {
				if (!this.privileges.contains(i)) {
					this.privileges.add(i);
				}
			}
		}
	}

	public Boolean hasPrivilege(int[] privileges) {

		for (int i : privileges) {
			if (this.privileges.contains(i)) {
				return true;
			}
		}
		return false;
	}

}

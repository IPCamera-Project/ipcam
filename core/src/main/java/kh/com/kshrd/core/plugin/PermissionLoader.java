package kh.com.kshrd.core.plugin;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;

/**
 * Created by sophatvathana on 15/12/16.
 */

public class PermissionLoader extends Policy {


	public PermissionCollection getPermissions(CodeSource codeSource) {
		Permissions p = new Permissions();
		if (!codeSource.getLocation().toString().endsWith("/*.jar")) {
			p.add(new AllPermission());
		}
		return p;
	}

	public void refresh() {
	}

}

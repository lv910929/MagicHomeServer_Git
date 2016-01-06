package com.lencity.magichome.intercepter;

import java.util.Map;

import com.lencity.magichome.action.BaseAction;
import com.lencity.magichome.model.AdminUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class PlatformAuthorize extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		ActionContext actionContext = invocation.getInvocationContext();
		
		Map session = actionContext.getSession();
		AdminUser adminUser = (AdminUser) session
				.get(BaseAction.SESSION_ADMIN_USER);
		if (null != adminUser)
		{
			return invocation.invoke();
		} else
		{
			return "adminLogin";
		}
	}
}

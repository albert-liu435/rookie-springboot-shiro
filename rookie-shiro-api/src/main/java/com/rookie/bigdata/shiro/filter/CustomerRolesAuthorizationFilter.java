package com.rookie.bigdata.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * @Classname RolesAuthorizationFilter
 * @Description 自定义角色控制过滤器，参考{@link RolesAuthorizationFilter},其中{@link RolesAuthorizationFilter}定义了全部角色通过校验才放行
 * @Author rookie
 * @Date 2021/7/13 17:46
 * @Version 1.0
 */
public class CustomerRolesAuthorizationFilter extends AuthorizationFilter {



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //获取角色
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            //没有角色限制，直接访问
            return true;
        }

//        Set<String> roles = CollectionUtils.asSet(rolesArray);
//        return subject.hasAllRoles(roles);

        for (int i = 0; i < rolesArray.length; i++) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }
        return false;
    }
}

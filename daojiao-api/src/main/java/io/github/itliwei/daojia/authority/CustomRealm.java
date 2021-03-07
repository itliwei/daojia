package io.github.itliwei.daojia.authority;

import io.github.itliwei.daojia.system.auth.entity.Resource;
import io.github.itliwei.daojia.system.auth.entity.Role;
import io.github.itliwei.daojia.system.auth.entity.RoleAuthority;
import io.github.itliwei.daojia.system.auth.entity.SysUserRole;
import io.github.itliwei.daojia.system.auth.query.SysUserRoleQueryModel;
import io.github.itliwei.daojia.system.auth.service.RoleService;
import io.github.itliwei.daojia.system.auth.service.SysUserRoleService;
import io.github.itliwei.daojia.system.user.entity.SysUser;
import io.github.itliwei.daojia.system.user.query.SysUserQueryModel;
import io.github.itliwei.daojia.system.user.service.SysUserService;
import io.github.itliwei.daojia.tenant.base.TenantBaseEntity;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.VService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private VService vService;


    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken authenticationToken
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            throw new AccountException("用户名不能为空");
        }
        String name = authenticationToken.getPrincipal().toString();
        // 从数据库获取对应用户名密码的用户
        SysUserQueryModel queryModel = SysUserQueryModel.builder().nameEQ(name).build();
        SysUser sysUser = sysUserService.findOne(queryModel);
        if (sysUser==null || sysUser.getPassword() == null
                || !sysUser.getPassword().equals(new String((char[]) authenticationToken.getCredentials()))) {
            throw new AccountException("用户名或密码不正确");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
                sysUser.getPassword(), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();

        SysUserQueryModel queryModel = SysUserQueryModel.builder().nameEQ(name).build();
        SysUser sysUser = sysUserService.findOne(queryModel);
        if (sysUser == null){
            throw new AccountException("用户不存在");
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        SysUserRoleQueryModel build = SysUserRoleQueryModel.builder().userIdEQ(sysUser.getId()).build();
        List<SysUserRole> list = sysUserRoleService.findList(build);
        List<Long> roleIdList=list.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

        List<Role> roleList = vService.findList(Role.class, roleIdList);

        List<RoleAuthority> roleAuthorityList = vService.findList(RoleAuthority.class, roleIdList);
        List<Long> authorityIdList=roleAuthorityList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        List<Resource> resourceList = vService.findList(Resource.class, authorityIdList);
        for (Role role :roleList){
            simpleAuthorizationInfo.addRole(role.getCode());
        }
        for (Resource resource : resourceList) {
            simpleAuthorizationInfo.addStringPermission(resource.getCode());
        }

        return simpleAuthorizationInfo;
    }
}
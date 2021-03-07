package io.github.itliwei.daojia.authority;

import io.github.itliwei.daojia.system.user.service.SysUserService;
import io.github.itliwei.vboot.vorm.orm.VService;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.response.Resp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Resp login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        String sessionId = (String) subject.getSession().getId();
        return Resp.success(sessionId);
    }

    @RequestMapping(value = "/noLogin", method = RequestMethod.GET)
    public Resp notLogin() {
        return Resp.error(ErrorCode.SIGN,"您尚未登录");

    }

    @RequestMapping(value = "/noRole", method = RequestMethod.GET)
    public Resp notRole() {
        return Resp.error(ErrorCode.AUTHORITY,"您没有权限");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Resp logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return Resp.success("成功注销");

    }


}
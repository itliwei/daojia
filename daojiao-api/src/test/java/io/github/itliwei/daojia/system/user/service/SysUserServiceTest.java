package io.github.itliwei.daojia.system.user.service; 
 
import io.github.itliwei.daojia.system.user.entity.SysUser;
import io.github.itliwei.daojia.system.user.query.SysUserQueryModel;
import io.github.itliwei.daojia.tenant.base.TenantBaseEntity;
import io.github.itliwei.daojia.tenant.config.ContextUtil;
import io.github.itliwei.daojia.tenant.entity.Tenant;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/** 
* SysUserService Tester. 
* 
* @author <Authors name> 
* @since <pre>三月 6, 2021</pre> 
* @version 1.0 
*/ 

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @BeforeAll
    public void before() throws Exception {
        ContextUtil.setTenant("test");
    } 

    @AfterAll
    public void after() throws Exception { 
    }

    @Test
    void addUserTest() {
        SysUser build = SysUser.builder().avatar("a").email("1").isDel(0).name("liwei").password("test").phone("test").build();
        sysUserService.save(build);
    }

    @Test
    void findByIdTest() {
        SysUser sysUser = sysUserService.findById(1L);
        System.out.println(sysUser.toString());
    }

    @Test
    void queryModelTest() {
        SysUserQueryModel sysUserQueryModel = new SysUserQueryModel();
        sysUserQueryModel.setNameEQ("liwei");
        List<SysUser> list = sysUserService.findList(sysUserQueryModel);
        System.out.println(list.size());
    }
}

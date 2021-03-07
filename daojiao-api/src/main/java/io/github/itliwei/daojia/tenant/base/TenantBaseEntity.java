package io.github.itliwei.daojia.tenant.base;

import io.github.itliwei.vboot.vorm.annotation.Field;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : liwei
 * @date : 2021/03/06 16:18
 * @description : ${TODO}
 */
@Getter
@Setter
public class TenantBaseEntity extends IdEntity {

    private String tenant;
}

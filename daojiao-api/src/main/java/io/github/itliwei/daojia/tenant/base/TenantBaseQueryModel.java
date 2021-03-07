package io.github.itliwei.daojia.tenant.base;

import io.github.itliwei.vboot.vorm.orm.opt.QueryModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : liwei
 * @date : 2021/03/06 16:30
 * @description : ${TODO}
 */
@Getter
@Setter
public class TenantBaseQueryModel extends QueryModel {
    private String tenantEQ;
}

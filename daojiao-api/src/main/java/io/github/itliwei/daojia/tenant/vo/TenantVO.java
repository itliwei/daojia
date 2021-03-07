package io.github.itliwei.daojia.tenant.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import java.util.Date;
import io.github.itliwei.daojia.tenant.entity.Tenant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TenantVO", description = "TenantVO")
public class TenantVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "用户名称")
	private String code;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "用户名称")
	private String logo;
	@ApiModelProperty(value = "负责人姓名")
	private String manager;
	@ApiModelProperty(value = "负责人手机号")
	private String managerPhone;
	@ApiModelProperty(value = "过期时间")
	private Date expirationTime;
	@ApiModelProperty(value = "状态：0：注册 1：试用 2：有效 3：过期")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/* 扩展 */

	/* 转换 */

	public static TenantVO convert2TenantVO(Tenant source){
		TenantVOConvert convert = new TenantVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class TenantVOConvert extends Converter<TenantVO, Tenant> {
		@Override
		protected Tenant doForward(TenantVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected TenantVO doBackward(Tenant source) {
		TenantVO target = new TenantVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}
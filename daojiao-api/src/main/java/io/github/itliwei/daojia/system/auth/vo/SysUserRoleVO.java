package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.SysUserRole;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserRoleVO", description = "SysUserRoleVO")
public class SysUserRoleVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "角色")
	private Long roleId;
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */

	public static SysUserRoleVO convert2SysUserRoleVO(SysUserRole source){
		SysUserRoleVOConvert convert = new SysUserRoleVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class SysUserRoleVOConvert extends Converter<SysUserRoleVO, SysUserRole> {
		@Override
		protected SysUserRole doForward(SysUserRoleVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected SysUserRoleVO doBackward(SysUserRole source) {
		SysUserRoleVO target = new SysUserRoleVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}
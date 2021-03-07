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
@ApiModel(value = "SysUserRoleDTO", description = "SysUserRoleDTO")
public class SysUserRoleDTO implements Serializable {
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
	public SysUserRole convert2SysUserRole(){
		SysUserRoleDTOConvert convert = new SysUserRoleDTOConvert();
		SysUserRole target = convert.convert(this);
		return target;
	}


	private static class SysUserRoleDTOConvert extends Converter<SysUserRoleDTO, SysUserRole> {
		@Override
		protected SysUserRole doForward(SysUserRoleDTO source) {
			SysUserRole target = new SysUserRole();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected SysUserRoleDTO doBackward(SysUserRole source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}
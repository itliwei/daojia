package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.RoleAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RoleAuthorityVO", description = "RoleAuthorityVO")
public class RoleAuthorityVO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "资源ID")
	private Long authorityId;
	@ApiModelProperty(value = "权限类型：MENU:菜单;RESOURCE:资源;")
	private String authorityType;
	@ApiModelProperty(value = "角色ID")
	private Long roleId;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */

	public static RoleAuthorityVO convert2RoleAuthorityVO(RoleAuthority source){
		RoleAuthorityVOConvert convert = new RoleAuthorityVOConvert();
		return  convert.reverse().convert(source);
	}


	private static class RoleAuthorityVOConvert extends Converter<RoleAuthorityVO, RoleAuthority> {
		@Override
		protected RoleAuthority doForward(RoleAuthorityVO source) {
		throw new AssertionError("不支持逆向转化方法!");
	}

	@Override
	protected RoleAuthorityVO doBackward(RoleAuthority source) {
		RoleAuthorityVO target = new RoleAuthorityVO();
		BeanConvertUtil.convert(target,source);
		return target;
	}
	}
}
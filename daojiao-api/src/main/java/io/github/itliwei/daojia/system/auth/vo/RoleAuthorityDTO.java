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
@ApiModel(value = "RoleAuthorityDTO", description = "RoleAuthorityDTO")
public class RoleAuthorityDTO implements Serializable {
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
	public RoleAuthority convert2RoleAuthority(){
		RoleAuthorityDTOConvert convert = new RoleAuthorityDTOConvert();
		RoleAuthority target = convert.convert(this);
		return target;
	}


	private static class RoleAuthorityDTOConvert extends Converter<RoleAuthorityDTO, RoleAuthority> {
		@Override
		protected RoleAuthority doForward(RoleAuthorityDTO source) {
			RoleAuthority target = new RoleAuthority();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected RoleAuthorityDTO doBackward(RoleAuthority source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}
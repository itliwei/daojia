package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.Role;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserDTO", description = "SysUserDTO")
public class SysUserDTO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "编码")
	private String code;
	@ApiModelProperty(value = "数据权限：ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人")
	private String dataType;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */
	public Role convert2Role(){
		SysUserDTOConvert convert = new SysUserDTOConvert();
		Role target = convert.convert(this);
		return target;
	}


	private static class SysUserDTOConvert extends Converter<SysUserDTO, Role> {
		@Override
		protected Role doForward(SysUserDTO source) {
			Role target = new Role();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected SysUserDTO doBackward(Role source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}
package io.github.itliwei.daojia.system.user.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.user.entity.SysUser;
import java.util.Date;

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
	@ApiModelProperty(value = "用户名称")
	private String username;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "部门")
	private Long orgId;
	@ApiModelProperty(value = "岗位")
	private Long stationId;
	@ApiModelProperty(value = "手机号")
	private String phone;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "头像")
	private String avatar;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/* 扩展 */

	/* 转换 */
	public SysUser convert2SysUser(){
		SysUserDTOConvert convert = new SysUserDTOConvert();
		SysUser target = convert.convert(this);
		return target;
	}


	private static class SysUserDTOConvert extends Converter<SysUserDTO, SysUser> {
		@Override
		protected SysUser doForward(SysUserDTO source) {
			SysUser target = new SysUser();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected SysUserDTO doBackward(SysUser source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}
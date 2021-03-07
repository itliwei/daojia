package io.github.itliwei.daojia.system.auth.vo;

import lombok.*;

import java.io.Serializable;
import io.swagger.annotations.*;
import com.google.common.base.Converter;
import io.github.itliwei.vboot.vorm.generator.util.BeanConvertUtil;

import io.github.itliwei.daojia.system.auth.entity.Menu;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MenuDTO", description = "MenuDTO")
public class MenuDTO implements Serializable {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "父ID")
	private Long parentId;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "路径")
	private String path;
	@ApiModelProperty(value = "分组")
	private String groupName;
	@ApiModelProperty(value = "排序")
	private Integer sortValue;
	@ApiModelProperty(value = "是否有效")
	private Integer status;
	@ApiModelProperty(value = "是否删除")
	private Integer isDel;
	/* 扩展 */

	/* 转换 */
	public Menu convert2Menu(){
		MenuDTOConvert convert = new MenuDTOConvert();
		Menu target = convert.convert(this);
		return target;
	}


	private static class MenuDTOConvert extends Converter<MenuDTO, Menu> {
		@Override
		protected Menu doForward(MenuDTO source) {
			Menu target = new Menu();
			BeanConvertUtil.convert(target,source);
			return target;
		}

		@Override
		protected MenuDTO doBackward(Menu source) {
			throw new AssertionError("不支持逆向转化方法!");
		}
	}

}
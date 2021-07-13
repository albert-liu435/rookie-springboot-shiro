package com.rookie.bigdata.mapper;

import com.rookie.bigdata.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rookie
 * @since 2021-07-13
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuByRoleId(Integer id);

}

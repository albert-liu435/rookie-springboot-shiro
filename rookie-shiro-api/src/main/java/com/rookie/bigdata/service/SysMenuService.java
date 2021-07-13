package com.rookie.bigdata.service;

import com.rookie.bigdata.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rookie
 * @since 2021-07-13
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuByRoleId(Integer id);
}

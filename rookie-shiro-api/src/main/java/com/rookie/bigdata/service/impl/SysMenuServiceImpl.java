package com.rookie.bigdata.service.impl;

import com.rookie.bigdata.entity.SysMenu;
import com.rookie.bigdata.mapper.SysMenuMapper;
import com.rookie.bigdata.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rookie
 * @since 2021-07-13
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectMenuByRoleId(Integer id) {


        return sysMenuMapper.selectMenuByRoleId(id);
    }
}

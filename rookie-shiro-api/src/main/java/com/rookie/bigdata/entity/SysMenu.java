package com.rookie.bigdata.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rookie
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 资源编码
     */
    private String code;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String api;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 更新日期
     */
    private String updateDate;


}

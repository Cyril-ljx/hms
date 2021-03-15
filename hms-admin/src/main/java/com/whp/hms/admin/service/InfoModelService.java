package com.whp.hms.admin.service;

import com.whp.hms.core.entity.InfoModel;

import java.util.List;

/**
 * 酒店信息模板(InfoModel)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface InfoModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InfoModel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<InfoModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param infoModel 实例对象
     * @return 实例对象
     */
    InfoModel insert(InfoModel infoModel);

    /**
     * 修改数据
     *
     * @param infoModel 实例对象
     * @return 实例对象
     */
    InfoModel update(InfoModel infoModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    boolean batchDelete(List<Integer> ids);

    Integer queryCount();

    boolean showById(Integer id);
}
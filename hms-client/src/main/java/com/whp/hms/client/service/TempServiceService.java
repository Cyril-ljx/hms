package com.whp.hms.client.service;

import com.whp.hms.core.entity.TempService;
import java.util.List;

/**
 * 临时服务：用户通过 前台预定系统 发送的服务请求(TempService)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:34
 */
public interface TempServiceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TempService queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TempService> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tempService 实例对象
     * @return 实例对象
     */
    TempService insert(TempService tempService);

    /**
     * 修改数据
     *
     * @param tempService 实例对象
     * @return 实例对象
     */
    TempService update(TempService tempService);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
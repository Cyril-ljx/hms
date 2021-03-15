package com.whp.hms.client.service;

import com.whp.hms.core.entity.RoomType;

import java.util.List;

/**
 * 房间类型(RoomType)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomType queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoomType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param roomType 实例对象
     * @return 实例对象
     */
    RoomType insert(RoomType roomType);

    /**
     * 修改数据
     *
     * @param roomType 实例对象
     * @return 实例对象
     */
    RoomType update(RoomType roomType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<RoomType> getAll();
}
package com.whp.hms.admin.service;

import com.whp.hms.core.entity.LiveRecord;

import java.util.List;

/**
 * 入住记录(LiveRecord)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface LiveRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LiveRecord queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LiveRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param liveRecord 实例对象
     * @return 实例对象
     */
    LiveRecord insert(LiveRecord liveRecord);

    /**
     * 修改数据
     *
     * @param liveRecord 实例对象
     * @return 实例对象
     */
    LiveRecord update(LiveRecord liveRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<LiveRecord> queryAdminList(Integer offset, Integer limit, String nickname, String room);

    Integer queryAdminCount(String nickname, String room);
}
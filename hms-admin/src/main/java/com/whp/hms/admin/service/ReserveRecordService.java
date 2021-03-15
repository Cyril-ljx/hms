package com.whp.hms.admin.service;

import com.whp.hms.core.entity.ReserveRecord;

import java.util.List;

/**
 * 订房记录(ReserveRecord)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface ReserveRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReserveRecord queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ReserveRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param reserveRecord 实例对象
     * @return 实例对象
     */
    ReserveRecord insert(ReserveRecord reserveRecord);

    /**
     * 修改数据
     *
     * @param reserveRecord 实例对象
     * @return 实例对象
     */
    ReserveRecord update(ReserveRecord reserveRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<ReserveRecord> queryAdminList(Integer offset, Integer limit, String nickname, String room);

    Integer queryAdminCount(String nickname, String room);
}
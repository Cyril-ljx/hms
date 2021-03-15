package com.whp.hms.admin.service;

import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.Notice;

import java.util.List;

/**
 * 前台公告栏(Notice)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notice queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Notice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Notice> queryAdminList(Integer offset, Integer limit);

    Integer queryAdminCount();

    /*
    *通过主键进行首显
    *
    */
    boolean showById(Integer id);
}
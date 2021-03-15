package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.NoticeService;
import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.Notice;
import com.whp.hms.mapper.NoticeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台公告栏(Notice)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer id) {
        return this.noticeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Notice> queryAllByLimit(int offset, int limit) {
        return this.noticeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice insert(Notice notice) {
        this.noticeDao.insert(notice);
        return notice;
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice update(Notice notice) {
        this.noticeDao.update(notice);
        return this.queryById(notice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.noticeDao.deleteById(id) > 0;
    }

    @Override
    public List<Notice> queryAdminList(Integer offset, Integer limit) {
        return this.noticeDao.queryAdminList(offset, limit);
    }

    @Override
    public Integer queryAdminCount() {
        return this.noticeDao.queryAdminCount();
    }

    @Override
    public boolean showById(Integer id) {
        return this.noticeDao.showById(id);
    }
}
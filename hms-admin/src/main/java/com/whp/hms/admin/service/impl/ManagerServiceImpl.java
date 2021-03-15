package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.ManagerService;
import com.whp.hms.core.entity.Manager;
import com.whp.hms.mapper.ManagerDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员：可以登录后台管理的用户(Manager)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerDao managerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Manager queryById(Integer id) {
        return this.managerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Manager> queryAllByLimit(int offset, int limit) {
        return this.managerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param manager 实例对象
     * @return 实例对象
     */
    @Override
    public Manager insert(Manager manager) {
        this.managerDao.insert(manager);
        return manager;
    }

    /**
     * 修改数据
     *
     * @param manager 实例对象
     * @return 实例对象
     */
    @Override
    public Manager update(Manager manager) {
        this.managerDao.update(manager);
        return this.queryById(manager.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.managerDao.deleteById(id) > 0;
    }

    @Override
    public Manager queryByUserName(String username) {
        return this.managerDao.queryByUserName(username);
    }

    @Override
    public List<Manager> queryAdminList(int uid, Integer offset, Integer limit) {
        return this.managerDao.queryAdminList(uid, offset, limit);
    }

    @Override
    public Integer queryAdminCount(int uid) {
        return this.managerDao.queryAdminCount(uid);
    }
}
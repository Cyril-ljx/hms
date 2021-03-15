package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.LoginLogService;
import com.whp.hms.core.entity.LoginLog;
import com.whp.hms.mapper.LoginLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志(LoginLog)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogDao loginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LoginLog queryById(Integer id) {
        return this.loginLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LoginLog> queryAllByLimit(int offset, int limit) {
        return this.loginLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog insert(LoginLog loginLog) {
        this.loginLogDao.insert(loginLog);
        return loginLog;
    }

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog update(LoginLog loginLog) {
        this.loginLogDao.update(loginLog);
        return this.queryById(loginLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean batchDelete(List<Integer> ids)
    {
        return this.loginLogDao.batchDelete(ids) > 0;
    }

    @Override
    public boolean deleteAll() {
        return this.loginLogDao.deleteAll() > 0;
    }

    @Override
    public Integer queryAdminCount() {
        return this.loginLogDao.queryAdminCount();
    }

    @Override
    public List<LoginLog> queryAdminList(Integer offset, Integer limit) {
        return this.loginLogDao.queryAdminList(offset, limit);
    }
}
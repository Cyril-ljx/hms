package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.TempServiceService;
import com.whp.hms.core.entity.TempService;
import com.whp.hms.mapper.TempServiceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 临时服务：用户通过 前台预定系统 发送的服务请求(TempService)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("tempServiceService")
public class TempServiceServiceImpl implements TempServiceService {
    @Resource
    private TempServiceDao tempServiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TempService queryById(Integer id) {
        return this.tempServiceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TempService> queryAllByLimit(int offset, int limit) {
        return this.tempServiceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tempService 实例对象
     * @return 实例对象
     */
    @Override
    public TempService insert(TempService tempService) {
        this.tempServiceDao.insert(tempService);
        return tempService;
    }

    /**
     * 修改数据
     *
     * @param tempService 实例对象
     * @return 实例对象
     */
    @Override
    public TempService update(TempService tempService) {
        this.tempServiceDao.update(tempService);
        return this.queryById(tempService.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tempServiceDao.deleteById(id) > 0;
    }

    @Override
    public List<TempService> list(Integer offset, Integer limit, Integer state) {
        return this.tempServiceDao.list(offset, limit, state);
    }

    @Override
    public Integer queryCount(Integer state) {
        return this.tempServiceDao.queryCount(state);

    }
}
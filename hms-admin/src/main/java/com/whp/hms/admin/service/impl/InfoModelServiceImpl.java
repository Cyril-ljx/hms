package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.InfoModelService;
import com.whp.hms.core.entity.InfoModel;
import com.whp.hms.mapper.InfoModelDao;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店信息模板(InfoModel)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("infoModelService")
public class InfoModelServiceImpl implements InfoModelService {
    @Resource
    private InfoModelDao infoModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InfoModel queryById(Integer id) {
        return this.infoModelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<InfoModel> queryAllByLimit(int offset, int limit) {
        return this.infoModelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param infoModel 实例对象
     * @return 实例对象
     */
    @Override
    public InfoModel insert(InfoModel infoModel) {
        this.infoModelDao.insert(infoModel);
        return infoModel;
    }

    /**
     * 修改数据
     *
     * @param infoModel 实例对象
     * @return 实例对象
     */
    @Override
    public InfoModel update(InfoModel infoModel) {
        this.infoModelDao.update(infoModel);
        return this.queryById(infoModel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.infoModelDao.deleteById(id) > 0;
    }

    @Override
    public boolean batchDelete(List<Integer> ids) {
        return this.infoModelDao.batchDelete(ids) > 0;
    }

    @Override
    public Integer queryCount() {
        return this.infoModelDao.queryCount();
    }

    @Override
    public boolean showById(Integer id) {
        return this.infoModelDao.showById(id);
    }

}
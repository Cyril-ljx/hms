package com.whp.hms.client.service.impl;

import com.whp.hms.core.entity.LiveRecord;
import com.whp.hms.mapper.LiveRecordDao;
import com.whp.hms.client.service.LiveRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入住记录(LiveRecord)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("liveRecordService")
public class LiveRecordServiceImpl implements LiveRecordService {
    @Resource
    private LiveRecordDao liveRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LiveRecord queryById(Integer id) {
        return this.liveRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<LiveRecord> queryAllByLimit(int offset, int limit) {
        return this.liveRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param liveRecord 实例对象
     * @return 实例对象
     */
    @Override
    public LiveRecord insert(LiveRecord liveRecord) {
        this.liveRecordDao.insert(liveRecord);
        return liveRecord;
    }

    /**
     * 修改数据
     *
     * @param liveRecord 实例对象
     * @return 实例对象
     */
    @Override
    public LiveRecord update(LiveRecord liveRecord) {
        this.liveRecordDao.update(liveRecord);
        return this.queryById(liveRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.liveRecordDao.deleteById(id) > 0;
    }

    @Override
    public List<LiveRecord> myRecordtList(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("id") Integer id) {
        return this.liveRecordDao.myRecordtList(offset,limit,id);
    }
}
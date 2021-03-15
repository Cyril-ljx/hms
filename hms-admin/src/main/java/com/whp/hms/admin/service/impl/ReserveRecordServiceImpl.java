package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.ReserveRecordService;
import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.mapper.ReserveRecordDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订房记录(ReserveRecord)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("reserveRecordService")
public class ReserveRecordServiceImpl implements ReserveRecordService {
    @Resource
    private ReserveRecordDao reserveRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReserveRecord queryById(Integer id) {
        return this.reserveRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ReserveRecord> queryAllByLimit(int offset, int limit) {
        return this.reserveRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param reserveRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ReserveRecord insert(ReserveRecord reserveRecord) {
        this.reserveRecordDao.insert(reserveRecord);
        return reserveRecord;
    }

    /**
     * 修改数据
     *
     * @param reserveRecord 实例对象
     * @return 实例对象
     */
    @Override
    public ReserveRecord update(ReserveRecord reserveRecord) {
        this.reserveRecordDao.update(reserveRecord);
        return this.queryById(reserveRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.reserveRecordDao.deleteById(id) > 0;
    }

    @Override
    public List<ReserveRecord> queryAdminList(Integer offset, Integer limit, String nickname, String room) {
        return this.reserveRecordDao.queryAdminList(offset, limit, nickname, room);

    }

    @Override
    public Integer queryAdminCount(String nickname, String room) {
        return this.reserveRecordDao.queryAdminCount(nickname, room);

    }
}
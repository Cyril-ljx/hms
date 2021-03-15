package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.RoomTypeService;
import com.whp.hms.core.entity.RoomType;
import com.whp.hms.mapper.RoomTypeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房间类型(RoomType)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("roomTypeService")
public class RoomTypeServiceImpl implements RoomTypeService {
    @Resource
    private RoomTypeDao roomTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoomType queryById(Integer id) {
        return this.roomTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RoomType> queryAllByLimit(int offset, int limit) {
        return this.roomTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roomType 实例对象
     * @return 实例对象
     */
    @Override
    public RoomType insert(RoomType roomType) {
        this.roomTypeDao.insert(roomType);
        return roomType;
    }

    /**
     * 修改数据
     *
     * @param roomType 实例对象
     * @return 实例对象
     */
    @Override
    public RoomType update(RoomType roomType) {
        this.roomTypeDao.update(roomType);
        return this.queryById(roomType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roomTypeDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryCount() {
        return this.roomTypeDao.queryCount();
    }

    @Override
    public Object queryAll() {
        return this.roomTypeDao.queryAll(null);
    }
}
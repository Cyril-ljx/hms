package com.whp.hms.admin.service.impl;

import com.whp.hms.admin.service.RoomService;
import com.whp.hms.core.entity.Room;
import com.whp.hms.mapper.RoomDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房间：可入住的房间(Room)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomDao roomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Room queryById(Integer id) {
        return this.roomDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Room> queryAllByLimit(int offset, int limit) {
        return this.roomDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room insert(Room room) {
        this.roomDao.insert(room);
        return room;
    }

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    @Override
    public Room update(Room room) {
        this.roomDao.update(room);
        return this.queryById(room.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roomDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryCount(Integer state, Integer use, Integer typeId) {
        return this.roomDao.queryCount(state, use, typeId);
    }

    @Override
    public List<Room> list(Integer offset, Integer limit, Integer state, Integer use, Integer typeId) {
        return this.roomDao.list(offset, limit, state, use, typeId);
    }

    @Override
    public Room queryByRoomNum(String roomNum) {
        return this.roomDao.queryByRoomNum(roomNum);
    }

    @Override
    public Room live(String roomNum) {
        int result = this.roomDao.live(roomNum);
        return result == 0 ? null : this.queryByRoomNum(roomNum);
    }

    @Override
    public Room cancel(String roomNum) {
        int result = this.roomDao.cancel(roomNum);
        return result == 0 ? null : this.queryByRoomNum(roomNum);
    }

    @Override
    public Room stop(String roomNum) {
        int result = this.roomDao.stop(roomNum);
        return result == 0 ? null : this.queryByRoomNum(roomNum);
    }

    @Override
    public Room endClear(String roomNum) {
        int result = this.roomDao.endClear(roomNum);
        return result == 0 ? null : this.queryByRoomNum(roomNum);
    }


}
package com.whp.hms.admin.service;

import com.whp.hms.core.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 房间：可入住的房间(Room)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Room> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Integer queryCount(Integer state, Integer use, Integer typeId);

    List<Room> list(Integer offset, Integer limit, Integer state, Integer use, Integer typeId);

    Room queryByRoomNum(String roomNum);

    /**
     * 入住
     */
    Room live(String roomNum);

    /**
     * 取消订房
     */
    Room cancel(String roomNum);


    /**
     * 退房
     */
    Room stop(String roomNum);

    /**
     * 房间清理结束
     */
    Room endClear(String roomNum);
}
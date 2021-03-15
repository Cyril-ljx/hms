package com.whp.hms.mapper;

import com.whp.hms.core.entity.Room;
import com.whp.hms.core.entity.RoomOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房间：可入住的房间(Room)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomDao {

    /**
     * 通过ID查询单条数据
     * @param id
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 通过ID查询单条数据
     * @param start 房价最低值
     * @param end 房价最高值
     * @return 实例对象
     */
    List<Room> query(@Param("offset") int offset, @Param("limit") int limit, @Param("typeId") Integer typeId , @Param("start") BigDecimal start, @Param("end") BigDecimal end);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Room> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param room 实例对象
     * @return 对象列表
     */
    List<Room> queryAll(Room room);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer queryCount(@Param("state") Integer state, @Param("use") Integer use, @Param("typeId") Integer typeId);

    List<Room> list(@Param("offset") Integer offset, @Param("limit") Integer limit,
                    @Param("state") Integer state, @Param("use") Integer use, @Param("typeId") Integer typeId);

    /**
     * 入住
     */
    int live(@Param("num") String roomNum);

    /**
     * 预定
     */
    int reserve(@Param("num") String roomNum);

    /**
     * 取消订房
     */
    int cancel(@Param("num") String roomNum);


    /**
     * 退房
     */
    int stop(@Param("num") String roomNum);

    /**
     * 房间清理结束
     */
    int endClear(@Param("num") String roomNum);

    Room queryByRoomNum(@Param("roomNum") String roomNum);

    /*
    *批量更新房间状态
     */
    int batchCancel( List<RoomOrder> roomOrderList);
}
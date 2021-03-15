package com.whp.hms.mapper;

import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.RoomOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订房订单：客户预定房间的记录(RoomOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomOrder queryById(Integer id);

    RoomOrder reserveById(@Param("id") Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoomOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roomOrder 实例对象
     * @return 对象列表
     */
    List<RoomOrder> queryAll(RoomOrder roomOrder);

    /**
     * 新增数据
     *
     * @param roomOrder 实例对象
     * @return 影响行数
     */
    int insert(RoomOrder roomOrder);

    /**
     * 修改数据
     *
     * @param roomOrder 实例对象
     * @return 影响行数
     */
    int update(RoomOrder roomOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<RoomOrder> list(@Param("offset") Integer offset, @Param("limit") Integer limit,
                         @Param("state") Integer state, @Param("typeId") Integer typeId,
                         @Param("roomNum") String roomNum, @Param("nickname") String nickname);

    Integer listCount(@Param("state") Integer state, @Param("typeId") Integer typeId,
                      @Param("roomNum") String roomNum, @Param("nickname") String nickname);

    List<RoomOrder> liveList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                             @Param("typeId") Integer typeId, @Param("roomNum") String roomNum,
                             @Param("nickname") String nickname);

    Integer liveListCount(@Param("typeId") Integer typeId, @Param("roomNum") String roomNum,
                          @Param("nickname") String nickname);

    List<RoomOrder> cancelList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                               @Param("typeId") Integer typeId, @Param("roomNum") String roomNum,
                               @Param("nickname") String nickname);

    Integer cancelListCount(@Param("typeId") Integer typeId, @Param("roomNum") String roomNum,
                            @Param("nickname") String nickname);

    /*
     * 获取订单状态为预定状态的订单
     *
     */
    List<RoomOrder> queryAllByState();

    /*
     * 更改订单状态也取消
     */
    int updateRoomOrder(List<RoomOrder> roomOrderList);

    List<RoomOrder> myroomOrdertList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                     @Param("id") Integer id);


}
package com.whp.hms.admin.service;

import com.whp.hms.core.entity.RoomOrder;
import com.whp.hms.core.vo.Response;

import java.util.List;

/**
 * 订房订单：客户预定房间的记录(RoomOrder)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoomOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param roomOrder 实例对象
     * @return 实例对象
     */
    RoomOrder insert(RoomOrder roomOrder);

    /**
     * 修改数据
     *
     * @param roomOrder 实例对象
     * @return 实例对象
     */
    RoomOrder update(RoomOrder roomOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<RoomOrder> list(Integer offset, Integer limit, Integer state, Integer typeId, String roomNum, String nickname);

    Integer listCount(Integer state, Integer typeId, String roomNum, String nickname);

    List<RoomOrder> liveList(Integer offset, Integer limit, Integer typeId, String roomNum, String nickname);

    Integer liveListCount(Integer typeId, String roomNum, String nickname);

    List<RoomOrder> cancelList(Integer offset, Integer limit, Integer typeId, String roomNum, String nickname);

    Integer cancelListCount(Integer typeId, String roomNum, String nickname);

    Response live(String jsonBody);

    Response stop(String jsonBody);

    Response close(String jsonBody);

    Response cancel(String jsonBody);

    Response outDate();
}
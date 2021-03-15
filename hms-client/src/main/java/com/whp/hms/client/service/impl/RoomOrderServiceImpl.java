package com.whp.hms.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.core.entity.Room;
import com.whp.hms.core.entity.RoomOrder;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.core.vo.Response;
import com.whp.hms.mapper.ReserveRecordDao;
import com.whp.hms.mapper.RoomDao;
import com.whp.hms.mapper.RoomOrderDao;
import com.whp.hms.client.service.RoomOrderService;
import com.whp.hms.mapper.RoomTypeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订房订单：客户预定房间的记录(RoomOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("roomOrderService")
public class RoomOrderServiceImpl implements RoomOrderService {
    @Resource
    private RoomOrderDao roomOrderDao;

    @Resource
    private RoomDao roomDao;

    @Resource
    private ReserveRecordDao reserveRecordDao;

    @Resource
    private RoomTypeDao typeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoomOrder queryById(Integer id) {
        return this.roomOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RoomOrder> queryAllByLimit(int offset, int limit) {
        return this.roomOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roomOrder 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Response insert(RoomOrder roomOrder) {
        roomOrder.setState(0);
        Integer id = ShiroUtil.getUserId();
        RoomOrder isReserve = roomOrderDao.reserveById(id);
        if (isReserve == null) {
            roomOrder.setUId(id);
            int result = roomOrderDao.insert(roomOrder);
            if (result == 0) {
                throw new RuntimeException("预定房间失败");
            }
            int room = roomDao.reserve(roomOrder.getRoomNum());
            //修改房间信息
            if (room == 0) {
                throw new RuntimeException("预定失败，房间信息不正确");
            }
            return Response.success("预定成功");
        }
        return Response.error("用户已有订房不允许再订");
    }


    /**
     * 修改数据
     *
     * @param roomOrder 实例对象
     * @return 实例对象
     */
    @Override
    public RoomOrder update(RoomOrder roomOrder) {
        this.roomOrderDao.update(roomOrder);
        return this.queryById(roomOrder.getId());
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roomOrderDao.deleteById(id) > 0;
    }

    @Override
    public List<RoomOrder> myroomOrdertList(Integer offset, Integer limit, Integer id) {
        return this.roomOrderDao.myroomOrdertList(offset, limit, id);
    }

    @Override
    @Transactional
    public Response cancel(String jsonBody) {
        RoomOrder roomOrder = queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
        roomOrder.setState(4);
        roomOrder = update(roomOrder);
        if (roomOrder != null) {
            int room = roomDao.cancel(roomOrder.getRoomNum());
            //修改房间信息
            if (room == 0) {
                throw new RuntimeException("取消订房失败，房间信息不正确");
            }
            //生成订房记录
            ReserveRecord reserveRecord = new ReserveRecord();
            reserveRecord.setClientId(roomOrder.getUId());
            reserveRecord.setCreateTime(DateUtil.getNowTime());
            reserveRecord.setFlag(0);
            reserveRecord.setRoomNum(roomOrder.getRoomNum());
            int result = reserveRecordDao.insert(reserveRecord);
            if (result == 0) {
                throw new RuntimeException("取消订房失败，生成订房记录失败");
            }
            return Response.success(roomOrder);
        }
        return Response.error("取消订房失败");
    }

    @Override
    public Response nowOrder() {
        Integer id = ShiroUtil.getUserId();
        RoomOrder order = roomOrderDao.reserveById(id);
        if (order != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("order", order);
            Room room = roomDao.queryById(order.getRoomId());
            result.put("room", room);
            result.put("type", typeDao.queryById(room.getTypeId()));
            return Response.success(result);
        }
        return Response.error("获取失败");
    }
}
package com.whp.hms.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.RoomOrderService;
import com.whp.hms.core.entity.LiveRecord;
import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.core.entity.Room;
import com.whp.hms.core.entity.RoomOrder;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.core.vo.Response;
import com.whp.hms.mapper.LiveRecordDao;
import com.whp.hms.mapper.ReserveRecordDao;
import com.whp.hms.mapper.RoomDao;
import com.whp.hms.mapper.RoomOrderDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private LiveRecordDao liveRecordDao;

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
    public RoomOrder insert(RoomOrder roomOrder) {
        this.roomOrderDao.insert(roomOrder);
        return roomOrder;
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
    public List<RoomOrder> list(Integer offset, Integer limit, Integer state, Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.list(offset, limit, state, typeId, roomNum, nickname);
    }

    @Override
    public Integer listCount(Integer state, Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.listCount(state, typeId, roomNum, nickname);
    }

    @Override
    public List<RoomOrder> liveList(Integer offset, Integer limit, Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.liveList(offset, limit, typeId, roomNum, nickname);
    }

    @Override
    public Integer liveListCount(Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.liveListCount(typeId, roomNum, nickname);
    }

    @Override
    public List<RoomOrder> cancelList(Integer offset, Integer limit, Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.cancelList(offset, limit, typeId, roomNum, nickname);

    }

    @Override
    public Integer cancelListCount(Integer typeId, String roomNum, String nickname) {
        return this.roomOrderDao.cancelListCount(typeId, roomNum, nickname);
    }

    @Override
    @Transactional
    public Response live(String jsonBody) {
        RoomOrder roomOrder = queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
        roomOrder.setState(1);
        roomOrder = update(roomOrder);
        if (roomOrder != null) {
            int room = roomDao.live(roomOrder.getRoomNum());
            //修改房间信息
            if (room == 0) {
                throw new RuntimeException("入住失败，房间信息不正确");
            }
            //生成订房记录
            ReserveRecord reserveRecord = new ReserveRecord();
            reserveRecord.setClientId(roomOrder.getUId());
            reserveRecord.setCreateTime(DateUtil.getNowTime());
            reserveRecord.setFlag(1);
            reserveRecord.setRoomNum(roomOrder.getRoomNum());
            int result = reserveRecordDao.insert(reserveRecord);
            if (result == 0) {
                throw new RuntimeException("入住失败，生成订房记录失败");
            }
            return Response.success(roomOrder);
        }
        return Response.error("办理入住失败");
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
    @Transactional
    public Response stop(String jsonBody) {
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setId(JSONObject.parseObject(jsonBody).getInteger("id"));
        roomOrder.setState(3);
        roomOrder = update(roomOrder);
        if (roomOrder != null) {
            LiveRecord liveRecord = new LiveRecord();
            liveRecord.setClientId(roomOrder.getUId());
            liveRecord.setLiveTime(roomOrder.getLiveTime());
            liveRecord.setEndTime(DateUtil.getNowTime());
            liveRecord.setRoomNum(roomOrder.getRoomNum());

            Room room = roomDao.queryByRoomNum(roomOrder.getRoomNum());
            int result = roomDao.stop(room.getRoomNum());
            if (result == 0) {
                throw new RuntimeException("房间异常");
            }
            int day = DateUtil.dayDiff(liveRecord.getLiveTime(), liveRecord.getEndTime());
            if (day == -1) {
                throw new RuntimeException("日期异常");
            }
            liveRecord.setTotalPrice(room.getPrice().multiply(new BigDecimal(day)));
            result = liveRecordDao.insert(liveRecord);
            if (result == 0) {
                throw new RuntimeException("生成订房记录失败");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("roomOrder", roomOrder);
            map.put("reserveRecord", liveRecord);
            return Response.success(map);
        }
        return Response.error("取消订房失败");
    }

    @Override
    @Transactional
    public Response close(String jsonBody) {
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        Integer id = jsonObject.getInteger("id");
        String roomNum = jsonObject.getString("roomNum");
        String liveTime = jsonObject.getString("liveTime");
        String now = DateUtil.getNowTime();
        Room room = roomDao.queryByRoomNum(roomNum);
        if (room == null) {
            throw new RuntimeException("结算失败，没有该房间");
        }
        int day = DateUtil.dayDiff(liveTime, now);
        if (day == -1) {
            throw new RuntimeException("结算失败,日期计算错误");
        }
        RoomOrder order = queryById(id);
        order.setState(2);
        int result = roomOrderDao.update(order);
        if (result == 0) {
            throw new RuntimeException("计算失败,订单异常");
        }
        return Response.success(room.getPrice().multiply(new BigDecimal(day)));
    }

    @Override
    @Transactional
    public Response outDate() {
        List<RoomOrder> result = this.roomOrderDao.queryAllByState();
        if(result.isEmpty()){
            return Response.error("无预定状态的订单");
        }
        List<RoomOrder> roomOrderList = null;
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date liveTime;
        Date nowTime;
        for (RoomOrder roomOrder : result) {
            try {
                liveTime = format.parse(roomOrder.getLiveTime());
                nowTime = format.parse(DateUtil.getNowTime());
                day = (nowTime.getTime()- liveTime.getTime() ) / (24 * 60 * 60 * 1000);
                System.out.println(day);
                if (day > 3) {
                    RoomOrder roomOrder1 = new RoomOrder();
                    roomOrder1.setId(roomOrder.getId());
                    roomOrder1.setRoomNum(roomOrder.getRoomNum());
                    roomOrder1.setUId(roomOrder.getUId());
                    //为reserveRecord的create_time
                    roomOrder1.setLiveTime(DateUtil.getNowTime());
                    //为reserveRecord的flag
                    roomOrder1.setState(0);
                    roomOrderList.add(roomOrder1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int count =this.roomOrderDao.updateRoomOrder(roomOrderList);
        if(count!=0) {
            int room=roomDao.batchCancel(roomOrderList);
            if(room == 0){
                throw  new RuntimeException("取消订房失败，房间信息不正确");
            }
        }
        //生成订房记录
        int num = reserveRecordDao.batchInsert(roomOrderList);
        if (num == 0) {
            throw new RuntimeException("取消订房失败，生成订房记录失败");
        }
        return Response.error("更新订单状态失败");
    }
}

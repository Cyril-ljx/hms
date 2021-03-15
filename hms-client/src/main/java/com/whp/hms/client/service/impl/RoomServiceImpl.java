package com.whp.hms.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.hms.core.entity.Room;
import com.whp.hms.core.entity.RoomType;
import com.whp.hms.core.vo.Response;
import com.whp.hms.mapper.RoomDao;
import com.whp.hms.client.service.RoomService;
import com.whp.hms.mapper.RoomTypeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private RoomTypeDao typeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param start 价格开始
     * @param end   价格结束
     * @return 实例对象
     */
    @Override
    public List<Room> query(Integer offset, Integer limit, Integer typeId, BigDecimal start, BigDecimal end) {
        return this.roomDao.query(offset, limit, typeId, start, end);
    }

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
    public Response detail(String jsonBody) {
        JSONObject params = JSON.parseObject(jsonBody);
        Integer id = params.getInteger("id");
        Room room = roomDao.queryById(id);
        if (room.getState() != 0) {
            return Response.error("该房间现在处于非空闲状态(╯‵□′)╯︵┻━┻");
        }
        RoomType roomType = typeDao.queryById(room.getTypeId());
        Map<String, Object> result = new HashMap<>();
        result.put("room", room);
        result.put("type", roomType);
        return Response.success(result);
    }
}
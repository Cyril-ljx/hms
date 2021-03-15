package com.whp.hms.mapper;

import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.core.entity.RoomOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订房记录(ReserveRecord)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface ReserveRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReserveRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ReserveRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param reserveRecord 实例对象
     * @return 对象列表
     */
    List<ReserveRecord> queryAll(ReserveRecord reserveRecord);

    /**
     * 新增数据
     *
     * @param reserveRecord 实例对象
     * @return 影响行数
     */
    int insert(ReserveRecord reserveRecord);

    /**
     * 修改数据
     *
     * @param reserveRecord 实例对象
     * @return 影响行数
     */
    int update(ReserveRecord reserveRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<ReserveRecord> queryAdminList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                       @Param("nickname") String nickname, @Param("room") String room);

    Integer queryAdminCount(@Param("nickname") String nickname, @Param("room") String room);

    List<ReserveRecord> myRecordtList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                      @Param("id") Integer id);

    int batchInsert(List<RoomOrder> roomOrderList);
}
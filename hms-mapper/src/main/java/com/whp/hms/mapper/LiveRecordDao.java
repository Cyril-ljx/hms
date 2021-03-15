package com.whp.hms.mapper;

import com.whp.hms.core.entity.LiveRecord;
import com.whp.hms.core.entity.ReserveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 入住记录(LiveRecord)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface LiveRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LiveRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LiveRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param liveRecord 实例对象
     * @return 对象列表
     */
    List<LiveRecord> queryAll(LiveRecord liveRecord);

    /**
     * 新增数据
     *
     * @param liveRecord 实例对象
     * @return 影响行数
     */
    int insert(LiveRecord liveRecord);

    /**
     * 修改数据
     *
     * @param liveRecord 实例对象
     * @return 影响行数
     */
    int update(LiveRecord liveRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<LiveRecord> queryAdminList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                    @Param("nickname") String nickname, @Param("room") String room);

    Integer queryAdminCount(@Param("nickname") String nickname, @Param("room") String room);

    List<LiveRecord> myRecordtList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                      @Param("id") Integer id);
}
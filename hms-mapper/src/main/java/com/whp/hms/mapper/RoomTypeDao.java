package com.whp.hms.mapper;

import com.whp.hms.core.entity.RoomType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 房间类型(RoomType)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface RoomTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoomType queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RoomType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roomType 实例对象
     * @return 对象列表
     */
    List<RoomType> queryAll(RoomType roomType);

    /**
     * 新增数据
     *
     * @param roomType 实例对象
     * @return 影响行数
     */
    int insert(RoomType roomType);

    /**
     * 修改数据
     *
     * @param roomType 实例对象
     * @return 影响行数
     */
    int update(RoomType roomType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select count(*) from room_type")
    Integer queryCount();

}
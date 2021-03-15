package com.whp.hms.mapper;

import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 前台公告栏(Notice)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface NoticeDao {

    /**
     * 通过ID查询单条数据
     *
     *
     * @return 实例对象
     */
    @Select("select * from notice where is_show=1 limit 1")
    Notice queryByShow();

    Notice queryById(Integer id);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Notice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param notice 实例对象
     * @return 对象列表
     */
    List<Notice> queryAll(Notice notice);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select * from notice order by create_time limit #{offset},#{limit}")
    List<Notice> queryAdminList(Integer offset, Integer limit);

    @Select("select count(*) from notice order by create_time")
    Integer queryAdminCount();

    /*
     *通过主键进行首显
     *
     */
    @Update("update notice set is_show=case when id=#{id} then 1 else 0 end")
    boolean showById(Integer id);
}
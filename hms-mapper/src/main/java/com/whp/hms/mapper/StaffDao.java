package com.whp.hms.mapper;

import com.whp.hms.core.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工：记录在案的员工信息，不参与后台管理系统的使用(Staff)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface StaffDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Staff queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Staff> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param staff 实例对象
     * @return 对象列表
     */
    List<Staff> queryAll(Staff staff);

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int insert(Staff staff);

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int update(Staff staff);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select count(*) from staff")
    Integer queryCount();

    List<Staff> queryStaffList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select count(*) from staff where job_id =#{id}")
    Integer queryByJobId(Integer id);

    @Select("select count(*) from staff where dep_id =#{id}")
    Integer queryByDepId(Integer id);
}
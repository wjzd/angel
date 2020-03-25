package com.yy.dao;

import com.yy.pojo.SysPermissionRole;
import com.yy.pojo.SysPermissionRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysPermissionRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int countByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int deleteByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int insert(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int insertSelective(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    List<SysPermissionRole> selectByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    SysPermissionRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int updateByExampleSelective(@Param("record") SysPermissionRole record, @Param("example") SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int updateByExample(@Param("record") SysPermissionRole record, @Param("example") SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int updateByPrimaryKeySelective(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission_role
     *
     * @mbggenerated Sun Nov 03 11:57:13 CST 2019
     */
    int updateByPrimaryKey(SysPermissionRole record);
}
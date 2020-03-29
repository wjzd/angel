package com.yy.dao;

import com.yy.pojo.AdminUserInfo;
import com.yy.pojo.AdminUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminUserInfoMapper{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int countByExample(AdminUserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByExample(AdminUserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insert(AdminUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insertSelective(AdminUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    List<AdminUserInfo> selectByExample(AdminUserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    AdminUserInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") AdminUserInfo record, @Param("example") AdminUserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExample(@Param("record") AdminUserInfo record, @Param("example") AdminUserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKeySelective(AdminUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adminUserInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKey(AdminUserInfo record);

    List<AdminUserInfo> selectByAdmin(AdminUserInfo adminUserInfo);
}
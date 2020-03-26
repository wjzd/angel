package com.yy.dao;

import com.yy.pojo.Commodity;
import com.yy.pojo.CommodityExample;

import java.awt.*;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int countByExample(CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByExample(CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insert(Commodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insertSelective(Commodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    List<Commodity> selectByExampleWithBLOBs(CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    List<Commodity> selectByExample(CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    Commodity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") Commodity record, @Param("example") CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Commodity record, @Param("example") CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExample(@Param("record") Commodity record, @Param("example") CommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKeySelective(Commodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Commodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table commodity
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKey(Commodity record);

    List<Commodity> selectByCom(Commodity commodity);
}
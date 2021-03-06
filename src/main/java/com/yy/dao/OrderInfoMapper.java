package com.yy.dao;

import com.yy.pojo.OrderInfo;
import com.yy.pojo.OrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int countByExample(OrderInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByExample(OrderInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insert(OrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int insertSelective(OrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    List<OrderInfo> selectByExample(OrderInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    OrderInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKeySelective(OrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectByOrderInfo(OrderInfo orderInfo);
}
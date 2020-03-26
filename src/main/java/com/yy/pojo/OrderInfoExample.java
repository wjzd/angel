package com.yy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public OrderInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNull() {
            addCriterion("buyTime is null");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNotNull() {
            addCriterion("buyTime is not null");
            return (Criteria) this;
        }

        public Criteria andBuytimeEqualTo(Date value) {
            addCriterion("buyTime =", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotEqualTo(Date value) {
            addCriterion("buyTime <>", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThan(Date value) {
            addCriterion("buyTime >", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyTime >=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThan(Date value) {
            addCriterion("buyTime <", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThanOrEqualTo(Date value) {
            addCriterion("buyTime <=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeIn(List<Date> values) {
            addCriterion("buyTime in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotIn(List<Date> values) {
            addCriterion("buyTime not in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeBetween(Date value1, Date value2) {
            addCriterion("buyTime between", value1, value2, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotBetween(Date value1, Date value2) {
            addCriterion("buyTime not between", value1, value2, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuymoneyIsNull() {
            addCriterion("buyMoney is null");
            return (Criteria) this;
        }

        public Criteria andBuymoneyIsNotNull() {
            addCriterion("buyMoney is not null");
            return (Criteria) this;
        }

        public Criteria andBuymoneyEqualTo(Double value) {
            addCriterion("buyMoney =", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyNotEqualTo(Double value) {
            addCriterion("buyMoney <>", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyGreaterThan(Double value) {
            addCriterion("buyMoney >", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("buyMoney >=", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyLessThan(Double value) {
            addCriterion("buyMoney <", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyLessThanOrEqualTo(Double value) {
            addCriterion("buyMoney <=", value, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyIn(List<Double> values) {
            addCriterion("buyMoney in", values, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyNotIn(List<Double> values) {
            addCriterion("buyMoney not in", values, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyBetween(Double value1, Double value2) {
            addCriterion("buyMoney between", value1, value2, "buymoney");
            return (Criteria) this;
        }

        public Criteria andBuymoneyNotBetween(Double value1, Double value2) {
            addCriterion("buyMoney not between", value1, value2, "buymoney");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernumEqualTo(String value) {
            addCriterion("orderNum =", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotEqualTo(String value) {
            addCriterion("orderNum <>", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThan(String value) {
            addCriterion("orderNum >", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThanOrEqualTo(String value) {
            addCriterion("orderNum >=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThan(String value) {
            addCriterion("orderNum <", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThanOrEqualTo(String value) {
            addCriterion("orderNum <=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLike(String value) {
            addCriterion("orderNum like", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotLike(String value) {
            addCriterion("orderNum not like", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumIn(List<String> values) {
            addCriterion("orderNum in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotIn(List<String> values) {
            addCriterion("orderNum not in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumBetween(String value1, String value2) {
            addCriterion("orderNum between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotBetween(String value1, String value2) {
            addCriterion("orderNum not between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrderamountIsNull() {
            addCriterion("orderAmount is null");
            return (Criteria) this;
        }

        public Criteria andOrderamountIsNotNull() {
            addCriterion("orderAmount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderamountEqualTo(Integer value) {
            addCriterion("orderAmount =", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountNotEqualTo(Integer value) {
            addCriterion("orderAmount <>", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountGreaterThan(Integer value) {
            addCriterion("orderAmount >", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderAmount >=", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountLessThan(Integer value) {
            addCriterion("orderAmount <", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountLessThanOrEqualTo(Integer value) {
            addCriterion("orderAmount <=", value, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountIn(List<Integer> values) {
            addCriterion("orderAmount in", values, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountNotIn(List<Integer> values) {
            addCriterion("orderAmount not in", values, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountBetween(Integer value1, Integer value2) {
            addCriterion("orderAmount between", value1, value2, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderamountNotBetween(Integer value1, Integer value2) {
            addCriterion("orderAmount not between", value1, value2, "orderamount");
            return (Criteria) this;
        }

        public Criteria andOrderunitIsNull() {
            addCriterion("orderUnit is null");
            return (Criteria) this;
        }

        public Criteria andOrderunitIsNotNull() {
            addCriterion("orderUnit is not null");
            return (Criteria) this;
        }

        public Criteria andOrderunitEqualTo(Double value) {
            addCriterion("orderUnit =", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitNotEqualTo(Double value) {
            addCriterion("orderUnit <>", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitGreaterThan(Double value) {
            addCriterion("orderUnit >", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitGreaterThanOrEqualTo(Double value) {
            addCriterion("orderUnit >=", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitLessThan(Double value) {
            addCriterion("orderUnit <", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitLessThanOrEqualTo(Double value) {
            addCriterion("orderUnit <=", value, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitIn(List<Double> values) {
            addCriterion("orderUnit in", values, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitNotIn(List<Double> values) {
            addCriterion("orderUnit not in", values, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitBetween(Double value1, Double value2) {
            addCriterion("orderUnit between", value1, value2, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderunitNotBetween(Double value1, Double value2) {
            addCriterion("orderUnit not between", value1, value2, "orderunit");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIsNull() {
            addCriterion("orderPrice is null");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIsNotNull() {
            addCriterion("orderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOrderpriceEqualTo(Double value) {
            addCriterion("orderPrice =", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotEqualTo(Double value) {
            addCriterion("orderPrice <>", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceGreaterThan(Double value) {
            addCriterion("orderPrice >", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("orderPrice >=", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceLessThan(Double value) {
            addCriterion("orderPrice <", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceLessThanOrEqualTo(Double value) {
            addCriterion("orderPrice <=", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIn(List<Double> values) {
            addCriterion("orderPrice in", values, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotIn(List<Double> values) {
            addCriterion("orderPrice not in", values, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceBetween(Double value1, Double value2) {
            addCriterion("orderPrice between", value1, value2, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotBetween(Double value1, Double value2) {
            addCriterion("orderPrice not between", value1, value2, "orderprice");
            return (Criteria) this;
        }

        public Criteria andPaystateIsNull() {
            addCriterion("payState is null");
            return (Criteria) this;
        }

        public Criteria andPaystateIsNotNull() {
            addCriterion("payState is not null");
            return (Criteria) this;
        }

        public Criteria andPaystateEqualTo(Integer value) {
            addCriterion("payState =", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotEqualTo(Integer value) {
            addCriterion("payState <>", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateGreaterThan(Integer value) {
            addCriterion("payState >", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateGreaterThanOrEqualTo(Integer value) {
            addCriterion("payState >=", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateLessThan(Integer value) {
            addCriterion("payState <", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateLessThanOrEqualTo(Integer value) {
            addCriterion("payState <=", value, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateIn(List<Integer> values) {
            addCriterion("payState in", values, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotIn(List<Integer> values) {
            addCriterion("payState not in", values, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateBetween(Integer value1, Integer value2) {
            addCriterion("payState between", value1, value2, "paystate");
            return (Criteria) this;
        }

        public Criteria andPaystateNotBetween(Integer value1, Integer value2) {
            addCriterion("payState not between", value1, value2, "paystate");
            return (Criteria) this;
        }

        public Criteria andComidIsNull() {
            addCriterion("comId is null");
            return (Criteria) this;
        }

        public Criteria andComidIsNotNull() {
            addCriterion("comId is not null");
            return (Criteria) this;
        }

        public Criteria andComidEqualTo(Integer value) {
            addCriterion("comId =", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotEqualTo(Integer value) {
            addCriterion("comId <>", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidGreaterThan(Integer value) {
            addCriterion("comId >", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidGreaterThanOrEqualTo(Integer value) {
            addCriterion("comId >=", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidLessThan(Integer value) {
            addCriterion("comId <", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidLessThanOrEqualTo(Integer value) {
            addCriterion("comId <=", value, "comid");
            return (Criteria) this;
        }

        public Criteria andComidIn(List<Integer> values) {
            addCriterion("comId in", values, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotIn(List<Integer> values) {
            addCriterion("comId not in", values, "comid");
            return (Criteria) this;
        }

        public Criteria andComidBetween(Integer value1, Integer value2) {
            addCriterion("comId between", value1, value2, "comid");
            return (Criteria) this;
        }

        public Criteria andComidNotBetween(Integer value1, Integer value2) {
            addCriterion("comId not between", value1, value2, "comid");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("payTime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("payTime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("payTime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("payTime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("payTime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payTime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("payTime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("payTime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("payTime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("payTime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("payTime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("payTime not between", value1, value2, "paytime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderInfo
     *
     * @mbggenerated do_not_delete_during_merge Thu Mar 26 14:09:35 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table orderInfo
     *
     * @mbggenerated Thu Mar 26 14:09:35 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
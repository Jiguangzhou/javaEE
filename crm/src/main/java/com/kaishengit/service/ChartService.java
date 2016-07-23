package com.kaishengit.service;

import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.SaleMapper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class ChartService {

    @Inject
    private CustomerMapper customerMapper;
    @Inject
    private SaleMapper saleMapper;

    /**
     * 查找本月新增的客户
     * @param start
     * @param end
     * @return
     */
    public Long findNewCustomerCount(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)){
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if (StringUtils.isEmpty(end)){
            end = now.toString("yyyy-MM-dd");
        }
        return customerMapper.findNewCustomer(start,end);
    }

    /**
     * 查找本月完成的交易
     * @param start
     * @param end
     * @return
     */
    public Long findSaleCount(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)){
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if (StringUtils.isEmpty(end)){
            end = now.toString("yyyy-MM-dd");
        }
        return saleMapper.findStateCount(start,end,"交易完成");
    }

    /**
     * 查询本月的交易额
     * @param start
     * @param end
     * @return
     */
    public Long findSaleMoney(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)){
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if (StringUtils.isEmpty(end)){
            end = now.toString("yyyy-MM-dd");
        }
        return saleMapper.findStateMoney(start,end,"交易完成");
    }

    public List<Map<String, Object>> loadPieData(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return saleMapper.countProgress(start,end);
    }

    public List<Map<String, Object>> loadBarData(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return saleMapper.totalUserMoney(start,end);
    }
}

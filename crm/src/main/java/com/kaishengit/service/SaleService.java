package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.SaleMapper;
import com.kaishengit.pojo.Sale;
import com.kaishengit.util.ShiroUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class SaleService {

    @Inject
    private SaleMapper saleMapper;
    @Inject
    private CustomerMapper customerMapper;

    /**
     * 业务列表
     * @param param
     * @return
     */
    public List<Sale> findSaleByParam(Map<String,Object> param) {
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return saleMapper.findByParam(param);
    }

    public Long count() {
        Map<String,Object> param = Maps.newHashMap();
        if(ShiroUtil.isEmployee()) {
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return saleMapper.countByParam(param);
    }

    public Long countByParam(Map<String, Object> param) {
        if (ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return saleMapper.countByParam(param);
    }


    /**
     * 添加业务
     * @param sale
     */
    @Transactional
    public void saveSale(Sale sale) {
        sale.setUserid(ShiroUtil.getCurrentUserID());
        sale.setUsername(ShiroUtil.getCurrentRealName());
        sale.setCustomername(customerMapper.findById(sale.getCustomerid()).getName());
        saleMapper.save(sale);
    }


}

package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.SaleLogMapper;
import com.kaishengit.mapper.SaleMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Sale;
import com.kaishengit.pojo.SaleLog;
import com.kaishengit.util.ShiroUtil;
import org.joda.time.DateTime;
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
    @Inject
    private SaleLogMapper saleLogMapper;
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

        SaleLog saleLog = new SaleLog();
        saleLog.setType(SaleLog.LOG_TYPE_AUTO);
        saleLog.setContext(ShiroUtil.getCurrentRealName() + " 创建了该业务");
        saleLog.setSaleid(sale.getId());
        saleLogMapper.save(saleLog);

    }

    /**
     * 查找所有的客户
     * @return
     */
    public List<Customer> findAllCustomer(Map<String,Object> param) {
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.findAll(param);
    }

    /**
     * 根据客户id查找业务
     * @param customerid
     * @return
     */
    public List<Sale> findSaleByCustomerId(Integer customerid) {
        return saleMapper.findByCustomerId(customerid);
    }

    /**
     * 根据id查找业务
     * @param id
     * @return
     */
    public Sale findSaleById(Integer id) {
        return saleMapper.findById(id);
    }

    /**
     * 根据业务id查找业务日志
     * @param saleid
     * @return
     */
    public List<SaleLog> findSaleLogById(Integer saleid) {
        return saleLogMapper.findBySaleId(saleid);
    }

    /**
     * 保存跟进日志
     * @param saleLog
     */
    public void saveLog(SaleLog saleLog) {
        saleLog.setType(SaleLog.LOG_TYPE_INPUT);
        saleLogMapper.save(saleLog);
        //保存业务的最后跟进时间
        Sale sale = saleMapper.findById(saleLog.getSaleid());
        sale.setLasttime(DateTime.now().toString("yyyy-MM-dd"));
        saleMapper.update(sale);
    }

    /**
     * 修改业务的跟进情况
     * @param id
     * @param progress
     */
    public void ediProgress(Integer id, String progress) {
        Sale sale = saleMapper.findById(id);
        sale.setProgress(progress);
        //修改最后的跟进时间
        sale.setLasttime(DateTime.now().toString("yyyy-MM-dd"));
        //判断跟进进度是否修改为【交易完成】
        if ("交易完成".equals(progress)){
            sale.setSuccesstime(DateTime.now().toString("yyyy-MM-dd"));
        }else{
            sale.setSuccesstime(null);
        }
        saleMapper.update(sale);

        //添加修改跟进的日志
        SaleLog saleLog = new SaleLog();
        saleLog.setType(SaleLog.LOG_TYPE_AUTO);
        saleLog.setContext(ShiroUtil.getCurrentRealName()+"更新跟进进度为:"+progress);
        saleLog.setSaleid(sale.getId());
        saleLogMapper.save(saleLog);
    }

    /**
     * 删除业务(先删除日志，再删除业务)
     * @param id
     */
    public void delSale(Integer id) {
        Sale sale = new Sale();
        if (sale != null){
            //删除日志
            List<SaleLog> saleLogList = saleLogMapper.findBySaleId(id);
            if (!saleLogList.isEmpty()){
                saleLogMapper.del(saleLogList);
            }
        }
        //删除业务
        saleMapper.del(id);
    }
}

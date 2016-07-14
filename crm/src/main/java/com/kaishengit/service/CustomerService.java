package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class CustomerService {

    @Inject
    private CustomerMapper customerMapper;

    /**
     * 客户列表
     * @param param
     * @return
     */
    public List<Customer> findCustomerByParam(Map<String,Object> param){
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.findByParam(param);
    }

    public Long count() {
        if (ShiroUtil.isEmployee()){
            Map<String,Object> param = Maps.newHashMap();
            param.put("userid",ShiroUtil.getCurrentUserID());
            return customerMapper.countByParam(param);
        }
        return customerMapper.count();
    }

    public Long countByParam(Map<String, Object> param) {
        if (ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.countByParam(param);
    }

    /**
     * 查询客户中的所有公司
     * @return
     */
    public Object findAllCompany() {
        return customerMapper.findByType(Customer.CUSTOMER_TYPE_COMPANY);
    }

    /**
     * 保存新客户
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        if (customer.getCompanyid() != null){
            Customer company = customerMapper.findById(customer.getCompanyid());
            customer.setCompanyname(company.getName());
        }
        customer.setUserid(ShiroUtil.getCurrentUserID());
        customerMapper.save(customer);
    }

    public List<Customer> findCompanyByKeyword(String keyword){
        return customerMapper.findCompanyLikeName(keyword);
    }
}

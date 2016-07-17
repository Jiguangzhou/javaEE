package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Customer> findAllCompany() {
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
        customer.setPinyin(Strings.pinyin(customer.getName()));
        customerMapper.save(customer);
    }

    public List<Customer> findCompanyByKeyword(String keyword){
        return customerMapper.findCompanyLikeName(keyword);
    }

    /**
     * 根据ID删除客户(经理权限)
     * @param id
     */
    @Transactional
    public void delById(Integer id) {
        Customer customer = customerMapper.findById(id);
        if (customer != null){
            if (customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
                //如果删除的用户是一个公司，则查询是否有关联客户，如果有将公司名字和Id设置为空
                List<Customer> customerList = customerMapper.findByCompanyId(id);
                for (Customer cust:customerList){
                    cust.setCompanyname(null);
                    cust.setCompanyid(null);

                    customerMapper.update(cust);
                }
            }
            //TODO 删除关联的项目和代办事项
            customerMapper.del(id);
        }
    }

    /**
     * 根据id查找客户
     * @param id
     * @return
     */
    public Customer findCustomerById(Integer id) {
        return customerMapper.findById(id);
    }

    /**
     * 修改客户信息
     * @param customer
     */
    @Transactional
    public void editCustomer(Customer customer) {
        if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
            //找到与他相关的客户并修改名字
            List<Customer> customerList = customerMapper.findByCompanyId(customer.getId());

            for (Customer cust:customerList){
                cust.setCompanyname(customer.getName());
                cust.setCompanyid(customer.getId());
                customerMapper.update(cust);
            }
        }else {
            if (customer.getCompanyid() != null){
                Customer company = customerMapper.findById(customer.getCompanyid());
                customer.setCompanyname(company.getName());
            }
        }
        customer.setPinyin(Strings.pinyin(customer.getName()));
        customerMapper.update(customer);
    }

    /**
     * 根据公司id查询客户
     * @param id
     * @return
     */
    public List<Customer> findCustomerByCompanyId(Integer id) {
        return customerMapper.findByCompanyId(id);
    }

    /**
     * 公开客户
     * @param customer
     */
    public void openCustomer(Customer customer) {
        customer.setUserid(null);
        customerMapper.update(customer);
    }

    /**
     * 推荐客户
     * @param customer
     * @param userid
     */
    public void remCustomer(Customer customer, Integer userid) {
        customer.setUserid(userid);
        customerMapper.update(customer);
    }

    /**
     * 将客户信息生成QrCard
     * @param id
     * @return
     */
    public String qrCard(Integer id) {
        Customer customer = customerMapper.findById(id);
        StringBuilder qrcard = new StringBuilder("MECARD:");
        if(StringUtils.isNotEmpty(customer.getName())) {
            qrcard.append("N:"+customer.getName()+";");
        }
        if(StringUtils.isNotEmpty(customer.getTel())) {
            qrcard.append("TEL:"+customer.getTel()+";");
        }
        if(StringUtils.isNotEmpty(customer.getEmail())) {
            qrcard.append("EMAIL:"+customer.getEmail()+";");
        }
        if(StringUtils.isNotEmpty(customer.getAddress())) {
            qrcard.append("ADR:"+customer.getAddress()+";");
        }
        if(StringUtils.isNotEmpty(customer.getCompanyname())) {
            qrcard.append("ORG:"+customer.getCompanyname()+";");
        }
        qrcard.append(";");

        return qrcard.toString();
    }

}

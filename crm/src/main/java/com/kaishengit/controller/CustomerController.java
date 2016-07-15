package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;
    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("companyList",customerService.findAllCompany());
        return "customer/customerlist";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Customer> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");
        keyword = Strings.toUTF8(keyword);

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyword",keyword);

        List<Customer> customerList = customerService.findCustomerByParam(param);
        Long count = customerService.count();
        Long filterConunt = customerService.countByParam(param);

        return new DataTablesResult<>(draw,customerList,count,filterConunt);
    }

    /**
     * 保存新客户
     * @param customer
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String saveNew(Customer customer){
        customerService.saveCustomer(customer);
        return "success";
    }

    /**
     * 删除客户(经理权限)
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id){
        customerService.delById(id);
        return "success";
    }

    /**
     * 显示所有公司
     * @return
     */
    @RequestMapping(value = "/company.json",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> showAllCompany() {
        return customerService.findAllCompany();
    }


    /**
     * 根据ID查找客户
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id:\\d+}.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> editCustomer(@PathVariable Integer id){
        Customer customer = customerService.findCustomerById(id);
        Map<String,Object> result = Maps.newHashMap();

        if (customer == null){
            result.put("state","error");
            result.put("message","未找到相关客户");
        }else {
            List<Customer> companyList = customerService.findAllCompany();
            result.put("state","success");
            result.put("customer",customer);
            result.put("companyList",companyList);
        }
        return result;
    }

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String edit(Customer customer){
        customerService.editCustomer(customer);
        return "success";
    }
    /**
     * 显示客户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String showCustomer(@PathVariable Integer id, Model model){
        Customer customer = customerService.findCustomerById(id);
        if (customer == null){
            throw new NotFoundException();
        }
        if (customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()){
            throw new ForbiddenException();
        }
        model.addAttribute("customer",customer);
        if (customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
            List<Customer> customerList = customerService.findCustomerByCompanyId(id);
            model.addAttribute("customerList",customerList);
        }
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList",userList);
        return "customer/customerview";
    }

    /**
     * 公开客户
     */
    @RequestMapping(value = "/open/{id:\\d+}",method = RequestMethod.GET)
    public String openCustomer(@PathVariable Integer id) {
        Customer customer = customerService.findCustomerById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.openCustomer(customer);
        return "redirect:/customer/"+id;
    }

    /**
     * 推荐客户
     * @param id
     * @param userid
     * @return
     */
    @RequestMapping(value = "/rem",method = RequestMethod.POST)
    public String remCustomer(Integer id,Integer userid){
        Customer customer = customerService.findCustomerById(id);
        if (customer == null){
            throw new NotFoundException();
        }
        if(customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.remCustomer(customer,userid);
        return "redirect:/customer";
    }

    /**
     * 将客户信息生成二维码
     * @param id
     * @param response
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping(value = "/qrcode/{id:\\d+}.png",method = RequestMethod.GET)
    public void makeQrCode(@PathVariable Integer id,HttpServletResponse response) throws IOException, WriterException {
        String qrCard = customerService.qrCard(id);

        Map<EncodeHintType,String> hints = Maps.newHashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCard, BarcodeFormat.QR_CODE,200,200,hints);

        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"png",outputStream);
        outputStream.flush();
        outputStream.close();
    }
}

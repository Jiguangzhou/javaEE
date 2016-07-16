package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Sale;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Inject
    private SaleService saleService;
    @Inject
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("customerList",customerService.findAllCustomer());
        return "sale/list";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Sale> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);

        List<Sale> saleList = saleService.findSaleByParam(param);
        Long count  = saleService.count();
        Long filter = saleService.countByParam(param);

        return new DataTablesResult<>(draw,saleList,count,filter);
    }

    /**
     * 添加业务
     * @param sale
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String newBusiness(Sale sale){
        saleService.saveSale(sale);
        return "success";
    }
}

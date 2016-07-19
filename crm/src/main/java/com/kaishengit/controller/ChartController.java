package com.kaishengit.controller;

import com.kaishengit.service.ChartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @Inject
    private ChartService chartService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(required = false,defaultValue = "") String start,
                       @RequestParam(required = false,defaultValue = "") String end){
        Long newCutomerCount = chartService.findNewCustomerCount(start,end);
        Long saleCount = chartService.findSaleCount(start,end);
        Long saleMoney = chartService.findSaleMoney(start,end);

        model.addAttribute("newCustomerCount",newCutomerCount);
        model.addAttribute("saleCount",saleCount);
        model.addAttribute("saleMoney",saleMoney);
        return "chart/list";
    }

}

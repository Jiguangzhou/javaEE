package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Book;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/datatable")
public class DataTableController {

    @Inject
    private BookService bookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "datatables/list";
    }

    @RequestMapping(value = "/data.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");//当前页面起始
        String length = request.getParameter("length");//每页显示的数据量
        String keyword = request.getParameter("search[value]");//搜索框中的值
        keyword = Strings.toUTF8(keyword);

        List<Book> bookList = bookService.findByDataTables(start,length,keyword);

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordFiltered",bookService.count());
        result.put("data",bookList);
        return result;
    }
}

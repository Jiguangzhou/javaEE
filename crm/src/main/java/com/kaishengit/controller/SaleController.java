package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Sale;
import com.kaishengit.pojo.SaleDoc;
import com.kaishengit.pojo.SaleLog;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.SaleService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Inject
    private SaleService saleService;
    @Value("${imagePath}")
    private String savePath;
    @Inject
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        Map<String,Object> param = Maps.newHashMap();
        model.addAttribute("customerList",saleService.findAllCustomer(param));
        return "sale/list";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Sale> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        String name = request.getParameter("salename");
        name = Strings.toUTF8(name);
        String progress = request.getParameter("progress");
        progress = Strings.toUTF8(progress);
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("name",name);
        param.put("progress",progress);
        param.put("startdate",startDate);
        param.put("enddate",endDate);
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
    public String saveBusiness(Sale sale){
        saleService.saveSale(sale);
        return "success";
    }

    /**
     * 显示业务详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String viewSale(@PathVariable Integer id,Model model) {
        Sale sale = saleService.findSaleById(id);
        if (sale == null){
            throw new NotFoundException();
        }
        if (!sale.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()){
            return "error/403";
        }
        model.addAttribute("sale",sale);

        //查看跟进的记录
        List<SaleLog> saleLogList = saleService.findSaleLogById(id);
        model.addAttribute(saleLogList);
        //查找业务文件
        List<SaleDoc> saleDocList = saleService.findSaleDocBySaleId(id);
        model.addAttribute(saleDocList);
        return "sale/view";
    }

    /**
     * 保存新的跟进日志
     * @param saleLog
     * @return
     */
    @RequestMapping(value = "/log/add",method = RequestMethod.POST)
    public String saveLog(SaleLog saleLog) {
        saleService.saveLog(saleLog);
        return "redirect:/sale/"+saleLog.getSaleid();
    }

    /**
     * 修改跟进进度
     * @param id
     * @param progress
     * @return
     */
    @RequestMapping(value = "/progress",method = RequestMethod.POST)
    public String editProgress(Integer id,String progress){
        saleService.ediProgress(id,progress);
        return "redirect:/sale/"+id;
    }

    /**
     * 删除业务
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    public String delSale(@PathVariable Integer id){
        saleService.delSale(id);
        return "redirect:/sale";
    }

    /**
     * 上传相关资料
     * @param file
     * @param saleid
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file,Integer saleid) throws IOException {
        saleService.uploadDoc(file.getInputStream(),file.getOriginalFilename(),file.getContentType(),file.getSize(),saleid);
        return "success";
    }

    /**
     * 文件下载
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}/download",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable Integer id) throws UnsupportedEncodingException, FileNotFoundException {
        SaleDoc saleDoc = saleService.findSaleDocById(id);
        if(saleDoc == null) {
            throw new NotFoundException();
        }
        File file = new File(savePath,saleDoc.getFilename());
        if(!file.exists()) {
            throw new NotFoundException();
        }
        FileInputStream inputStream = new FileInputStream(file);
        String fileName = saleDoc.getName();
        fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
        return ResponseEntity.ok().contentLength(saleDoc.getSize()).contentType(MediaType.parseMediaType(saleDoc.getContenttype())).header("Content-Disposition","attachment;filename=\""+fileName+"\"").body(new InputStreamResource(inputStream));

    }

}

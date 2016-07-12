package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Notice;
import com.kaishengit.service.NoticeService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Inject
    private NoticeService noticeService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        return "notice/noticelist";
    }

    /**
     * 发布公告
     * @return
     */
    @RequestMapping(value = "/newnotice",method = RequestMethod.GET)
    public String newNotice(){
        return "notice/newnotice";
    }

    @RequestMapping(value = "/newnotice",method = RequestMethod.POST)
    public String newNotice(Notice notice , RedirectAttributes redirectAttributes){
        noticeService.saveNotice(notice);
        redirectAttributes.addFlashAttribute("message","发布成功");
        return "redirect:/notice";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Notice> loadUser(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");
        keyword = Strings.toUTF8(keyword);

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyword",keyword);

        List<Notice> noticeList = noticeService.findListByParam(param);
        Long count = noticeService.findNoticeCount();
        Long filtercount = noticeService.findCountByParam(param);

        return new DataTablesResult<>(draw,noticeList,count,filtercount);
    }

    /**
     * 根据ID显示公告内容
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String viewNotice(@PathVariable Integer id, Model model){
        Notice notice = noticeService.findNoticeById(id);
        if (notice == null){
            throw new NotFoundException();
        }
        model.addAttribute("notice",notice);
        return "notice/view";
    }
}

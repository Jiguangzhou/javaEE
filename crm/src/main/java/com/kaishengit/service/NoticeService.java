package com.kaishengit.service;

import com.kaishengit.mapper.NoticeMapper;
import com.kaishengit.pojo.Notice;
import com.kaishengit.util.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class NoticeService {

    @Inject
    private NoticeMapper noticeMapper;

    /**
     * 保存新公告
     * @param notice
     */
    public void saveNotice(Notice notice) {
        notice.setUserid(ShiroUtil.getCurrentUserID());
        notice.setRealname(ShiroUtil.getCurrentRealName());
        noticeMapper.save(notice);
        //TODO 微信通知
    }
    /**
     * 根据查询的参数获取公告的列表
     * @param param
     * @return
     */
    public List<Notice> findListByParam(Map<String, Object> param) {
        return noticeMapper.findByParam(param);
    }

    /**
     * 查询公告的总数量
     * @return
     */
    public Long findNoticeCount() {
        return noticeMapper.count();
    }

    /**
     * 根据查询的条件获得公告数量
     * @param param
     * @return
     */
    public Long findCountByParam(Map<String, Object> param) {
        return noticeMapper.countByParam(param);
    }
    
}

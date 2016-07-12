package com.kaishengit.service;

import com.kaishengit.mapper.NoticeMapper;
import com.kaishengit.pojo.Notice;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class NoticeService {

    @Inject
    private NoticeMapper noticeMapper;
    @Value("${imagePath}")
    private String imgSavePath;
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

    /**
     * 根据ID查询公告
     * @param id
     * @return
     */
    public Notice findNoticeById(Integer id) {
        return noticeMapper.findById(id);
    }

    /**
     * 保存在线编辑器中上传的文件
     * @param inputStream
     * @param fileName
     * @return
     */
    public String saveImage(InputStream inputStream, String fileName) throws IOException {
        String newFileName = UUID.randomUUID().toString();
            FileOutputStream outputStream = new FileOutputStream(new File(imgSavePath, newFileName));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        return "/preview/"+newFileName;
    }

}

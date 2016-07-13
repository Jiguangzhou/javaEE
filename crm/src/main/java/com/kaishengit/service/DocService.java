package com.kaishengit.service;

import com.kaishengit.mapper.DocMapper;
import com.kaishengit.pojo.Doc;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Named
public class DocService {

    @Inject
    private DocMapper docMapper;
    @Value("${imagePath}")
    private String savePath;

    public List<Doc> findFileByFid(int fid) {
        return docMapper.findByFid(fid);
    }

    /**
     * 新建文件夹
     * @param name
     * @param fid
     */
    public void saveDir(String name,Integer fid){
        Doc doc = new Doc();
        doc.setName(name);
        doc.setFid(fid);
        doc.setCreateuser(ShiroUtil.getCurrentRealName());
        doc.setType(Doc.TYPE_DIR);
        docMapper.save(doc);
    }

    /**
     * 保存文件
     * @param inputStream 文件输入流
     * @param originalFilename 文件的原始名字
     * @param size 文件大小
     * @param contentType 文件mime类型
     * @param fid 文件级别
     */
    @Transactional
    public void saveFile(InputStream inputStream, String originalFilename, long size, String contentType, Integer fid) {
        String extName = "";
        if (originalFilename.lastIndexOf(".") != -1){
            extName = originalFilename.substring(originalFilename.indexOf("."));
        }
        String newFileName = UUID.randomUUID().toString() + extName;
        try {
            FileOutputStream outputStream = new FileOutputStream(new java.io.File(savePath,newFileName));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Doc doc = new Doc();
        doc.setName(originalFilename);
        doc.setSize(FileUtils.byteCountToDisplaySize(size));
        doc.setType(Doc.TYPE_DOC);
        doc.setCreateuser(ShiroUtil.getCurrentRealName());
        doc.setFilename(newFileName);
        doc.setFid(fid);
        doc.setContexttype(contentType);
        docMapper.save(doc);
    }

    public Doc findFileById(Integer id){
        return docMapper.findById(id);
    }

    public void delDoc(Integer id) {
        docMapper.del(id);
    }
}

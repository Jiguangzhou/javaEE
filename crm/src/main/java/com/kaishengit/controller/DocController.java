package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Doc;
import com.kaishengit.service.DocService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/doc")
public class DocController {

    @Inject
    private DocService docService;
    @Value("${imagePath}")
    private String savePath;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,@RequestParam(required = false,defaultValue = "0") Integer fid){
        List<Doc> docList = docService.findFileByFid(fid);
        model.addAttribute("docList", docList);
        model.addAttribute("fid",fid);
        return "doc/doclist";
    }

    /**
     * 保存文件夹
     * @param name
     * @param fid
     * @return
     */
    @RequestMapping(value = "/newDir",method = RequestMethod.POST)
    public String saveDir(String name,Integer fid){
        docService.saveDir(name,fid);
        return "redirect:/doc?fid="+fid;
    }

    /**
     * 上传文件
     * @param file
     * @param fid
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String saveFile(MultipartFile file,Integer fid) throws IOException {
        if (file.isEmpty()){
            throw new NotFoundException();
        }else {
            docService.saveFile(file.getInputStream(),file.getOriginalFilename(),file.getSize(),file.getContentType(),fid);
        }
        return "success";
    }

    /**
     * 根据ID下载对应文件
     * @param id
     * @return
     */
    @RequestMapping(value = "/download/{id:\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) throws FileNotFoundException, UnsupportedEncodingException {
        Doc doc = docService.findFileById(id);
        if (doc == null){
            throw new NotFoundException();
        }
        File file = new File(savePath,doc.getFilename());
        if (!file.exists()){
            throw new NotFoundException();
        }
        FileInputStream inputStream = new FileInputStream(file);
        String fileName = file.getName();
        fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(doc.getContexttype()))
                .contentLength(file.length())
                .header("Content-Disposition","attachment;filename=\""+fileName+"\"")
                .body(new InputStreamResource(inputStream));

    }
}

package com.kaishengit.service;

import com.kaishengit.dao.DocumentDao;
import com.kaishengit.entity.Document;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class DocumentService {

    private DocumentDao documentDao = new DocumentDao();

    public void updateFile(String fileName,Long size,InputStream inputStream)throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
        String md5 = DigestUtils.md5Hex(byteArrayInputStream);

        Document document = documentDao.findByMd5(md5);
        if (document == null){

            String savaFileName = saveFile(fileName,byteArrayInputStream);

            document = new Document();
            document.setFilename(fileName);
            document.setSavename(savaFileName);
            document.setExtname(fileName.substring(fileName.indexOf(".")));
            document.setMd5(md5);
            document.setSize(size);
            document.setDisplaysize(FileUtils.byteCountToDisplaySize(size));
            documentDao.save(document);
        }


    }
    private String saveFile(String fileName,InputStream inputStream) throws IOException {

        inputStream.reset();
        File dir = new File("E:/opensource");
        if (!dir.exists()) {
            dir.mkdir();
        }

        String extName = fileName.substring(fileName.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + extName;

        FileOutputStream output = new FileOutputStream(new File(dir,fileName));


        IOUtils.copy(inputStream, output);
       /* BufferedInputStream bufferInput = new BufferedInputStream(input);
        BufferedOutputStream bufferOutput = new BufferedOutputStream(output);

        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = bufferInput.read(buffer))!=-1){
            bufferOutput.write(buffer,0,len);
        }**/
        output.flush();
        output.close();
        inputStream.close();

        return fileName;
    }


    public List<Document> findAllDocument() {

        return documentDao.findAll();
    }
    public Document findDocumentByMd5(String md5){
        return documentDao.findByMd5(md5);
    }
}

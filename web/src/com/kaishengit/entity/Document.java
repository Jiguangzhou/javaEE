package com.kaishengit.entity;

public class Document {

    private Integer id;
    private String filename;
    private String savename;
    private String md5;
    private String extname;
    private long size;
    private String displaysize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDisplaysize() {
        return displaysize;
    }

    public void setDisplaysize(String displaysize) {
        this.displaysize = displaysize;
    }

    public boolean isPreview(){
        if (getFilename()==null){
            return false;
        }
        if (getFilename().endsWith(".jpg")
                ||getFilename().endsWith(".png")
                ||getFilename().endsWith(".pdf")
                ||getFilename().endsWith(".bmp")
                ||getFilename().endsWith("jpeg")
                ||getFilename().endsWith(".gif")
                ){
            return true;
        }
            return false;
    }

}

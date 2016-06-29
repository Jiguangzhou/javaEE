package com.kaishengit.pojo;

public class Movie {

    private Integer id;
    private String title;
    private float rate;
    private String releaseyear;
    private String sendtime;
    private String daoyan;
    private String jianjie;
    private Integer userid;
    private Integer nodeid;
    private User user;
    private Node node;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getDaoyan() {
        return daoyan;
    }

    public void setDaoyan(String daoyan) {
        this.daoyan = daoyan;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", releaseyear='" + releaseyear + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", daoyan='" + daoyan + '\'' +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }
}

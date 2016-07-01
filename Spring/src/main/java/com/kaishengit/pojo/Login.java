package com.kaishengit.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Login implements Serializable {

    private Integer id;
    private Timestamp timestamp;
    private String ip;
    private Integer userid;

    public Login() {
    }

    public Login(String ip, Integer userid) {
        this.ip = ip;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", ip='" + ip + '\'' +
                ", userid=" + userid +
                '}';
    }
}

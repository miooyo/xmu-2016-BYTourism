package com.ezz.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.c.I;

/**
 * Created by 37492 on 2016/4/22.
 */
public class Partner extends BmobObject{
    private Integer idone;
    private Integer idtwo;
    private String yuetime;
    private Integer startid;
    private Integer endid;
    private String state;
    private String title;
    private String describe;




    public void setIdone(Integer idone) {
        this.idone = idone;
    }

    public void setIdtwo(Integer idtwo) {
        this.idtwo = idtwo;
    }

    public void setYuetime(String yuetime) {
        this.yuetime = yuetime;
    }

    public void setStartid(Integer startid) {
        this.startid = startid;
    }

    public void setEndid(Integer endid) {
        this.endid = endid;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getIdone() {
        return idone;
    }

    public Integer getIdtwo() {
        return idtwo;
    }

    public String getYuetime() {
        return yuetime;
    }

    public Integer getStartid() {
        return startid;
    }

    public Integer getEndid() {
        return endid;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }


}

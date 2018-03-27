package com.liuss.model.entity.sys;

import com.liuss.model.entity.BaseEntity;

public class Module extends BaseEntity {
    private String name;
    private Integer seq;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

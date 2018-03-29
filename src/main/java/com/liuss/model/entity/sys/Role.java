package com.liuss.model.entity.sys;

import com.liuss.model.entity.BaseEntity;

public class Role extends BaseEntity{
    private String name;
    private String nameEn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}

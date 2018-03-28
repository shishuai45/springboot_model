package com.liuss.model.model.sys;

import java.util.List;

public class Mod_Menu {
    private Integer id;
    private String name;
    private String url;
    private String icon;
    private boolean leaf;
    private boolean expanded;
    private List<Mod_Menu> childrens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Mod_Menu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Mod_Menu> childrens) {
        this.childrens = childrens;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

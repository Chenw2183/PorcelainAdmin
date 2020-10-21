package com.stone.domain;

public class CmMenu {
    private Integer id;
    private String menuName;
    private Integer menuLevel;
    private Integer menuParent;
    private String menuUrl;
    private Integer menuSort;
    private Integer menuEnable;
    private Integer menuDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(Integer menuParent) {
        this.menuParent = menuParent;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getMenuEnable() {
        return menuEnable;
    }

    public void setMenuEnable(Integer menuEnable) {
        this.menuEnable = menuEnable;
    }

    public Integer getMenuDel() {
        return menuDel;
    }

    public void setMenuDel(Integer menuDel) {
        this.menuDel = menuDel;
    }
}

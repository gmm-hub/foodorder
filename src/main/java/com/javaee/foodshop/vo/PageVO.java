package com.javaee.foodshop.vo;

import java.util.List;

/**
 * vo 辅助类（页面中需要的数据）
 */
public class PageVO<T> {

    //数据
    private List<T> list;

    //当前页
    private Integer pageNow;

    //总页数
    private Integer myPages;

    //条件
    private String query;

    public PageVO() {
    }

    public PageVO(List<T> list, Integer pageNow, Integer myPages, String query) {
        this.list = list;
        this.pageNow = pageNow;
        this.myPages = myPages;
        this.query = query;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getMyPages() {
        return myPages;
    }

    public void setMyPages(Integer myPages) {
        this.myPages = myPages;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "list=" + list +
                ", pageNow=" + pageNow +
                ", myPages=" + myPages +
                ", query='" + query + '\'' +
                '}';
    }
}
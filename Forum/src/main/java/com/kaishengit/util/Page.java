package com.kaishengit.util;

import java.util.List;

public class Page<T> {

    private Integer totalSize;

    private Integer totalPages;

    private Integer pageNo;

    private Integer pageSize;

    private List<T> items;

    private Integer start;

    /**
     * @param pageSize  每页显示的数量
     * @param pageNo    当前页码
     * @param totalSize 总记录
     */
    public Page(Integer pageSize, Integer pageNo, Integer totalSize) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalSize = totalSize;

        totalPages = totalSize / pageSize;
        if (totalSize % pageSize != 0) {
            totalPages++;
        }
        if (pageNo > totalPages) {
            this.pageNo = totalPages;
        }
        if (pageNo<=0){
            this.pageNo = 1;
        }
        start = (this.pageNo - 1) * pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getStart() {
        return start;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}



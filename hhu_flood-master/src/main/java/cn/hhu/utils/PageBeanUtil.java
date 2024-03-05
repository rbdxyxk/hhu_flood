package cn.hhu.utils;


import cn.hhu.Bean.Road;

import java.util.List;

public class PageBeanUtil {

    private int prePage; // 上一页
    private int nextPage; // 下一页
    private int firstPage = 1; // 首页
    private int lastPage; // 尾页
    private int currentPage = 1; // 当前页
    private int totalPage; // 总页数
    private int pageSize; // 每页显示数量
    private int totalData; // 数据总条数

    private List<Road> pageData; // 数据

    public PageBeanUtil(int currentPage, int pageSize, int totalData) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalData = totalData;

        //计算获得总页数（尾页）
        this.totalPage = this.lastPage = (totalData % pageSize == 0) ? (totalData / pageSize) : (totalData/pageSize + 1);
//                (totalPage + pageSize - 1) /pageSize;

        //防止当前页小于1
        this.currentPage = Math.max(this.currentPage,1);
        //防止当前页大于总的页数
        this.currentPage = Math.min(this.currentPage,this.totalPage);
        //设置上一页，上一页不能小于1
        this.prePage = Math.max(this.currentPage - 1, 1);
        //设置下一页，下一页不能大于总页数
        this.nextPage = Math.min(this.currentPage + 1,this.totalPage);

    }

    @Override
    public String toString() {
        return "PageBeanUtil{" +
                "prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalData=" + totalData +
                ", pageData=" + pageData +
                '}';
    }

    public List<Road> getPageData() {
        return pageData;
    }

    public void setPageData(List<Road> pageData) {
        this.pageData = pageData;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }
}

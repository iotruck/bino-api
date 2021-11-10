package br.com.iotruck.bino.config;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCustom implements Pageable {

    Integer pageSize;

    public PageableCustom(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}

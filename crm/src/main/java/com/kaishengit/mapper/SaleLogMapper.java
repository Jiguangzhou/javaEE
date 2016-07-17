package com.kaishengit.mapper;

import com.kaishengit.pojo.SaleLog;

import java.util.List;

public interface SaleLogMapper {
    void save(SaleLog saleLog);

    List<SaleLog> findBySaleId(Integer saleid);

    void del(List<SaleLog> saleLogList);
}

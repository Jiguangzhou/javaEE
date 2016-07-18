package com.kaishengit.mapper;

import com.kaishengit.pojo.SaleDoc;

import java.util.List;

public interface SaleDocMapper {
    List<SaleDoc> findBySaleId(Integer saleid);

    SaleDoc findById(Integer id);

    void save(SaleDoc saleDoc);
}

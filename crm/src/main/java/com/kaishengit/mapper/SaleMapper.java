package com.kaishengit.mapper;

import com.kaishengit.pojo.Sale;
import com.kaishengit.pojo.SaleLog;

import java.util.List;
import java.util.Map;

public interface SaleMapper {
    List<Sale> findByParam(Map<String, Object> param);

    Long countByParam(Map<String, Object> param);

    void save(Sale sale);

    List<Sale> findByCustomerId(Integer customerid);

    Sale findById(Integer id);

    void update(Sale sale);

    void del(Integer id);
}

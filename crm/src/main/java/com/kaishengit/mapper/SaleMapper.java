package com.kaishengit.mapper;

import com.kaishengit.pojo.Sale;

import java.util.List;
import java.util.Map;

public interface SaleMapper {
    List<Sale> findByParam(Map<String, Object> param);

    Long countByParam(Map<String, Object> param);

    void save(Sale sale);

}

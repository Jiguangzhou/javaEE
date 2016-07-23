package com.kaishengit.mapper;

import com.kaishengit.pojo.Sale;
import com.kaishengit.pojo.SaleLog;
import org.apache.ibatis.annotations.Param;

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

    Long findStateCount(@Param("start") String start,@Param("end") String end,@Param("state") String state);

    Long findStateMoney(@Param("start") String start,@Param("end") String end,@Param("state") String state);

    List<Map<String,Object>> countProgress(@Param("start") String start,@Param("end") String end);

    List<Map<String,Object>> totalUserMoney(@Param("start") String start,@Param("end") String end);
}

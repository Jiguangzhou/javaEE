package com.kaishengit.mapper;

import com.kaishengit.pojo.Doc;

import java.util.List;

public interface DocMapper {

    List<Doc> findByFid(int fid);

    void save(Doc doc);

    Doc findById(Integer id);

    void del(Integer id);


}

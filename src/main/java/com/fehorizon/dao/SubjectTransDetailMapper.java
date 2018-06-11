package com.fehorizon.dao;

import com.fehorizon.model.SubjectTransDetail;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SubjectTransDetailMapper extends Mapper<SubjectTransDetail> {
    void saveBatchRecords(List<SubjectTransDetail> transDetails);

    void clearTableRecords();
}
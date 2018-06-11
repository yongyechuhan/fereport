package com.fehorizon.dao;

import com.fehorizon.model.SubjectTransDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SubjectTransDetailMapper extends Mapper<SubjectTransDetail> {
    void saveBatchRecords(List<SubjectTransDetail> transDetails);

    void clearTableRecords();

    List<SubjectTransDetail> sumSubjectTransAmts(@Param("fillMer") String fillMer
            , @Param("subjectNames") List subjectNames);

    List<SubjectTransDetail> getUnMatchCellInf(@Param("fillMer") String fillMer
            , @Param("subjectNames") List subjectNames);
}
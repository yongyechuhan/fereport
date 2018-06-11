package com.fehorizon.service;

import com.fehorizon.model.SubjectTransDetail;

import java.util.List;

public interface SubjectTransDetailDaoService {
    /**
     * 批量保存交易明细，保存前需要清空之前的记录
     * @param transDetails
     * @return
     */
    boolean batchSaveTransDetails(List<SubjectTransDetail> transDetails);

    /**
     * 按照填充单位和提供的科目名统计汇总金额
     * @param fillMer
     * @param subjectNames
     * @return
     */
    List<SubjectTransDetail> sumSubjectTransAmts(String fillMer, List<String> subjectNames);

    /**
     * 按照填充单位和提供的科目名查出对应单元格在表中的坐标位置
     * @param fillMer
     * @param subjectNames
     * @return
     */
    List<SubjectTransDetail> getUnMatchCellInf(String fillMer, List<String> subjectNames);
}

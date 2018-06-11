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
}

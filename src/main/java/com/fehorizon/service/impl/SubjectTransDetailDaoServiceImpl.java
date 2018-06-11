package com.fehorizon.service.impl;

import com.fehorizon.dao.SubjectTransDetailMapper;
import com.fehorizon.model.SubjectTransDetail;
import com.fehorizon.service.SubjectTransDetailDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SubjectTransDetailDaoServiceImpl implements SubjectTransDetailDaoService{

    private Logger logger = LoggerFactory.getLogger(SubjectTransDetailDaoServiceImpl.class);

    @Autowired
    private SubjectTransDetailMapper subjectTransDetailMapper;

    @Override
    @Transactional
    public boolean batchSaveTransDetails(List<SubjectTransDetail> transDetails) {
        try {
            // 首先尝试清空之前的所有数据
            subjectTransDetailMapper.clearTableRecords();
            subjectTransDetailMapper.saveBatchRecords(transDetails);

            return true;
        } catch (Exception e) {
            logger.error("批量保存交易明细失败", e);
        }

        return false;
    }
}

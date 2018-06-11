package com.fehorizon.service.impl;

import com.alibaba.fastjson.JSON;
import com.fehorizon.dao.SubjectTransDetailMapper;
import com.fehorizon.model.SubjectTransDetail;
import com.fehorizon.service.SubjectTransDetailDaoService;
import com.fehorizon.utils.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("subjectTransDetailDaoService")
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

//    @Override
//    public List<SubjectTransDetail> selTransDetialByParams(SubjectTransDetail subjectTransDetail) {
//        List<SubjectTransDetail> transDetails = null;
//        try{
//            CriteriaBuilder builder = CriteriaBuilder.instance(subjectTransDetail.getClass());
//            builder.addEq("fillMer", subjectTransDetail.getFillMer());
//            builder.addEq("subjectName", subjectTransDetail.getSubjectName());
//
//            transDetails = subjectTransDetailMapper.selectByExample(builder.getExample());
//        } catch (Exception e) {
//            logger.error("按照条件【{}】查询交易明细失败", JSON.toJSONString(subjectTransDetail), e);
//        }
//        return transDetails;
//    }

    @Override
    public List<SubjectTransDetail> sumSubjectTransAmts(String fillMer, List<String> subjectNames) {
        List<SubjectTransDetail> transDetails = null;
        try{
            transDetails = subjectTransDetailMapper.sumSubjectTransAmts(fillMer, subjectNames);
        } catch (Exception e) {
            logger.error("按照填充单位【{}】和科目名【{}】汇总交易金额失败"
                    , fillMer, JSON.toJSONString(subjectNames), e);
        }
        return transDetails;
    }

    @Override
    public List<SubjectTransDetail> getUnMatchCellInf(String fillMer, List<String> subjectNames) {
        List<SubjectTransDetail> transDetails = null;
        try{
            transDetails = subjectTransDetailMapper.getUnMatchCellInf(fillMer, subjectNames);
        } catch (Exception e){
            logger.error("按照填充单位【{}】和科目名【{}】查询单元格信息失败"
                    , fillMer, JSON.toJSONString(subjectNames), e);
        }
        return transDetails;
    }
}

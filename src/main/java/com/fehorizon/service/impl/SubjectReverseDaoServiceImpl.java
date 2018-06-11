package com.fehorizon.service.impl;

import com.fehorizon.dao.SubjectReverseMapper;
import com.fehorizon.model.SubjectReverse;
import com.fehorizon.service.SubjectReverseDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subjectReverseDaoService")
public class SubjectReverseDaoServiceImpl implements SubjectReverseDaoService{

    private Logger logger = LoggerFactory.getLogger(SubjectReverseDaoServiceImpl.class);

    @Autowired
    private SubjectReverseMapper subjectReverseMapper;

    @Override
    public List<SubjectReverse> selectAllSubjectReverse() {
        List<SubjectReverse> subjectReverses = null;
        try{
            subjectReverses = subjectReverseMapper.selectAll();
        } catch (Exception e) {
            logger.error("获取科目对应关系失败", e);
        }

        return subjectReverses;
    }
}

package com.fehorizon.service.impl;

import com.fehorizon.dao.DeptInfoMapper;
import com.fehorizon.model.DeptInfo;
import com.fehorizon.service.DeptInfoDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptInfoDaoService")
public class DeptInfoDaoServiceImpl implements DeptInfoDaoService{
    private Logger logger = LoggerFactory.getLogger(DeptInfoDaoServiceImpl.class);

    @Autowired
    private DeptInfoMapper deptInfoMapper;

    public List<DeptInfo> getAllDeptInfos(){
        List<DeptInfo> deptInfos = null;
        try{
            deptInfos = deptInfoMapper.selectAll();
        } catch (Exception e) {
            logger.error("获取全部部门信息失败", e);
        }
        return deptInfos;
    }
}

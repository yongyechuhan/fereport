package com.fehorizon.busi;

import com.fehorizon.domain.UnMatchCell;
import com.fehorizon.model.DeptInfo;
import com.fehorizon.model.SubjectTransDetail;
import com.fehorizon.service.SubjectTransDetailDaoService;
import com.fehorizon.tools.cache.DeptInfoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 按照商户科目检查金额是否匹配，完全匹配时，应该抵消为0
 */
public class CheckSubjectTransAmt {

    @Autowired
    private SubjectTransDetailDaoService subjectTransDetailDaoService;

    private Logger logger = LoggerFactory.getLogger(CheckSubjectTransAmt.class);


    public void checkTransAmt(){
        Map<String, DeptInfo> deptInfoMap = DeptInfoCache.getDeptCache();
        if(deptInfoMap == null || deptInfoMap.size() < 1){
            logger.info("部门信息获取失败，退出科目金额匹配检测。");
        }

        for(Map.Entry<String, DeptInfo> entry : deptInfoMap.entrySet()){
            String fillMer = entry.getValue().getDeptName();

            SubjectTransDetail param = new SubjectTransDetail();
            param.setFillMer(fillMer);
            List<SubjectTransDetail> subjectTransDetails = null;
//                    subjectTransDetailDaoService.selTransDetialByParams(param);

            if(subjectTransDetails == null || subjectTransDetails.size() < 1){
                continue;
            }

            searchUnMatchCellInf(subjectTransDetails);
        }
    }

    /**
     * 检查单一科目是否存在双方记录不匹配记录，
     * 如存在不匹配需要记录单元格坐标位置
     */
    private void searchUnMatchCellInf(List<SubjectTransDetail> transDetails){
        Map<String, List<UnMatchCell>> matchCellMap = new HashMap<>();
        for(SubjectTransDetail transDetail : transDetails){
            String subjectName = transDetail.getSubjectName();
            if(matchCellMap.containsKey(subjectName)){

            }
        }
    }
}

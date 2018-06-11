package com.fehorizon.tools.cache;

import com.fehorizon.model.SubjectReverse;
import com.fehorizon.service.SubjectReverseDaoService;
import com.fehorizon.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于缓存科目对应关系
 */
public class SubjectReverseCache {

    private static final Logger logger = LoggerFactory.getLogger(SubjectReverseCache.class);

    private static boolean initFlag = false;

    private static final String DAO_SERVICE_NAME = "subjectReverseDaoService";

    private static final Map<String, String> subjectReverseCache = new HashMap<>();

    private synchronized static void init() {
        if(initFlag){
            return;
        }

        try{
            SubjectReverseDaoService subjectReverseDaoService =
                    (SubjectReverseDaoService) ApplicationContextUtil.getBean(DAO_SERVICE_NAME);

            if(subjectReverseDaoService == null){
                logger.error("科目对应关系初始化失败");
                return;
            }

            List<SubjectReverse> subjectReverses =
                    subjectReverseDaoService.selectAllSubjectReverse();
            if(subjectReverses == null || subjectReverses.size() < 1){
                return;
            }

            for(SubjectReverse subjectReverse : subjectReverses){
                subjectReverseCache.put(subjectReverse.getSubject(), subjectReverse.getReverseSubject());
            }

            initFlag = true;
        } catch (Exception e) {
            logger.error("初始化部门信息失败", e);
        }
    }

    public static Map<String, String> getSubjectReverseCache(){
        if(!initFlag){
            init();
        }
        return subjectReverseCache;
    }

    /**
     * 根据科目获取对应抵消的科目名
     * @return
     */
    public static String getReverseSubject(String subject){
        if(!initFlag){
            init();
        }
        if(subjectReverseCache == null || subjectReverseCache.size() < 1){
            return null;
        }
        if(subjectReverseCache.containsKey(subject)){
            return subjectReverseCache.get(subject);
        }
        return null;
    }
}

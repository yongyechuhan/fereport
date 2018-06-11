package com.fehorizon.tools.cache;

import com.fehorizon.model.DeptInfo;
import com.fehorizon.service.DeptInfoDaoService;
import com.fehorizon.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于缓存公司信息，暂时不考虑数据定时刷新
 */
public class DeptInfoCache {

    private static final Logger logger = LoggerFactory.getLogger(DeptInfoCache.class);

    private static boolean initFlag = false;

    private static final String DAO_SERVICE_NAME = "deptInfoDaoService";

    private static final Map<String, DeptInfo> deptCache = new HashMap<>();

    private synchronized static void init() {
        if(initFlag){
            return;
        }

        try{
            DeptInfoDaoService deptInfoDaoService =
                    (DeptInfoDaoService) ApplicationContextUtil.getBean(DAO_SERVICE_NAME);

            if(deptInfoDaoService == null){
                logger.error("部门信息数据接口初始化失败");
                return;
            }

            List<DeptInfo> deptInfos = deptInfoDaoService.getAllDeptInfos();
            if(deptInfos == null || deptInfos.size() < 1){
                return;
            }

            for(DeptInfo deptInfo : deptInfos){
                deptCache.put(deptInfo.getSheetName(), deptInfo);
            }

            initFlag = true;
        } catch (Exception e) {
            logger.error("初始化部门信息失败", e);
        }
    }

    public static Map<String, DeptInfo> getDeptCache(){
        if(!initFlag){
            init();
        }
        return deptCache;
    }
}

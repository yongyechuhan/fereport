package com.fehorizon.busi;

import com.fehorizon.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *  基础表格解析器，用于处理公共解析部份。
 */
@Service
public class ExcelParseBusi {

    private Logger logger = LoggerFactory.getLogger(ExcelParseBusi.class);

    @Value("#{sysConfig.reportFilePath}")
    protected String reportFilePath;

    private static Workbook workbook;

    private static boolean initFlag = false;

    private synchronized void initWorkbook(){
        if(initFlag){
            return;
        }

        if(StringUtils.isEmpty(reportFilePath)){
            logger.error("载入文件路径为空");
            return;
        }

        workbook = ExcelUtil.loadExcel(reportFilePath);
        if(workbook != null){
            initFlag = true;
        }
    }

    protected Sheet loadSheetWithName(String sheetName) throws Exception {
        if(!initFlag){
            initWorkbook();
        }
        if(workbook != null)
            return workbook.getSheet(sheetName);
        else
            throw new Exception("workbook init error");
    }
}

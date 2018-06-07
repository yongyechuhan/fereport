package com.fehorizon.busi;

import com.fehorizon.task.ExcelUtil;
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

    protected Sheet loadSheetWithName(String sheetName){
        if(StringUtils.isEmpty(reportFilePath)){
            logger.error("载入文件路径为空");
            return null;
        }
        Workbook workbook = ExcelUtil.loadExcel(reportFilePath);
        if(workbook == null){
            return null;
        }
        return workbook.getSheet(sheetName);
    }
}

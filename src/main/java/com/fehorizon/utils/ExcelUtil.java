package com.fehorizon.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * 用于解析Excel文档
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String EXCEL_XLS = "xls";

    private static final String EXCEL_XLSX = "xlsx";

    public static Workbook loadExcel(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            logger.error("获取指定路径【{}】文件失败", filePath);
            return null;
        }

        Workbook workbook = null;
        String fileName = file.getName();
        try{
            FileInputStream fis = new FileInputStream(file);

            if(fileName.endsWith(EXCEL_XLS)){
                workbook = new HSSFWorkbook(fis);
            } else if(fileName.endsWith(EXCEL_XLSX)){
                workbook = new XSSFWorkbook(fis);
            } else {
                logger.error("文件【{}】的类型非excel,不能处理。");
            }
        } catch (Exception e) {
            logger.error("解析文件【{}】失败", fileName, e);
        }
        return workbook;
    }
}

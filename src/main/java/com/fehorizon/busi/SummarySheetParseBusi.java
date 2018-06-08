package com.fehorizon.busi;

import com.alibaba.fastjson.JSON;
import com.fehorizon.model.TestModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于处理sheet :summary的解析处理
 */
@Service
public class SummarySheetParseBusi extends ExcelParseBusi{
    private Logger logger = LoggerFactory.getLogger(SummarySheetParseBusi.class);

    // 商户各科目交易汇总金额
    public Map<String, List<TestModel>> transAmtMap = new HashMap<>();

    private static final String sheetName = "Summary";

    public void parseSheet(){
        Sheet sheet = loadSheetWithName(sheetName);
        if(sheet == null){
            logger.error("加载汇总表格【{}】失败", sheetName);
        }

        int maxRow = sheet.getLastRowNum();
        int columnNum = 6;

        // 从第2行开始读取数据
        for(int i = 2; i < maxRow; i++){
            Row row = sheet.getRow(i);
            int columnMax = row.getLastCellNum();

            List<TestModel> testModels;
            int columnStartIndex = 0;
            while(columnStartIndex < columnMax){
                for(int j = 1; j < columnNum; j++){
                    Cell cell =  row.getCell(columnStartIndex + j);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    }
                }
                String fillMer = getCellValue(row.getCell(columnStartIndex + 1));
                String otherMer = getCellValue(row.getCell(columnStartIndex + 2));
                String subjectName = getCellValue(row.getCell(columnStartIndex + 3));
                String transAmt = getCellValue(row.getCell(columnStartIndex + 4));
                String decideNum = getCellValue(row.getCell(columnStartIndex + 5));

                //如果读出填报单位,对方单位科目名为空或金额为0，进行下一个6列计算
                if(StringUtils.isEmpty(fillMer) || StringUtils.isEmpty(otherMer)
                        || StringUtils.isEmpty(subjectName) || "0".equals(transAmt)){
                    columnStartIndex += columnNum;
                    continue;
                }

                if(transAmtMap.containsKey(fillMer)){
                    testModels = transAmtMap.get(fillMer);
                } else {
                    testModels = new ArrayList<>();
                    transAmtMap.put(fillMer, testModels);
                }

                TestModel testModel = new TestModel();
                testModel.setFillMer(fillMer);
                testModel.setOtherMer(otherMer);
                testModel.setSubjectName(subjectName);
                //金额需要做保留两位数处理
                BigDecimal bigDecimal = new BigDecimal(transAmt);
                bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                testModel.setTransAmt(String.valueOf(transAmt));
                testModel.setDecideNum(decideNum);
                testModels.add(testModel);

                columnStartIndex+=6;
            }
        }

        logger.info(JSON.toJSONString(transAmtMap));
    }

    private String getCellValue(Cell cell){
        if(cell == null) return null;
        if(cell.getCellType() == Cell.CELL_TYPE_STRING)
            return cell.getStringCellValue();
        else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
            return String.valueOf(cell.getNumericCellValue());
        else
            return String.valueOf(cell.getStringCellValue());
    }
}

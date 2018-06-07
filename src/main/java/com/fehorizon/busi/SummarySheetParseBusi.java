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
        int columnStartIndex = 0;

        // 从第2行开始读取数据
        for(int i = 2; i < maxRow; i++){
            Row row = sheet.getRow(i);
            int columnMax = row.getLastCellNum();

            List<TestModel> testModels = null;
            while(columnStartIndex < columnMax){
                String fillMer = row.getCell(columnStartIndex + 1).getStringCellValue();
                for(int j = 0; j < columnNum; j++){
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                }

               String otherMer = row.getCell(columnStartIndex + 2).getStringCellValue();

                //如果读出填报单位或对方单位为空，进行下一个单位
                if(StringUtils.isEmpty(fillMer)){
                    columnStartIndex += columnNum;
                    continue;
                }

                if(transAmtMap.containsKey(fillMer)){
                    testModels = transAmtMap.get(fillMer);
                } else {
                    testModels = new ArrayList<>();
                    transAmtMap.put(fillMer, testModels);
                }

                String subjectName = row.getCell(columnStartIndex + 3).getStringCellValue();
                String transAmt = row.getCell(columnStartIndex + 4).getStringCellValue();
                String decideNum = row.getCell(columnStartIndex + 5).getStringCellValue();

                TestModel testModel = new TestModel();
                testModel.setFillMer(fillMer);
                testModel.setOtherMer(otherMer);
                testModel.setSubjectName(subjectName);
                testModel.setTransAmt(String.valueOf(transAmt));
                testModel.setDecideNum(decideNum);
                testModels.add(testModel);

                columnStartIndex+=6;
                break;
            }
        }

        logger.info(JSON.toJSONString(transAmtMap));
    }
}

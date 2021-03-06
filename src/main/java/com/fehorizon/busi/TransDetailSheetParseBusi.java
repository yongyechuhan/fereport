package com.fehorizon.busi;

import com.fehorizon.model.DeptInfo;
import com.fehorizon.model.SubjectTransDetail;
import com.fehorizon.service.SubjectTransDetailDaoService;
import com.fehorizon.tools.cache.DeptInfoCache;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  用于解析各科目账务明细表格的解析器
 */
@Service("transDetailSheetParseBusi")
public class TransDetailSheetParseBusi extends ExcelParseBusi{
    private Logger logger = LoggerFactory.getLogger(TransDetailSheetParseBusi.class);

    @Autowired
    private SubjectTransDetailDaoService subjectTransDetailDaoService;

    public void parseTransDetailSheet(){
        long parseBeginTime = System.currentTimeMillis();

        //首先获取需要解析的所有部门
        Map<String, DeptInfo> deptInfs = DeptInfoCache.getDeptCache();

        if(deptInfs == null || deptInfs.size() < 1){
            logger.error("部门信息获取失败，退出科目账务明细表格解析");
            return;
        }

        int deptNums = deptInfs.size();
        // 用于汇总所有部门的账户交易汇总
        List<SubjectTransDetail> transDetails = new ArrayList<>();
        for(String sheetName : deptInfs.keySet()){
            try {
                List<SubjectTransDetail> deptTransDetails = parseSheet(sheetName);
                if(transDetails != null)
                    transDetails.addAll(deptTransDetails);
                logger.info("已解析表格【{}】,还剩余【{}】个表格等待解析", sheetName, --deptNums);
            } catch (Exception e) {
                logger.error("解析部门【{}】交易明细失败", sheetName, e);
                return;
            }
        }

        // 数据库储存
        boolean saveRes =
                subjectTransDetailDaoService.batchSaveTransDetails(transDetails);
        logger.info("交易明细表格解析完毕，解析结果【{}】，解析时间【{}】s",
                saveRes, (System.currentTimeMillis() - parseBeginTime) / 1000);
    }

    /**
     * 按照sheet名分别解析各部门的交易
     * @param sheetName
     * @return 数据库储存格式的交易数据
     */
    private List<SubjectTransDetail> parseSheet(String sheetName) throws Exception {
        List<SubjectTransDetail> deptTransDetails = new ArrayList<>();

        Sheet sheet = loadSheetWithName(sheetName);
        if(sheet == null) return null;

        int rowMax = sheet.getLastRowNum();
        for(int i = 2; i < rowMax; i++){
            Row row = sheet.getRow(i);

            if(row == null) continue;

            for(int j = 0; j < 8; j++){
                Cell cell = row.getCell(j);
                if(cell != null) cell.setCellType(Cell.CELL_TYPE_STRING);
            }

            String fillMer = getCellValue(row.getCell(1));
            String otherMer = getCellValue(row.getCell(2));
            String reportType = getCellValue(row.getCell(3));
            String subjectName = getCellValue(row.getCell(4));
            String transAmt = getCellValue(row.getCell(5));
            String subPlatForm = getCellValue(row.getCell(7));

            if(StringUtils.isEmpty(fillMer) || StringUtils.isEmpty(otherMer)
                    || StringUtils.isEmpty(reportType) || StringUtils.isEmpty(subjectName)
                    || StringUtils.isEmpty(transAmt) || "0".equals(transAmt)){
                continue;
            }

            SubjectTransDetail transDetail = new SubjectTransDetail();
            transDetail.setFillMer(fillMer);
            transDetail.setOtherMer(otherMer);
            transDetail.setSubjectType(reportType);
            transDetail.setSubjectName(subjectName);

            String symbol = "";
            if(transAmt.indexOf("(") > -1) {
                symbol = "-";
                transAmt = transAmt.replace("(", "").replace(")", "");
            }
            BigDecimal bigDecimal = new BigDecimal(transAmt);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            transDetail.setTransAmt(symbol.concat(bigDecimal.toString()));
            transDetail.setSubPlatform(subPlatForm);
            transDetail.setRowNum(i);
            transDetail.setSheetName(sheetName);

            deptTransDetails.add(transDetail);
        }
        return deptTransDetails;
    }

    private String getCellValue(Cell cell){
        if(cell == null) return null;
        else
            return cell.getStringCellValue();
    }
}

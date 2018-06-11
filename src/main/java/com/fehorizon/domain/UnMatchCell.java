package com.fehorizon.domain;

/**
 *  用于记录不满足金额匹配的单元格坐标位置
 */
public class UnMatchCell {
    /**
     * 表格名
     */
    private String sheetName;

    /**
     * 行
     */
    private int rowNum;

    /**
     * 列
     */
    private int columnNum;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
}

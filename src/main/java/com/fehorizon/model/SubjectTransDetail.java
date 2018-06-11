package com.fehorizon.model;

import javax.persistence.*;

@Table(name = "subject_trans_detail")
public class SubjectTransDetail {
    /**
     * 填报单位
     */
    @Column(name = "fill_mer")
    private String fillMer;

    /**
     * 对方单位
     */
    @Column(name = "other_mer")
    private String otherMer;

    /**
     * 科目类型
     */
    @Column(name = "subject_type")
    private String subjectType;

    /**
     * 科目名称
     */
    @Column(name = "subject_name")
    private String subjectName;

    /**
     * 交易金额
     */
    @Column(name = "trans_amt")
    private String transAmt;

    /**
     * 对方子平台
     */
    @Column(name = "sub_platform")
    private String subPlatform;

    /**
     * 行数
     */
    @Column(name = "row_num")
    private Integer rowNum;

    /**
     * 表格名
     */
    @Column(name = "sheet_name")
    private String sheetName;

    /**
     * 获取填报单位
     *
     * @return fill_mer - 填报单位
     */
    public String getFillMer() {
        return fillMer;
    }

    /**
     * 设置填报单位
     *
     * @param fillMer 填报单位
     */
    public void setFillMer(String fillMer) {
        this.fillMer = fillMer;
    }

    /**
     * 获取对方单位
     *
     * @return other_mer - 对方单位
     */
    public String getOtherMer() {
        return otherMer;
    }

    /**
     * 设置对方单位
     *
     * @param otherMer 对方单位
     */
    public void setOtherMer(String otherMer) {
        this.otherMer = otherMer;
    }

    /**
     * 获取科目类型
     *
     * @return subject_type - 科目类型
     */
    public String getSubjectType() {
        return subjectType;
    }

    /**
     * 设置科目类型
     *
     * @param subjectType 科目类型
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * 获取科目名称
     *
     * @return subject_name - 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置科目名称
     *
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取交易金额
     *
     * @return trans_amt - 交易金额
     */
    public String getTransAmt() {
        return transAmt;
    }

    /**
     * 设置交易金额
     *
     * @param transAmt 交易金额
     */
    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    /**
     * 获取对方子平台
     *
     * @return sub_platform - 对方子平台
     */
    public String getSubPlatform() {
        return subPlatform;
    }

    /**
     * 设置对方子平台
     *
     * @param subPlatform 对方子平台
     */
    public void setSubPlatform(String subPlatform) {
        this.subPlatform = subPlatform;
    }

    /**
     * 获取行数
     *
     * @return row_num - 行数
     */
    public Integer getRowNum() {
        return rowNum;
    }

    /**
     * 设置行数
     *
     * @param rowNum 行数
     */
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * 获取表格名
     *
     * @return sheet_name - 表格名
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * 设置表格名
     *
     * @param sheetName 表格名
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
package com.fehorizon.model;

import javax.persistence.*;

@Table(name = "dept_info")
public class DeptInfo {
    @Id
    private Long id;

    /**
     * 集团编号
     */
    @Column(name = "dept_no")
    private Integer deptNo;

    /**
     * 集团名
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 对应sheet表格名
     */
    @Column(name = "sheet_name")
    private String sheetName;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取集团编号
     *
     * @return dept_no - 集团编号
     */
    public Integer getDeptNo() {
        return deptNo;
    }

    /**
     * 设置集团编号
     *
     * @param deptNo 集团编号
     */
    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * 获取集团名
     *
     * @return dept_name - 集团名
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置集团名
     *
     * @param deptName 集团名
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取对应sheet表格名
     *
     * @return sheet_name - 对应sheet表格名
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * 设置对应sheet表格名
     *
     * @param sheetName 对应sheet表格名
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
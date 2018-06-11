package com.fehorizon.model;

import javax.persistence.*;

@Table(name = "subject_reverse")
public class SubjectReverse {
    @Id
    private Integer id;

    /**
     * 科目
     */
    private String subject;

    /**
     * 对应抵消科目
     */
    @Column(name = "reverse_subject")
    private String reverseSubject;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取科目
     *
     * @return subject - 科目
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置科目
     *
     * @param subject 科目
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取对应抵消科目
     *
     * @return reverse_subject - 对应抵消科目
     */
    public String getReverseSubject() {
        return reverseSubject;
    }

    /**
     * 设置对应抵消科目
     *
     * @param reverseSubject 对应抵消科目
     */
    public void setReverseSubject(String reverseSubject) {
        this.reverseSubject = reverseSubject;
    }
}
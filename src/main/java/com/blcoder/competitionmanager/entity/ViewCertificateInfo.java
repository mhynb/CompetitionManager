package com.blcoder.competitionmanager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewCertificateInfo implements Serializable {
    private String teamId;
    private String teamName;
    private String studentName;
    private String studentSchool;
    private String teacherName;
    private String teacherSchool;
}

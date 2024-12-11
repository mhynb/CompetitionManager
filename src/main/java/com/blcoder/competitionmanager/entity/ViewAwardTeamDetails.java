package com.blcoder.competitionmanager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewAwardTeamDetails implements Serializable {
    private String rank;
    private String trackType;
    private String awardRank;
    private String teamId;
    private String teamName;
    private String problemName;
    private String school;
    private String studentName;
    private String role;
    private String teacherName;
    private String teacherSchool;
}

package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    @TableId
    private String studentId;
    private String studentName;
    private String sex;
    private String studentTel;
    private String studentSchool;
    private String roles;
    private String teamId;
}

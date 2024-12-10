package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {
    @TableId
    private String number;
    private String teamId;
    private String teacherName;
    private String teacherTel;
    private String teacherSchool;
}

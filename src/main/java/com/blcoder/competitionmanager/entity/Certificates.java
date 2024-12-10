package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class Certificates implements Serializable {
    @TableId
    private String certificatesId;
    private String trackType;
    private String finalRank;
    private String teamSchool;
    private String studentName;
    private String studentSchool;
    private String teacherName;
    private String teacherSchool;
    private String teamId;
}

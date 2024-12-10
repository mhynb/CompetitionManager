package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Team implements Serializable {
    @TableId
    private String teamId;
    private String teamName;
    private String teamSchool;
}

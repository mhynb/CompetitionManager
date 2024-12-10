package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Records implements Serializable {
    @TableId
    private String recordsNumber;
    private String teamId;
    private String expertId;
    private String stage;
    private String score;
}

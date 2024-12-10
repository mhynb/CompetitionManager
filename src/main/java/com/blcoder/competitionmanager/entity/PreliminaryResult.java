package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class PreliminaryResult implements Serializable {
    @TableId
    private String teamId;
    private String preliminaryScore;
    private String preliminaryRank;
}

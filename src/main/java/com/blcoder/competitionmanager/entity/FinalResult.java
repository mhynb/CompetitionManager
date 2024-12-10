package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class FinalResult implements Serializable {
    @TableId
    private String teamId;
    private String finalScore;
    private String finalRank;
}

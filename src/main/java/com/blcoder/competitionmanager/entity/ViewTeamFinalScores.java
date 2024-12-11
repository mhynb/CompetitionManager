package com.blcoder.competitionmanager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewTeamFinalScores implements Serializable {
    private String teamId;
    private String teamName;
    private String problemName;
    private String finalAverageScore;
}

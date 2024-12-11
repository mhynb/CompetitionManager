package com.blcoder.competitionmanager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViewTeamAwardStatus implements Serializable {
    private String teamId;
    private Integer awardStatus;
}

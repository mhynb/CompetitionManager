package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Expert implements Serializable {
    @TableId
    private String expertId;
    private String expertName;
    private String expertTel;
    private String expertEmail;
    private String workplace;
    private String jobTitle;
    private String researchInterest;
}

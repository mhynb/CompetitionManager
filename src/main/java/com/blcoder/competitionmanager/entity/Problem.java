package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Problem implements Serializable {
    @TableId
    private String problemNumber;
    private String problemName;
    private String questionCompany;
    private String trackType;
}

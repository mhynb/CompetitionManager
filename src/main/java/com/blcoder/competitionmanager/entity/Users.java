package com.blcoder.competitionmanager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

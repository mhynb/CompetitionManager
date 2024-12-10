package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.Teacher;
import com.blcoder.competitionmanager.mapper.TeacherMapper;
import com.blcoder.competitionmanager.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}

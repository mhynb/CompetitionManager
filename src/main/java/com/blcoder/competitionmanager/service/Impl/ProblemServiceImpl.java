package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.Problem;
import com.blcoder.competitionmanager.mapper.ProblemMapper;
import com.blcoder.competitionmanager.service.ProblemService;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
}

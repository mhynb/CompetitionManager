package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.TeamProblem;
import com.blcoder.competitionmanager.mapper.TeamProblemMapper;
import com.blcoder.competitionmanager.service.TeamProblemService;
import org.springframework.stereotype.Service;

@Service
public class TeamProblemServiceImpl extends ServiceImpl<TeamProblemMapper, TeamProblem> implements TeamProblemService {
}

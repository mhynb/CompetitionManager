package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.Expert;
import com.blcoder.competitionmanager.mapper.ExpertMapper;
import com.blcoder.competitionmanager.service.ExpertService;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements ExpertService {
}

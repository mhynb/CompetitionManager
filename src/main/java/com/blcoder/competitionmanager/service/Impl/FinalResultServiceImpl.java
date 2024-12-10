package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.FinalResult;
import com.blcoder.competitionmanager.mapper.FinalResultMapper;
import com.blcoder.competitionmanager.service.FinalResultService;
import org.springframework.stereotype.Service;

@Service
public class FinalResultServiceImpl extends ServiceImpl<FinalResultMapper, FinalResult> implements FinalResultService {
}

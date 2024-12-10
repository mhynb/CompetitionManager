package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.Records;
import com.blcoder.competitionmanager.mapper.RecordsMapper;
import com.blcoder.competitionmanager.service.RecordsService;
import org.springframework.stereotype.Service;

@Service
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements RecordsService {
}

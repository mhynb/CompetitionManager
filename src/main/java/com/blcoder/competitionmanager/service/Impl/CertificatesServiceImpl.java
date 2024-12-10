package com.blcoder.competitionmanager.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blcoder.competitionmanager.entity.Certificates;
import com.blcoder.competitionmanager.mapper.CertificatesMapper;
import com.blcoder.competitionmanager.service.CertificatesService;
import org.springframework.stereotype.Service;

@Service
public class CertificatesServiceImpl extends ServiceImpl<CertificatesMapper, Certificates> implements CertificatesService {
}

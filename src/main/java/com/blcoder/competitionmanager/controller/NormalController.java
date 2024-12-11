package com.blcoder.competitionmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blcoder.competitionmanager.common.R;
import com.blcoder.competitionmanager.entity.*;
import com.blcoder.competitionmanager.service.ViewAwardTeamDetailsService;
import com.blcoder.competitionmanager.service.ViewCertificateInfoService;
import com.blcoder.competitionmanager.service.ViewTeamAwardStatusService;
import com.blcoder.competitionmanager.service.ViewTeamFinalScoresService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/normal")
public class NormalController {
    @Autowired
    private ViewTeamAwardStatusService viewTeamAwardStatusService;

    @Autowired
    private ViewTeamFinalScoresService viewTeamFinalScoresService;

    @Autowired
    private ViewAwardTeamDetailsService viewAwardTeamDetailsService;

    @Autowired
    private ViewCertificateInfoService viewCertificateInfoService;

    @GetMapping("/getAwardsStatus")
    public R<Page> getAwardsStatus(int page, int pageSize){
        Page<ViewTeamAwardStatus> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ViewTeamAwardStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ViewTeamAwardStatus::getTeamId);
        viewTeamAwardStatusService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getFinalScores")
    public R<Page> getFinalScores(int page, int pageSize){
        Page<ViewTeamFinalScores> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ViewTeamFinalScores> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ViewTeamFinalScores::getTeamId);
        queryWrapper.orderByDesc(ViewTeamFinalScores::getFinalAverageScore);
        viewTeamFinalScoresService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getTeamDetails")
    public R<Page> getTeamDetails(int page, int pageSize){
        Page<ViewAwardTeamDetails> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ViewAwardTeamDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ViewAwardTeamDetails::getTeamId);
        viewAwardTeamDetailsService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getCertificateInfo")
    public R<Page> getCertificateInfo(int page, int pageSize){
        Page<ViewCertificateInfo> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ViewCertificateInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ViewCertificateInfo::getTeamId);
        viewCertificateInfoService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/isWon")
    public R<String> isWon(String teamId){
        LambdaQueryWrapper<ViewTeamAwardStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ViewTeamAwardStatus::getTeamId,teamId);
        ViewTeamAwardStatus one = viewTeamAwardStatusService.getOne(queryWrapper);
        if (one == null){
            return R.error("未查询到相关队伍");
        }
        switch (one.getAwardStatus()){
            case 1:
                return R.success("恭喜您获得一等奖!");
            case 2:
                return R.success("恭喜您获得二等奖!");
            case 3:
                return R.success("恭喜您获得三等奖!");
        }
        return R.success("很遗憾，您未能获奖");
    }
}

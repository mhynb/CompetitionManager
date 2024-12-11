package com.blcoder.competitionmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blcoder.competitionmanager.common.R;
import com.blcoder.competitionmanager.entity.*;
import com.blcoder.competitionmanager.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private FinalResultService finalResultService;

    @Autowired
    private PreliminnaryResultService preliminnaryResultService;

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private TeamProblemService teamProblemService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CertificatesService certificatesService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/getTeam")
    public R<Page> getTeam(int page,int pageSize){
        Page<Team> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Team> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Team::getTeamId);
        teamService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getStudent")
    public R<Page> getStudent(int page,int pageSize){
        Page<Student> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Student::getStudentId);
        studentService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getTeacher")
    public R<Page> getTeacher(int page,int pageSize){
        Page<Teacher> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Teacher::getNumber);
        teacherService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getProblem")
    public R<Page> getProblem(int page,int pageSize){
        Page<Problem> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Problem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Problem::getProblemNumber);
        problemService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getTeamProblem")
    public R<Page> getTeamProblem(int page,int pageSize){
        Page<TeamProblem> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<TeamProblem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(TeamProblem::getTeamId);
        teamProblemService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getExpert")
    public R<Page> getExpert(int page,int pageSize){
        Page<Expert> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Expert> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Expert::getExpertId);
        expertService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getRecords")
    public R<Page> getRecords(int page,int pageSize){
        Page<Records> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Records> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Records::getRecordsNumber);
        recordsService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getPreliminaryResult")
    public R<Page> getPreliminaryResult(int page,int pageSize){
        Page<PreliminaryResult> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<PreliminaryResult> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(PreliminaryResult::getTeamId);
        preliminnaryResultService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getCertificates")
    public R<Page> getCertificates(int page,int pageSize){
        Page<Certificates> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Certificates> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Certificates::getCertificatesId);
        certificatesService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @GetMapping("/getFinalResult")
    public R<Page> getFinalResult(int page,int pageSize){
        Page<FinalResult> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<FinalResult> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(FinalResult::getTeamId);
        finalResultService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping("/addTeam")
    public R<String> addTeam(@RequestBody Team team){
        Team newTeam = teamService.getById(team.getTeamId());
        if (newTeam != null){
            return R.error("团队编号已存在");
        }
        teamService.save(team);
        return R.success("保存成功");
    }

    @PutMapping("/updateTeam")
    public R<String> updateTeam(@RequestBody Team team){
        Team newTeam = teamService.getById(team.getTeamId());
        if (newTeam != null){
            return R.error("团队编号已存在");
        }
        teamService.updateById(team);
        return R.success("新增团队成功");
    }

    @DeleteMapping("/deleteTeam")
    @Transactional(rollbackFor = Exception.class) // 指定哪些异常会导致事务回滚
    public R<String> deleteTeam(@PathVariable String teamId) {
        try {
            teamService.removeById(teamId);
            return R.success("团队删除成功");
        } catch (Exception e) {
            // 这里可以记录日志或进行其他异常处理
            return R.error("团队删除失败");
        }
    }
}

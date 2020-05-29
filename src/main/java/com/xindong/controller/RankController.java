package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.Rank;
import com.xindong.service.impl.RankServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "评分接口")
public class RankController {

    @Autowired
    RankServiceImpl rankService;

    //    提交评分
    @ApiOperation("提交评分")
    @PostMapping(value = "/api/pushRank")
    public Result signup(@RequestBody Rank rank) {
        Integer res = rankService.insert(rank);
        if (res == 1) {
            return Result.ok().code(1).msg("评价成功");
        } else if (res == 0) {
            return Result.error().code(0).msg("评价失败");
        } else {
            return Result.error().code(2).msg("您已经评价过了！");
        }
    }

    //    获取指定歌单的评分
    @ApiOperation("获取指定歌单的评分")
    @GetMapping(value = "/api/getRank/{songListId}")
    public Result ranks(@PathVariable String songListId) {
        int i = rankService.selectAverScore(Long.parseLong(songListId));
        return Result.ok().data(i);
    }
}

package com.fangshuo.stars.controller;

import com.fangshuo.stars.req.CommentSaveReq;
import com.fangshuo.stars.resp.CommentListResp;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("/list/{blogId}")
    public CommonResp<List<CommentListResp>> commentList(@PathVariable Long blogId) {
        CommonResp<List<CommentListResp>> resp = new CommonResp<>();
        List<CommentListResp> commentList = commentService.list(blogId);
        resp.setContent(commentList);
        return resp;
    }

    @GetMapping("/commentNum/{blogId}")
    public CommonResp commentNum(@PathVariable Long blogId) {
        CommonResp resp = new CommonResp<>();
        Integer commentNum = commentService.commentNum(blogId);
        resp.setContent(commentNum);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp commentSave(@Valid @RequestBody CommentSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        commentService.save(req);
        return resp;
    }
}

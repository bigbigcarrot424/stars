package com.fangshuo.stars.controller;

import com.fangshuo.stars.req.CollectSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.CollectListByUserResp;
import com.fangshuo.stars.service.CollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Resource
    private CollectService collectService;

    @GetMapping("/list/{userId}")
    public CommonResp<List<CollectListByUserResp>> collectList(@PathVariable Long userId) {
        CommonResp<List<CollectListByUserResp>> resp = new CommonResp<>();
        List<CollectListByUserResp> collectList = collectService.list(userId);
        resp.setContent(collectList);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp collectSave(@Valid @RequestBody CollectSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        collectService.save(req);
        return resp;
    }

    @GetMapping("/collectBlogList/{userId}")
    public CommonResp collectSave(@PathVariable Long userId) {
        CommonResp<Object> resp = new CommonResp<>();
        List<BlogListResp> blogListResps = collectService.collectBlogList(userId);
        resp.setContent(blogListResps);
        return resp;
    }

    @GetMapping("/delete/{userId}/{blogId}")
    public CommonResp deleteCollect(@PathVariable Long userId, @PathVariable Long blogId) {
        CommonResp<Object> resp = new CommonResp<>();
        collectService.deleteByUserIdAndBlogId(userId, blogId);
        return resp;
    }
}

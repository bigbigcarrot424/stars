package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Message;
import com.fangshuo.stars.req.MessageSaveReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @PostMapping("/save")
    public CommonResp messageSave(@Valid @RequestBody MessageSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        messageService.save(req);
        return resp;
    }

    @GetMapping("/getMessage/{senderId}/{receiverId}")
    public CommonResp getMessageList(@PathVariable Long senderId, @PathVariable Long receiverId) {
        CommonResp<Object> resp = new CommonResp<>();
        List<Message> messageList = messageService.getMessageList(senderId, receiverId);
        resp.setContent(messageList);
        return resp;
    }

    @GetMapping("/deleteById/{messageId}")
    public CommonResp deleteById(@PathVariable Long messageId) {
        CommonResp<Object> resp = new CommonResp<>();
        messageService.deleteById(messageId);
        return resp;
    }
}

package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Message;
import com.fangshuo.stars.mapper.MessageMapper;
import com.fangshuo.stars.req.MessageSaveReq;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class MessageService {
    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private SnowFlake snowFlake;

    //通过消息Id找到消息
    public Message getMessageById(Long messageId){
        Message messageById = messageMapper.getMessageById(messageId);
        return messageById;
    }

    //存储消息
    public void save(MessageSaveReq req){
        Message message = CopyUtil.copy(req, Message.class);

        if (ObjectUtils.isEmpty(message.getId())){
            long messageId = snowFlake.nextId();
            message.setId(messageId);
        }
        message.setTime(new Timestamp(new Date().getTime()));
        messageMapper.save(message);
    }


    //删除消息
    public void deleteById(Long messageId){
        LOG.info("删除消息id：{}",messageId);
        messageMapper.deleteById(messageId);
    }
}

package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Message;

import java.util.List;

public interface MessageMapper {

    public void save(Message message);

    public Message getMessageById(Long id);

    public void deleteById(Long messageId);

    public List<Message> getMessageByPair(Long senderId, Long receiverId);

}

package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Message;

public interface MessageMapper {

    public void save(Message message);

    public Message getMessageById(Long id);

    public void deleteById(Long messageId);

}

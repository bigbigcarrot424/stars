package com.fangshuo.stars.websocket;

import com.fangshuo.stars.req.MessageSaveReq;
import com.fangshuo.stars.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fang YuanHao
 */
@Slf4j
@Component
@ServerEndpoint("/ws/chat/{userName}/{friendName}")
public class WebSocket {
    /**
     * 所有的客户端
     */
    public static Map<String,Session> sessions = new HashMap<>();


    /**
     * 建立连接时的回调
     * @param session session(客户端)
     * @param userName 用户名
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userName") String userName, @PathParam("friendName") String friendName) {
      log.info("{}->:{}，进入聊天室" , userName, friendName);
      String chatId = userName + ":" + friendName;
      if (sessions.containsKey(chatId)){
          session.getAsyncRemote().sendText("用户名已经存在");
          return;
      }
      sessions.put(chatId,session);
//      senMessage(String.format("%s:加入群聊",chatId), userName, friendName);
    }

    /**
     * 断开连接时的回调
     * @param userName 用户名
     */
    @OnClose
    public void onClose(Session session,@PathParam("userName") String userName, @PathParam("friendName") String friendName) throws IOException {
        session.close();
        String chatId = userName + ":" + friendName;
        log.info("{}，断开连接",chatId);
        sessions.remove(chatId);
//        senMessage(String.format("%s:离开群聊",chatId), userName, friendName);
    }

    /**
     * 发生错误时的回调
     * @param session session(客户端)
     * @param throwable 错误
     */
    @OnError
    public void onError(Session session, Throwable throwable,@PathParam("userName") String userName) throws IOException {
        log.info("{},出现错误", userName);
        session.close();
        sessions.remove(userName);
    }

    /**
     * 收到消息时的回调
     * @param message 消息
     * @param userName 用户名
     */
    @OnMessage
    public void onMessage(String message,@PathParam("userName") String userName ,@PathParam("friendName") String friendName) {
        log.info("{}->{}：{}",userName, friendName, message);

        log.info("{message}，存储前");
//        MessageService service = new MessageService();
//        service.save(new MessageSaveReq(Long.parseLong(userName), Long.parseLong(friendName), message));
        log.info("{message}，存储后");
        String chatId = userName + ":" + friendName;
        senMessage(String.format("%s:%s",chatId,message), userName, friendName);
    }

//    /**
//     * 发送给指定客户端
//     * @param message 消息
//     * @param session session(客户端)
//     */
//    public void senMessage(String message,Session session) {
//        // 发送消息
//        session.getAsyncRemote().sendText(message);
//    }

    /**
     * 发送给双方客户端
     * @param message 消息
     * @param userName 用户名
     * @param friendName 朋友名
     */
    public void senMessage(String message,String userName, String friendName) {
        sessions.forEach((k,v) -> {
            if (k.equals(userName + ":" +friendName) || k.equals(friendName + ":" +userName)){
                v.getAsyncRemote().sendText(message);
            }
        });
    }

//    /**
//     * 发送给所有的客户端
//     * @param message 消息
//     */
//    public void senMessage(String message) {
//        sessions.forEach((k,v) -> {
//            if (v.isOpen()) {
//                // 发送消息
//                v.getAsyncRemote().sendText(message);
//            }
//        });
//    }
}


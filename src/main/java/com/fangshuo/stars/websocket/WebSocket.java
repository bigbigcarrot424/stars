package com.fangshuo.stars.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Y
 */
@Slf4j
@Component
@ServerEndpoint("/ws/chat/{userName}")
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
    public void onOpen(Session session,@PathParam("userName") String userName) {
      log.info("{}，进入聊天室",userName);
      if (sessions.containsKey(userName)){
          session.getAsyncRemote().sendText("用户名已经存在");
          return;
      }
      sessions.put(userName,session);
      senMessage(String.format("%s：加入群聊",userName));
    }

    /**
     * 断开连接时的回调
     * @param userName 用户名
     */
    @OnClose
    public void onClose(Session session,@PathParam("userName") String userName) throws IOException {
        session.close();
        log.info("{}，断开连接",userName);
        sessions.remove(userName);
        senMessage(String.format("%s：离开群聊",userName));
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
    public void onMessage(String message,@PathParam("userName") String userName) {
        log.info("{}：{}",userName,message);
        senMessage(String.format("%s：%s",userName,message));
    }

    /**
     * 发送给指定客户端
     * @param message 消息
     * @param session session(客户端)
     */
    public void senMessage(String message,Session session) {
        // 发送消息
        session.getAsyncRemote().sendText(message);
    }

    /**
     * 发送给所有的客户端
     * @param message 消息
     */
    public void senMessage(String message) {
        sessions.forEach((k,v) -> {
            if (v.isOpen()) {
                // 发送消息
                v.getAsyncRemote().sendText(message);
            }
        });
    }
}


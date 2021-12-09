package com.example.videodownload.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
 private static int onlineCount=0;
private  static CopyOnWriteArrayList<MyWebSocket> webSockets=new CopyOnWriteArrayList<>();
private Session session;

@OnOpen
public void onOpen(Session session){
    this.session=session;
    webSockets.add(this);
    onlineCount++;
    System.out.println("有新连接加入！当前在线人数为"+onlineCount);

}
    @OnClose
    public void onClose() {
        webSockets.remove(this);  //从set中删除
        onlineCount--;
              //在线数减1
        System.out.println("当前在线人数为"+onlineCount);
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (MyWebSocket item : webSockets) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSockets) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

}

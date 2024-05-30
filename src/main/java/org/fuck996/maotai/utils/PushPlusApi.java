package org.fuck996.maotai.utils;

import cn.hutool.http.HttpUtil;

import org.fuck996.maotai.utils.manager.AsyncManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * @author zhiyuan
 */
@Component
public class PushPlusApi {

    @Value("${maotai.msg.token}")
    private String token;

    public void sendNotice(String title, String content) {
        
        AsyncManager.me().execute(sendNotice(token, title, content, "txt"));
    }

    /**
     * push推送
     *
     * @param token    token
     * @param title    消息标题
     * @param content  具体消息内容
     * @param template 发送消息模板
     */
    public static TimerTask sendNotice(String token, String title, String content, String template) {
        return new TimerTask() {
            @Override
            public void run() {
                String url = "http://www.pushplus.plus/send";
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("title", title);
                map.put("content", content);
                if (StringUtils.isEmpty(template)) {
                    map.put("template", "html");
                }
                HttpUtil.post(url, map);
            }
        };
    }

}

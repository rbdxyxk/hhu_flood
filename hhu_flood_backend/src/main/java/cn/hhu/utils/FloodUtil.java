package cn.hhu.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.UUID;

public class FloodUtil {

    // 生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    public static String getJSONString(int status, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }

        return json.toJSONString();
    }



    public static String getJSONString(int status, String msg) {
        return getJSONString(status,msg,null);
    }

    public static String getJSONString(int status) {
        return getJSONString(status, null,null);
    }

}

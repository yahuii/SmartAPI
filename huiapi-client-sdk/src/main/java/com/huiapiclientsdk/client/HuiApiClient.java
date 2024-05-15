package com.huiapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.huiapiclientsdk.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.huiapiclientsdk.util.SignUtils.genSecret;

/**
 * 调用第三方的客户端
 *
 * @author 顾琴
 */
@AllArgsConstructor
@NoArgsConstructor
public class HuiApiClient {

    private String accessKey;
    private String secretKey;

    public String getNameByGet(String name){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);



        String result = HttpRequest.get("http://localhost:8080/name/")
                .header("accessKey", accessKey)
                .header("secretKey", secretKey)
                .form(paramMap)
                .execute().body();
        System.out.println(result);
        return result;

    }


    public String getNameByPost(String name){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String result = HttpUtil.post("http://localhost:8080/name/", paramMap);
        System.out.println(result);
        return result;
    }


    /**
     * 生成请求头
     * @param body
     * @return
     */
    private Map<String,String> getHeaderMap(String body){
        Map<String,String> headerMap = new HashMap<>();

        headerMap.put("accessKey",accessKey);
        headerMap.put("body",body);
        headerMap.put("nonce", RandomUtil.randomNumbers(4));
        headerMap.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        headerMap.put("sign",genSecret(body,secretKey));

        return headerMap;
    }

    public String getUserNameByPost(User user){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",user.getName());
        String jsonStr = JSONUtil.toJsonStr(paramMap);
        String result = HttpRequest.post("http://localhost:8080/name/user")
                .addHeaders(getHeaderMap(jsonStr))
                .body(jsonStr)
                .execute().body();
        System.out.println(result);
        return result;
    }
}

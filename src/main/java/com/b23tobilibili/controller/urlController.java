package com.b23tobilibili.controller;


import com.b23tobilibili.pojo.Reply;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

@Controller
public class urlController {

    @RequestMapping("/index")
    @ResponseBody
    public String urlConverter(@RequestParam String b23url) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        String optedUrl = urlOpt(b23url);
        String pcUrl = null;
        try {
            URL url1 = new URL(optedUrl);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(5000);
            String s = conn.getHeaderField("Location");
            if (s==null){
//                jsonObject.put("code",0);
//                jsonObject.put("msg","Network Error!");
                pcUrl = "NetWork Error!";
            }else {
                pcUrl = s.split("\\?")[0];
//                jsonObject.put("code",1);
//                jsonObject.put("url",pcUrl);

            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return jsonObject.toString();
        return pcUrl;
    }

    private String urlOpt(String url) {
        String[] split = url.split("/");
        int n = split.length;
        return "https://b23.tv/"+split[n-1];
    }
}

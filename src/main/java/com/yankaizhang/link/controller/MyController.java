package com.yankaizhang.link.controller;

import com.yankaizhang.link.api.Result;
import com.yankaizhang.link.service.impl.MyServiceImpl;
import com.yankaizhang.spring.beans.factory.annotation.Autowired;
import com.yankaizhang.spring.context.annotation.Controller;
import com.yankaizhang.spring.util.StringUtils;
import com.yankaizhang.spring.webmvc.annotation.RequestBody;
import com.yankaizhang.spring.webmvc.annotation.RequestMapping;
import com.yankaizhang.spring.webmvc.annotation.RequestParam;
import com.yankaizhang.spring.webmvc.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Controller
public class MyController {

    @Autowired
    private MyServiceImpl myService;

    @Autowired
    private Pattern urlPattern;

    @Autowired
    private String baseSite;

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }

    @RequestMapping("/longLink")
    @ResponseBody
    public Result getShortLink(@RequestParam("link") String longLink){
        if (!urlPattern.matcher(longLink).matches()){
            return Result.failed();
        }
        String shortLink = myService.getShortLink(longLink);
        Result result;
        if (!StringUtils.isEmpty(shortLink)){
            result = Result.success();
            result.setData(baseSite + shortLink);
        }else{
            result = Result.failed();
        }
        return result;
    }

    /**
     * 重定向短连接到长连接
     */
    @RequestMapping("/s")
    public void redirectShortLink(@RequestParam("l") String shortLink, HttpServletResponse response){
        String longLink = myService.getLongLink(shortLink);
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        if (longLink == null){
            response.setHeader("Location", "/error");
        }else{
            response.setHeader("Location", longLink);
        }
    }
}

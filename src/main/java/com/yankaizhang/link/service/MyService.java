package com.yankaizhang.link.service;


public interface MyService {

    /**
     * 通过短链接获取长链接
     * @param shortLink 输入短链接
     * @return 长连接
     */
    String getLongLink(String shortLink);

    /**
     * 将长连接转化为短连接
     * @param longLink 输入长链接
     * @return 短链接
     */
    String getShortLink(String longLink);

}

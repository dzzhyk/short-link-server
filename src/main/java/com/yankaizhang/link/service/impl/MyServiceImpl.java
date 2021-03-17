package com.yankaizhang.link.service.impl;

import com.yankaizhang.link.service.MyService;
import com.yankaizhang.spring.context.annotation.Service;
import com.yankaizhang.spring.util.StringUtils;
import org.apache.commons.codec.digest.MurmurHash2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyServiceImpl implements MyService {


    /** 暂时没有实现持久化功能，就是用Map代替一下数据库 shortLink -> longLink*/
    private Map<String, String> cache = new ConcurrentHashMap<>(256);

    /** 62进制数字符表 */
    private final String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String getLongLink(String shortLink) {
        if (shortLink == null) return null;
        if (cache.containsKey(shortLink)){
            return cache.get(shortLink);
        }
        return null;
    }

    @Override
    public String getShortLink(String longLink) {
        if (longLink == null) return null;
        int hash = MurmurHash2.hash32(longLink);
        String ans = intToBase62(hash);
        if (cache.containsKey(ans)){
            String tempLongLink = cache.get(ans);
            if (tempLongLink.equals(longLink)) {
                // 如果发生了哈希冲突，使用开放地址法解决，知道找到一个位置hash不冲突
                do {
                    hash += 1;
                    ans = intToBase62(hash);
                }while(cache.containsKey(ans));
                cache.put(ans, longLink);
            }
        }
        return ans;
    }


    /**
     * 将int数字转换为62进制数，压缩长度
     * @param number 输入数字
     * @return 62进制结果
     */
    private String intToBase62(int number){
        if (number == 0) return "0";
        StringBuilder ans = new StringBuilder();
        int negative = number < 0 ? 1 : 0;
        int temp = negative == 1 ? -number : number;
        while(temp != 0){
            ans.append(base62.charAt(temp % 62));
            temp /= 62;
        }
        if (negative == 1) ans.append("0");
        return ans.reverse().toString();
    }
}

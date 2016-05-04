package com.github.fallblank.ganklast.data.entity;

import java.io.Serializable;
import java.util.Date;

import retrofit.http.PUT;

/**
 * Created by fallb on 2016/4/23.
 */

/**
 * {
 * "_id": "56cc6d23421aa95caa707b47",
 * "createdAt": "2015-06-15T12:36:50.961Z",
 * "desc": "Google\u5b98\u65b9\u51fa\u54c1\u7684\u5c06Dalvik\u5b57\u8282\u7801\u8f6c\u6362\u4e3a\u7b49\u4ef7\u7684Java\u5b57\u8282\u7801\u7684\u5de5\u5177",
 * "publishedAt": "2016-04-22T12:18:49.675Z",
 * "type": "Android",
 * "url": "https://github.com/google/enjarify",
 * "used": true,
 * "who": "mthli"
 * }
 */
public class Gank implements Cloneable ,Serializable{
    public String _id;
    public Date createdAt;
    public String desc;
    public Date publishedAt;
    public String type;
    public String url;
    public boolean used;
    public String who;

    /**
     * judge whether it is a header or not,if is,show header
     */
    public boolean isHeader;

    @Override
    public Gank clone() {
        Gank gank = null;
        try {
            gank = (Gank) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gank;
    }
}

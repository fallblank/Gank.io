package com.github.fallblank.ganklast.data;

import java.util.List;

/**
 * Created by fallb on 2016/4/22.
 * data format:
 * {
 *"error": false,
 *"results": [
 *"2016-04-22",
 *.........
 *"2015-05-18"
 *]
 *}
 */
public class History extends BaseData{
    public List<String> results;
}

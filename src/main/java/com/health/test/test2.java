package com.health.test;

import redis.clients.jedis.Jedis;

// 将类名从Test2改为test2以匹配文件名
public class test2 {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("redis://localhost:6379");
    }
}
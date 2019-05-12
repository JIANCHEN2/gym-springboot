package com.guava;

public interface GuavaRateLimiterService {
    /**
     * 功能描述:
     *
     * @param: 尝试获取令牌
     * @return:
     * @auther: chen
     * @date: 2019-05-12 16:44
     */
    boolean tryAcquireSeckill();
    String executeSeckill();
}


package com.guava.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.guava.GuavaRateLimiterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @Auther: chen
 * @Date: 2019-05-12 14:46
 * @Description: 测试RateLimiter限流服务
 */
@Service
public class GuavaRateLimiterServiceImpl implements GuavaRateLimiterService {

    /**
     * 每秒钟只发出2个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter seckillRateLimiter = RateLimiter.create(0.5);

    @Override
    public boolean tryAcquireSeckill() {
        return seckillRateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    @Transactional
    public String executeSeckill() {
        // 验证是否被限流器限制，如果没有，则继续往下执行业务
        if(tryAcquireSeckill()){
            return "没有限制住";
        }else {
            //被限流器限制
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "被限制";
        }
    }
}

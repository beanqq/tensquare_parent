package com.tensquare.user.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit消息队列的自动配置类
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("sms");
    }
}

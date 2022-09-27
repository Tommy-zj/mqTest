package cn.itcast.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JJ
 * @version 1.0
 * @description: TODO
 * @date 2022/9/26 20:36:58
 */
@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("itcast.fanout");
    }


    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }

    @Bean
    public Binding fanoutBinding(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }
}

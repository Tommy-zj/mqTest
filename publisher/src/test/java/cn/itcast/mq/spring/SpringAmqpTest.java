package cn.itcast.mq.spring;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JJ
 * @version 1.0
 * @description: TODO
 * @date 2022/9/26 17:17:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void rabbitTest() {
        String message = "hello world";
        String queueName = "simple.queue";
        rabbitTemplate.convertAndSend(queueName, message);
    }


    @Test
    public void rabbitTestWorkQueue() throws InterruptedException {
        String message = "hello world>>>";
        String queueName = "simple.queue";
        for (int i = 0; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            System.out.println("消息发布完成>>>>" + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() {
        String exchangeName = "itcast.fanout";
        String message = "hello every one!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        String exchangeName = "itcast.direct";
        String message = "hello yellow!";
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }

    @Test
    public void testSendTopicExchange() {
        String exchangeName = "itcast.topic";
        String message = "船只教育在深交所上市了!";
        rabbitTemplate.convertAndSend(exchangeName, "china", message);
    }

    @Test
    public void testSendTopicExchange2() {
        String exchangeName = "itcast.topic";
        String message = "今天天气不错，我今天心情好极了!";
        rabbitTemplate.convertAndSend(exchangeName, "china.wether", message);
    }

    @Test
    public void testSendObjectQueue() {
        String exchangeName = "object.queue";
        Map message = new HashMap<>();
        message.put("name","陈独秀");
        message.put("age",16);
        rabbitTemplate.convertAndSend(exchangeName, message);
    }
}

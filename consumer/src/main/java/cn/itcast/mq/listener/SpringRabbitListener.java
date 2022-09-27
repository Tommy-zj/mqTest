package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author JJ
 * @version 1.0
 * @description: TODO
 * @date 2022/9/26 17:26:30
 */
@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String message) {
//        System.out.println("监听到的消息》》>" + message);
//    }


    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue(String message) throws InterruptedException {
        System.out.println("消费者1收到到的消息》》>" + message + ">>>" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String message) throws InterruptedException {
        System.out.println("消费者2.....消息》》>" + message + ">>>" + LocalTime.now());
        Thread.sleep(200);
    }


    @RabbitListener(queues = "fanout.queue1")
    public void listenfanoutQueue(String message) {
        System.out.println("监听到fanoutQueue1的消息》》>" + message);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenfanoutQueue2(String message) {
        System.out.println("监听到fanoutQueue2的消息》》>" + message);
    }

    // 监听传输内容为object
    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Object message) {
        System.out.println("监听到objectQueue1的消息》》>" + message);
    }


    /**
     * 声明式监听direct交换机模式
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void ListenDirectQueue1(String msg) {
        System.out.println("消费者接收到direct.queue1的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void ListenDirectQueue2(String msg) {
        System.out.println("消费者接收到direct.queue2的消息：" + msg);
    }

    // 创建和绑定topic交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg) {
        System.out.println("消费者接收到topic.queue1的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg) {
        System.out.println("消费者接收到topic.queue2的消息：" + msg);
    }
}

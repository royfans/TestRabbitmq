import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Date;

public class Recv {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME ;
        for(int i=0;i<50000;i++){
            QUEUE_NAME= "queue_name_" + i;
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 绑定队列到交换机
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key.*");
        }
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        for(int i=0;i<50000;i++){
            QUEUE_NAME= "queue_name_" + i;
            // 监听队列，手动返回完成
            channel.basicConsume(QUEUE_NAME, false, consumer);
        }
        long currentTime=new Date().getTime();
        System.out.println("currentTime:" + currentTime);
        // 获取消息
        int count = 1;
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            long currentTime2=new Date().getTime();
            System.out.println(count + ":" + currentTime2 + " :[x] Received '" + message + "'");
            count = count + 1;
        }
    }
}

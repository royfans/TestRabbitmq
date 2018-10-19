import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Date;

public class Recv {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // ��ȡ�������Լ�mqͨ��
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME ;
        for(int i=0;i<50000;i++){
            QUEUE_NAME= "queue_name_" + i;
            // ��������
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // �󶨶��е�������
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key.*");
        }
        // ������е�������
        QueueingConsumer consumer = new QueueingConsumer(channel);
        for(int i=0;i<50000;i++){
            QUEUE_NAME= "queue_name_" + i;
            // �������У��ֶ��������
            channel.basicConsume(QUEUE_NAME, false, consumer);
        }
        long currentTime=new Date().getTime();
        System.out.println("currentTime:" + currentTime);
        // ��ȡ��Ϣ
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

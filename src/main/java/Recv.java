import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Date;

public class Recv {

    //private final static String QUEUE_NAME= "test_queue_topic_work1";
    //private final static String QUEUE_NAME2 = "test_queue_topic_work2";

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {

        // ��ȡ�������Լ�mqͨ��
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        String QUEUE_NAME ;
        for(int i=0;i<200;i++){
            QUEUE_NAME= "queue_name_" + i;
            // ��������
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // �󶨶��е�������
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key.*");

        }
        // ������е�������
        QueueingConsumer consumer = new QueueingConsumer(channel);
        for(int i=0;i<200;i++){
            QUEUE_NAME= "queue_name_" + i;
            // �������У��ֶ��������
            channel.basicConsume(QUEUE_NAME, false, consumer);
        }

        // ��ȡ��Ϣ
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            long currentTime=new Date().getTime();
            System.out.println(currentTime +" :[x] Received '" + message + "'");
            //Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}

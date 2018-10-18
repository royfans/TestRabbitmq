import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // ��ȡ�������Լ�mqͨ��
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // ����exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // ��Ϣ����
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "key.1", null, message.getBytes());
//        channel.basicPublish(EXCHANGE_NAME, "keys.*", null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
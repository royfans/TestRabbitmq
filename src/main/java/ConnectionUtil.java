

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        //�������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        //���÷����ַ
        factory.setHost("localhost");
        //�˿�
        factory.setPort(5672);
        //�����˺���Ϣ���û��������롢vhost
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // ͨ�����̻�ȡ����
        Connection connection = factory.newConnection();

        return connection;
    }

}

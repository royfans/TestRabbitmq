import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Date;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";
    //private static long currentTime=new Date().getTime();
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //long currentTime=new Date().getTime();
        long currentTime=new Date().getTime();
        System.out.println("start_currentTime:" + currentTime);
        // 消息内容
        String message = "{\"script\":\"weibo/facebook/guanzhu_mobile.js\",\"site_id\":\"9\",\"engine\":\"mobile\",\"id\":\"130955\",\"task_id\":\"89\",\"task_type\":\"twitter_attention\",\"priority\":\"0\",\"user_id\":\"1\",\"failed_times\":0,\"bind_ip_str\":\"{\\\"\\\":1}\",\"last_login_time\":null,\"telephone\":null,\"device\":\"mobile\",\"params\":{\"content\":\"\",\"login_tag\":\"1\",\"scheduler_time\":\"1539761497000\",\"imei\":\"863273031210874\",\"fullphone\":\"\",\"email\":\"wbsf7j@163.com\",\"taskId\":\"\",\"taskName\":\"\",\"last_timeline_iid\":\"\",\"crawl_timeline\":\"0\",\"crawl_friends\":\"0\",\"crawl_timeline_page\":\"10\",\"crawl_friends_page\":\"3\",\"userName\":\"wbsf7j@163.com\",\"userName1\":\"wbsf7j\",\"nickName\":\"wbsf7j@163.com\",\"passWord\":\"jq2803\",\"birth\":null,\"cid\":null,\"guid\":\"\",\"token\":null,\"telephone\":null,\"first_name_us\":null,\"last_name_us\":null,\"country_code\":null,\"identity\":null,\"place_id\":\"\",\"linux_ip\":\"\",\"phone_seq\":99999,\"uid\":\"\",\"operateUrl\":\"https://www.facebook.com/FantandsddgoBeautyShop/\",\"email_id\":\"\"},\"account_type\":\"s\",\"area_code\":\"0000\",\"ock\":null,\"oock\":null,\"account_id\":\"1552551\",\"brw\":null,\"op_start_time\":\"1539761501000\"}";
        channel.basicPublish(EXCHANGE_NAME, "key.2", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
        long currentTime2=new Date().getTime();
        System.out.println("start_currentTime:" + currentTime2);
    }
}
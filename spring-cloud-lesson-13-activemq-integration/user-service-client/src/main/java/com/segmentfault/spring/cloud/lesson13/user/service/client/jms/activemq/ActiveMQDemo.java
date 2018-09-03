package com.segmentfault.spring.cloud.lesson13.user.service.client.jms.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * Created by lizhuquan on 2018/9/3.
 */
public class ActiveMQDemo {

//    public static void main(String[] args) throws Exception {
//        sendMessage();
//        receiveMessage();
//    }

    private static void sendMessage() throws Exception {
        // 创建ActiveMQ链接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创建JMS链接
        Connection connection = connectionFactory.createConnection();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建消息的目的地
        Destination destination =  session.createQueue("test-queue");
        // 创建消息生产者
        MessageProducer producer = session.createProducer(destination);
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText("Hello,ActiveMQ");
        // 发送文本消息
        producer.send(message);
        // 关闭
        producer.close();
        session.close();
        connection.close();
    }

    private static void receiveMessage() throws Exception{
        // 创建ActiveMQ链接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创建JMS链接
        Connection connection = connectionFactory.createConnection();
        // 启动链接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建消息的目的地
        Destination destination =  session.createQueue("test-queue");
        // 创建消息消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //  监听消息消费
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("消费内容：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 关闭
//        consumer.close();
//        session.close();
//        connection.close();
    }
}

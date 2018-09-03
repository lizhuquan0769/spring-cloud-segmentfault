package com.segmentfault.spring.cloud.stream.binder.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;

import javax.jms.*;

/**
 * ActiveMQ MessageChannel Binder 实现
 * Created by lizhuquan on 2018/9/3.
 */
public class ActiveMQMessageChannelBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 负责接收ActiveMQ的消息
     * @param name
     * @param group
     * @param inChannel
     * @param consumerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inChannel, ConsumerProperties consumerProperties) {
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 创造 JMS 链接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话 Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息目的
            Destination destination = session.createQueue(name);
            // 创建消息消费者
            MessageConsumer messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(message -> {
                // message 来自于 ActiveMQ
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        Object object = objectMessage.getObject();
                        inChannel.send(new GenericMessage<Object>(object));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return () -> {

        };
    }

    /**
     * 负责发送ActiveMQ消息
     * @param name
     * @param outChannel
     * @param producerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outChannel, ProducerProperties producerProperties) {
        Assert.isInstanceOf(SubscribableChannel.class, outChannel, "Binding is supported only for SubsrcibableChannelinstances");

        SubscribableChannel subscribableChannel = (SubscribableChannel) outChannel;
        subscribableChannel.subscribe(message -> {
            // 接收内部管道消息，来自于MessageChannel#send(Message)
            // 实际并没有发送消息，而是将此消息代理给MessageHandler发送
            jmsTemplate.convertAndSend(name, message.getPayload());
        });

        return () -> {

        };
    }

}

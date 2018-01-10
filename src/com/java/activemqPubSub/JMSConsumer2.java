package com.java.activemqPubSub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;

/**
 * 消息订阅者2
 * @author Yuanjingkun
 *
 */
public class JMSConsumer2 {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
	private static final int SENDNUM = 10;//发送的消息数量
	public static void main(String[] args) {
		
		ConnectionFactory  connectionFactory;//连接工厂
		Connection connection = null ;//连接
		Session session;//会话接受或者发送消息的线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		
		//实例化连接工厂
		connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(JMSConsumer2.USERNAME, JMSConsumer2.PASSWORD, JMSConsumer2.BROKEURL);
		try {
			connection = connectionFactory.createConnection();
			connection.start();//启动连接
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//创建session
			destination = session.createTopic("Topic1");
			messageConsumer = session.createConsumer(destination);
			messageConsumer.setMessageListener(new Listener2());//注册消息监听
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//通过连接工厂获取连接
		
	}
}

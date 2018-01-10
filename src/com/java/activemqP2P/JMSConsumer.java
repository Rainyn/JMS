package com.java.activemqP2P;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;

/**
 * receive方式消费
 * @author Yuanjingkun
 *
 */
public class JMSConsumer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
	public static void main(String[] args) {
		
		ConnectionFactory  connectionFactory;//连接工厂
		Connection connection = null ;//连接
		Session session;//会话接受或者发送消息的线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		
		//实例化连接工厂
		connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
		try {
			connection = connectionFactory.createConnection();
			connection.start();//启动连接
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//创建session
			destination = session.createQueue("FirstQueue1");
			messageConsumer = session.createConsumer(destination);
			while(true){
				TextMessage textMessage =(TextMessage)messageConsumer.receive(100000);//100秒接收一次
				if(textMessage != null){
					System.out.println("receive方式收到的消息："+textMessage.getText());
				}else{
					break;
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//通过连接工厂获取连接
		
	}
}

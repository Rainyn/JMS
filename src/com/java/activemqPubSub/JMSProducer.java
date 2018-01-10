package com.java.activemqPubSub;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

import javax.jms.Connection;

public class JMSProducer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//Ĭ�����ӵ�ַ
	private static final int SENDNUM = 10;//���͵���Ϣ����
	public static void main(String[] args) {
		
		
		ConnectionFactory  connectionFactory;//���ӹ���
		Connection connection = null ;//����
		Session session;//�Ự���ܻ��߷�����Ϣ���߳�
		Destination destination;//��Ϣ��Ŀ�ĵ�
		MessageProducer messageProducer;//��Ϣ������
		
		//ʵ�������ӹ���
		connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BROKEURL);
		
		try {
			connection = connectionFactory.createConnection();//ͨ�����ӹ�����ȡ����
			connection.start();//��������
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);//����session
			destination = session.createTopic("Topic1");
			messageProducer = session.createProducer(destination);//������Ϣ������
			sendMessage(session, messageProducer);//������Ϣ
			session.commit();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection !=null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ������Ϣ
	 * @param session
	 * @param messageProducer
	 * @throws JMSException 
	 */
	public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException{
		
		for(int i=0;i<JMSProducer.SENDNUM;i++){
			TextMessage message =  session.createTextMessage("ActiveMQ ��������Ϣ"+i);
			System.out.println("������Ϣ��"+"Active MQ��������Ϣ"+i);
			messageProducer.send(message);
		}
	}
}

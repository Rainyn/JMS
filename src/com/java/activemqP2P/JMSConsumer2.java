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
 * ������ʽ����
 * @author Yuanjingkun
 *
 */
public class JMSConsumer2 {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//Ĭ�����ӵ�ַ
	public static void main(String[] args) {
		
		ConnectionFactory  connectionFactory;//���ӹ���
		Connection connection = null ;//����
		Session session;//�Ự���ܻ��߷�����Ϣ���߳�
		Destination destination;//��Ϣ��Ŀ�ĵ�
		MessageConsumer messageConsumer;//��Ϣ������
		
		//ʵ�������ӹ���
		connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(JMSConsumer2.USERNAME, JMSConsumer2.PASSWORD, JMSConsumer2.BROKEURL);
		try {
			connection = connectionFactory.createConnection();
			connection.start();//��������
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//����session
			destination = session.createQueue("FirstQueue1");
			messageConsumer = session.createConsumer(destination);
			messageConsumer.setMessageListener(new Listener());//ע����Ϣ����
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ͨ�����ӹ�����ȡ����
		
	}
}

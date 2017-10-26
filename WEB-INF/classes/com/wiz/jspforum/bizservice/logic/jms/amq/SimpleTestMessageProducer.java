
package com.wiz.jspforum.bizservice.logic.jms.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SimpleTestMessageProducer {

	protected JmsTemplate jmsTemplate = null;

	public SimpleTestMessageProducer() {

	}

	public void sendMessages(String msg) throws JMSException {
		final String text = msg;
		final int index = 1;
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(text);
				message.setIntProperty("messageCount", index);
				return message;
			}
		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}

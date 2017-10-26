
package com.wiz.jspforum.bizservice.logic.jms.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SimpleTestMessageListener implements MessageListener {

	public SimpleTestMessageListener() {

	}

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage msg = (TextMessage)message;
			System.out.println("Consumed message: " + msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}

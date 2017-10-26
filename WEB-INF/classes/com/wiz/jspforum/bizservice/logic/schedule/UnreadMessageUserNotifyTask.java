
package com.wiz.jspforum.bizservice.logic.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

//import com.google.gson.Gson;
import com.wiz.jspforum.bizservice.logic.basic.bdo.MessageBdo;
import com.wiz.jspforum.bizservice.logic.jms.amq.SimpleTestMessageProducer;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;

public class UnreadMessageUserNotifyTask {

	private MailSender mailSender = null;
	private MessageBdo messageBdo = null;
	private SimpleMailMessage simpleMailMessage = null;
	private SimpleTestMessageProducer simpleTestMessageProducer = null;

	public void runTaskForUserUnreadMessage() throws JMSException {
		System.out.println((new Date()) + " <==> javamail is invoked, dummy mails are sent ...");
//		Gson gson = new Gson();
		List<UserMessage> messageList = messageBdo.getAllUnreadMessagesForAllUsers();
		for (UserMessage msg : messageList) {
			Map<String, Object> map = new HashMap<String, Object>();
			simpleMailMessage.setTo(msg.getMessageDestUser().getUserEmail());
			simpleMailMessage.setSubject("Your friend - " + msg.getMessageSourceUser().getUserName() + " (" + msg.getMessageSourceUser().getUserEmail() + ") send one message to you from <jspforum>!");
			simpleMailMessage.setText(msg.getMessageContent() + " -- at the time of " + msg.getMessageTime());
			map.put("to", simpleMailMessage.getTo());
			map.put("subject", simpleMailMessage.getSubject());
			map.put("text", simpleMailMessage.getText());
//	        mailSender.send(simpleMailMessage);
//			simpleTestMessageProducer.sendMessages(gson.toJson(map));
		}
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public MessageBdo getMessageBdo() {
		return messageBdo;
	}

	@Autowired
	public void setMessageBdo(MessageBdo messageBdo) {
		this.messageBdo = messageBdo;
	}

	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	@Autowired
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public SimpleTestMessageProducer getSimpleTestMessageProducer() {
		return simpleTestMessageProducer;
	}

	@Autowired
	public void setSimpleTestMessageProducer(SimpleTestMessageProducer simpleTestMessageProducer) {
		this.simpleTestMessageProducer = simpleTestMessageProducer;
	}
}
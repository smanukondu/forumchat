
package com.wiz.jspforum.web.basic.mvc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiz.jspforum.bizservice.logic.basic.bdo.MessageBdo;
import com.wiz.jspforum.bizservice.logic.basic.bdo.UserBdo;
import com.wiz.jspforum.common.constant.ActionResultConstant;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.MessageForm;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractActionController {

	private MessageBdo messageBdo = null;
	
	private UserBdo userBdo = null;

	@Autowired
	public void setMessageBdo(MessageBdo messageBdo) {
		this.messageBdo = messageBdo;
	}
	
	@Autowired
	public void setUserBdo(UserBdo userBdo) {
		this.userBdo = userBdo;
	}

	@Override
	@ModelAttribute
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		logger = (SimpleLog)request.getSession(true).getAttribute(WebAttributeConstant.SYS_LOG);
		messageBdo.setSessionLogger(logger);
	}

	@RequestMapping(value="/common/loadinboxmessages", method=RequestMethod.GET)
	public String loadInboxAllMessagesByUser() {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load all the InBox messages");
		List<UserMessage> messageInBoxList = messageBdo.getMessageListByDestinationUser(user);
		logger.log(CommonLog.DEBUG, getClass().getName(), "messageInBoxList.size ==> " + messageInBoxList.size());
		forwardListPresentBeanToPage(messageInBoxList, WebAttributeConstant.INBOX_MESSAGE_PRESENT_BEAN);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load all the InBox messages");
		return ActionResultConstant.LOADED_INBOX_MESSAGES_PAGE;
	}

	@RequestMapping(value="/common/loadinmessagedetail", method=RequestMethod.GET)
	public String loadInboxMessagesDetail(Model model, 
		@RequestParam(required=true, name="MESSAGE_ID") int messageId) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to review the detail of InBox message (Read Only)");
		UserMessage message = messageBdo.getMessageDetailByMessageId(messageId);
		messageBdo.setReadFlagValueOfMessageAs(message, UserMessage.CONSTANT_MESSAGE_READ_FLAG_READ);
		model.addAttribute(WebAttributeConstant.MESSAGE_DETAIL, message);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to review the detail of InBox message (Read Only)");
		return ActionResultConstant.LOADED_MESSAGE_DETAIL_PAGE;
	}

	@RequestMapping(value="/common/loadoutboxmessages", method=RequestMethod.GET)
	public String loadOutboxAllMessagesByUser() {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load all the OutBox messages");
		List<UserMessage> messageOutBoxList = messageBdo.getMessageListBySourceUser(user);
		logger.log(CommonLog.DEBUG, getClass().getName(), "messageOutBoxList.size ==> " + messageOutBoxList.size());
		forwardListPresentBeanToPage(messageOutBoxList, WebAttributeConstant.OUTBOX_MESSAGE_PRESENT_BEAN);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load all the OutBox messages");
		return ActionResultConstant.LOADED_OUTBOX_MESSAGES_PAGE;
	}

	@ModelAttribute("messageForm")
	public MessageForm newMessageForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "MessageController.newMessageForm() is called by SpringMVC for creating the instance of MessageForm ...");
		return new MessageForm();
	}

	@RequestMapping(value="/common/messageform", method=RequestMethod.GET)
	public void messageForm(@RequestParam(required=true, name="MESSAGE_ID") int messageId, Model model) {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "MessageController.messageForm() is called by SpringMVC for loading the view page of MessageForm ...");
		session.setAttribute(WebAttributeConstant.MESSAGE_DETAIL, messageBdo.getMessageDetailByMessageId(messageId));
	}

	@RequestMapping(value="/common/messageform", method=RequestMethod.POST)
	public String replyForInboxMessage(@Valid MessageForm messageForm, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to reply the message to friend");
		// insert the new message into DB
		messageBdo.processLogicOfSendingMessageToFriend(messageForm);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to reply the message to friend");
		return "redirect:/message/common/loadoutboxmessages";
	}

	@RequestMapping(value="/common/loadmessagehistorywithfriend", method=RequestMethod.GET)
	public String loadMessagesHistoryWithFriend(@RequestParam(required=true, name="FRIEND_USER_ID") int friendUserId) {
		UserProfile friend = userBdo.getUserDetailsByUserId(friendUserId);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load the message history on friend (" + friend.getUserName() + ")");
		List<UserMessage> messageList = messageBdo.getHistoryMessageListWithFriend(user, friend);
		logger.log(CommonLog.DEBUG, getClass().getName(), "messageHistoryList.size() = " + messageList.size());
		forwardMessageHistoryPresentBeanToPage(messageList, friend);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") end to load the message history on friend (" + friend.getUserName() + ")");
		return ActionResultConstant.LOADED_MESSAGES_HISTORY_WITH_FRIEND_PAGE;
	}
}

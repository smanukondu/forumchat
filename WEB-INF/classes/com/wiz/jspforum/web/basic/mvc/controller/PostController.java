
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

import com.wiz.jspforum.bizservice.logic.basic.bdo.PostBdo;
import com.wiz.jspforum.common.constant.ActionResultConstant;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.PostCommentForm;
import com.wiz.jspforum.web.basic.bean.form.UserPostForm;
import com.wiz.jspforum.web.basic.bean.presentation.PostCommentListModel;

@Controller
@RequestMapping("/post")
public class PostController extends AbstractActionController {

	private PostBdo postBdo = null;

	@Autowired
	public void setPostBdo(PostBdo postBdo) {
		this.postBdo = postBdo;
	}

	@Override
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		logger = (SimpleLog)request.getSession(true).getAttribute(WebAttributeConstant.SYS_LOG);
		postBdo.setSessionLogger(logger);
	}

	@ModelAttribute("userPostForm")
	public UserPostForm newUserPostForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "PostController.newUserPostForm() is called by SpringMVC for creating the instance of UserPostForm ...");
		return new UserPostForm();
	}

	@RequestMapping(value="/common/userpostform", method=RequestMethod.GET)
	public void userPostForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "PostController.userPostForm() is called by SpringMVC for loading the view page of UserPostForm ...");
	}

	@RequestMapping(value="/common/userpostform", method=RequestMethod.POST)
	public String raiseNewPost(@Valid UserPostForm userPostForm, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to raise a new user post");
		postBdo.addNewUserPost(userPostForm, user);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to raise a new user post");
		return "redirect:/post/common/loadallposts";
	}

	@RequestMapping(value="/common/loadallposts", method=RequestMethod.GET)
	public String loadAllPosts() {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load all the posts");
		List<UserPost> postList = postBdo.getAllThePosts();
		logger.log(CommonLog.DEBUG, getClass().getName(), "postList.size() == " + ((postList == null || postList.size() == 0) ? 0 : postList.size()));
		forwardListPresentBeanToPage(postList, WebAttributeConstant.POST_PRESENT_BEAN);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load all the posts");
		return ActionResultConstant.LOADED_POSTS_PAGE;
	}

	@RequestMapping(value="/common/loadpostdetail", method=RequestMethod.GET)
	public String loadPostDetail(@RequestParam(required=true, name="POST_ID") int postId) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to load the post detail (ReadOnly mode)");
		forwardPostCommentsListPresentBeanToPage(postBdo.getPostByPostId(postId));
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to load the post detail (ReadOnly mode)");
		return ActionResultConstant.LOADED_POST_DETAIL_PAGE;
	}

	@ModelAttribute("postCommentForm")
	public PostCommentForm newPostCommentForm() {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "PostController.newPostCommentForm() is called by SpringMVC for creating the instance of newPostCommentForm ...");
		return new PostCommentForm();
	}

	@RequestMapping(value="/common/postcommentform", method=RequestMethod.GET)
	public <E> void postCommentForm(@RequestParam(required=true, name="POST_ID") int postId, Model model) {
		CommonLog.log(CommonLog.INFOR, getClass().getName(), "PostController.userPostForm() is called by SpringMVC for loading the view page of UserPostForm ...");
		UserPost post = postBdo.getPostByPostId(postId);
		PostCommentListModel<E> pclpb = new PostCommentListModel<E>(post);
		model.addAttribute(WebAttributeConstant.POST_COMMENT_PRESENT_BEAN, pclpb);
	}

	@RequestMapping(value="/common/postcommentform", method=RequestMethod.POST)
	public String replyNewCommnetToPost(@Valid PostCommentForm postCommentForm, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to raise a new user post");
		postBdo.processLogicOfAppendingCommentToPost(postCommentForm, user);
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to raise a new user post");
		return "redirect:/post/common/loadpostdetail?POST_ID=" + postCommentForm.getPostId();
	}
}

/*
 * Licensed to the WIZ under one or more contributor license agreements. 
 * The WIZ licenses this file to You under the WIZ License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *     http://www.wiz.com/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  For additional information regarding 
 * copyright in this work, please see the NOTICE file in the top level 
 * directory of this distribution.
 */

package com.wiz.jspforum.bizservice.logic.basic.bdoImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wiz.jspforum.bizservice.logic.basic.bdo.AbstractBdoSupport;
import com.wiz.jspforum.bizservice.logic.basic.bdo.PostBdo;
import com.wiz.jspforum.common.constant.ContextHelper;
import com.wiz.jspforum.persistence.basic.data.dao.PostDao;
import com.wiz.jspforum.persistence.basic.data.dto.PostComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.date.DateSimpleFormat;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.util.search.SearchFacade;
import com.wiz.jspforum.web.basic.bean.form.PostCommentForm;
import com.wiz.jspforum.web.basic.bean.form.UserPostForm;

/**
 * The Implement Class of PostBdo
 */
@Service("postBdo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PostBdoImple extends AbstractBdoSupport implements PostBdo {

	private PostDao postDao = null;

	public PostBdoImple() {

	}

	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public List<UserPost> getAllThePosts() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getAllThePosts()");
		return postDao.getUserPostRecordsForAllOrderByPostTimeDesc();
	}

	@Override
	public UserPost getPostByPostId(int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getPostByPostId(postId)");
		UserPost post = postDao.getUserPostRecordByPostId(postId);
		// ----------------------------------
		// try to process the BB Code simply
		// ----------------------------------
		String postContent = post.getPostContent();
		postContent = StringUtils.replace(StringUtils.replace(postContent, "<", "&lt;"), ">", "&gt;");
		postContent = StringUtils.replace(StringUtils.replace(postContent, "[b]", "<b>"), "[/b]", "</b>");
		postContent = StringUtils.replace(StringUtils.replace(postContent, "[i]", "<i>"), "[/i]", "</i>");
		postContent = StringUtils.replace(StringUtils.replace(postContent, "[u]", "<u>"), "[/u]", "</u>");
		postContent = StringUtils.replace(StringUtils.replace(postContent, "[s]", "<s>"), "[/s]", "</s>");
		postContent = StringUtils.replace(postContent, "[br]", "</br>");
		postContent = StringUtils.replace(postContent, "[/color]", "</font>");
		postContent = StringUtils.replace(postContent, "[color=black]", "<font color=\"black\">");
		postContent = StringUtils.replace(postContent, "[color=darkred]", "<font color=\"darkred\">");
		postContent = StringUtils.replace(postContent, "[color=red]", "<font color=\"red\">");
		postContent = StringUtils.replace(postContent, "[color=orange]", "<font color=\"orange\">");
		postContent = StringUtils.replace(postContent, "[color=brown]", "<font color=\"brown\">");
		postContent = StringUtils.replace(postContent, "[color=yellow]", "<font color=\"yellow\">");
		postContent = StringUtils.replace(postContent, "[color=green]", "<font color=\"green\">");
		postContent = StringUtils.replace(postContent, "[color=olive]", "<font color=\"olive\">");
		postContent = StringUtils.replace(postContent, "[color=cyan]", "<font color=\"cyan\">");
		postContent = StringUtils.replace(postContent, "[color=blue]", "<font color=\"blue\">");
		postContent = StringUtils.replace(postContent, "[color=darkblue]", "<font color=\"darkblue\">");
		postContent = StringUtils.replace(postContent, "[color=violet]", "<font color=\"violet\">");
		postContent = StringUtils.replace(postContent, "[:)]", "<img src=\"" + ContextHelper.APP_CONTEXT_NAME + "/resources/images/bbcode_smile.gif\" alt=\":)\">");
		postContent = StringUtils.replace(postContent, "[:(]", "<img src=\"" + ContextHelper.APP_CONTEXT_NAME + "/resources/images/bbcode_cry.gif\" alt=\":(\">");
		postContent = StringUtils.replace(postContent, "[/img]", "\" alt=\"\"/>");
		postContent = StringUtils.replace(postContent, "[img]", "<img src=\"");
		// postContent = StringUtils.replace(postContent, "[/url]", "</a>");
		// postContent = StringUtils.replace(postContent, ")]", "\">");
		// postContent = StringUtils.replace(postContent, "[url=(", "<a href=\"");
		postContent = StringUtils.replace(postContent, "[/url]", "</a>");
		int urlTagCount = StringUtils.countMatches(postContent, "[url=");
		for (int i = 0; i < urlTagCount; i++) {
			int urlTagStartIdx = StringUtils.indexOf(postContent, "[url=");
			int urlContentStartIdx = urlTagStartIdx + "[url=".length();
			int urlTagEndIdx = StringUtils.indexOf(postContent, ']', urlTagStartIdx);
			String urlContent = StringUtils.substring(postContent, urlContentStartIdx, urlTagEndIdx);
			postContent = StringUtils.substring(postContent, 0, urlTagStartIdx) + ("<a href=\"" + urlContent + "\">") + StringUtils.substring(postContent, urlTagEndIdx + 1);
		}
		post.setPostContent(postContent);
		return post;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void addNewUserPost(UserPostForm userPostForm, UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.addNewUserPost(userPostForm,user)");
		String attachment_path = DateSimpleFormat.getSimpleDatetimeString(new Date()) + user.getUserId();
		String attachmentImages = "";
		for (int i = 0; i < userPostForm.getFormFiles().size(); i++) {
			MultipartFile formFile = userPostForm.getFormFiles().get(i);
			if (formFile != null && formFile.getOriginalFilename() != null && !formFile.getOriginalFilename().equals("")) {
				try {
					ResourceBundle rb = ResourceBundle.getBundle("config");
					String folderPath = rb.getString("RESOURCE_POST_ATTACHMENT_PATH");
					String userAttachmentFolder_str = folderPath + attachment_path;
					File userAttachmentFolderFile = new File(userAttachmentFolder_str);
					if (!userAttachmentFolderFile.exists()) {
						userAttachmentFolderFile.mkdir();
					}
					File newFile = new File(userAttachmentFolder_str + "/" + ("0" + i) + "-" + formFile.getOriginalFilename());
					if (!newFile.exists()) {
						FileOutputStream fos = null;
						fos = new FileOutputStream(newFile);
						fos.write(formFile.getBytes());
						fos.flush();
						fos.close();
					}
					attachmentImages += (attachment_path + "/" + ("0" + i) + "-" + formFile.getOriginalFilename() + ",");
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		if (!"".equals(attachmentImages)) {
			userPostForm.setAttachmentImage(attachmentImages.substring(0, attachmentImages.length() - 1));
		} else {
			userPostForm.setAttachmentImage("");
		}
		UserPost post = new UserPost();
		post.setPostTopic(userPostForm.getPostTopic());
		post.setPostContent(userPostForm.getPostContent());
		post.setPostMark(0);
		post.setPostCommentNum(0);
		post.setPostLevel(1);
		post.setPostUser(user);
		post.setPostTime(new Date());
		post.setPostAttachmentImage(userPostForm.getAttachmentImage());
		System.out.println(post.getPostAttachmentImage());
		postDao.insertUserPostRecord(post);
		// put the new raised Post into local Lucene Search Engine
		SearchFacade.create(post);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void addNewPostComment(PostCommentForm postCommentForm, UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.addNewPostComment(postCommentForm,user)");
		UserPost post = postDao.getUserPostRecordByPostId(postCommentForm.getPostId());
		PostComment postComment = new PostComment();
		postComment.setPostCommentPost(post);
		postComment.setPostCommentUser(user);
		postComment.setPostCommentMark(postCommentForm.getPostCommentMark());
		postComment.setPostCommentContent(postCommentForm.getPostCommentContent());
		postComment.setPostCommentDate(new Date());
		postDao.insertPostCommentRecord(postComment);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void updatePostMarkByPostComment(int postCommentMark, int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updatePostMarkByPostComment(postCommentMark,postId)");
		UserPost post = postDao.getUserPostRecordByPostId(postId);
		post.setPostMark(post.getPostMark() + postCommentMark);
		toLog(CommonLog.DEBUG, getClass().getName(), "new postMark = " + post.getPostMark());
		postDao.updateUserPostRecordForPostMark(post);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void increasePostCommentTimes(int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.increasePostCommentTimes(postId)");
		UserPost post = postDao.getUserPostRecordByPostId(postId);
		post.setPostCommentNum(post.getPostCommentNum() + 1);
		toLog(CommonLog.DEBUG, getClass().getName(), "new postCommentTimes = " + post.getPostCommentNum());
		postDao.updateUserPostRecordForPostCommentTimes(post);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfAppendingCommentToPost(PostCommentForm postCommentForm, UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfAppendingCommentToPost(postCommentForm,user)");
		addNewPostComment(postCommentForm, user);
		updatePostMarkByPostComment(postCommentForm.getPostCommentMark(), postCommentForm.getPostId());
		increasePostCommentTimes(postCommentForm.getPostId());
		UserPost updatedPost = postDao.getUserPostRecordByPostId(postCommentForm.getPostId());
		// put the updated Post to synchronize with the one in local Lucene Search Engine
		SearchFacade.update(updatedPost);
	}

	@Override
	public void setSessionLogger(SimpleLog logger) {
		super.logger = logger;
		setSessionLoggerToDaos(logger);
	}

	@Override
	public void setSessionLoggerToDaos(SimpleLog logger) {
		postDao.setUserLogInstance(logger);
	}
}

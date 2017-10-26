
package com.wiz.jspforum.web.basic.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiz.jspforum.common.constant.ActionResultConstant;
import com.wiz.jspforum.common.constant.WebAttributeConstant;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.search.SearchArgs;
import com.wiz.jspforum.util.search.SearchFacade;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractActionController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/searchfromlucene", method=RequestMethod.POST)
	public String searchFromLuceneEngine(@RequestParam(required=true, name="keyword") String keyword) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to search the posts from Lucene Search Engine");
		SearchArgs searchArgs = new SearchArgs();
		searchArgs.setKeywords(keyword);
		List<UserPost> postList = (List<UserPost>)(SearchFacade.search(searchArgs).records());
		forwardListPresentBeanToPage(postList, WebAttributeConstant.SEARCH_FOR_POST_PRESENT_BEAN);
		return ActionResultConstant.SEARCHED_POST_RECORD_RESULT_PAGE;
	}

	@Override
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		
	}
}

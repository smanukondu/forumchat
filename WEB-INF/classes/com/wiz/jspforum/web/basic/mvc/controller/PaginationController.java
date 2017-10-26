
package com.wiz.jspforum.web.basic.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiz.jspforum.common.model.PagingListModel;
import com.wiz.jspforum.util.log.CommonLog;

@Controller
@RequestMapping("/pagingList")
public class PaginationController<E> extends AbstractActionController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/{action}", method=RequestMethod.GET)
	public String executePagination(@PathVariable(value="action") String pagingAction, 
		@RequestParam(required=true, name="PRESENT_BEAN_NAME") String presentBeanName, 
		@RequestParam(required=true, name="RESULT_PAGE") String resultPage) {
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") start to do the paging");
		PagingListModel<E> pb = (PagingListModel<E>)session.getAttribute(presentBeanName);
		if (pagingAction.equals("first")) {
			pb.setPageIndex(0);
		} else if (pagingAction.equals("prev")) {
			if (pb.isAble2MoveToPrev()) {
				pb.setPageIndex(pb.getPageIndex() - 1);
			}
		} else if (pagingAction.equals("next")) {
			if (pb.isAble2MoveToNext()) {
				pb.setPageIndex(pb.getPageIndex() + 1);
			}
		} else if (pagingAction.equals("last")) {
			pb.setPageIndex(pb.getMaxPageNo() - 1);
		}
		logger.log(CommonLog.DEBUG, getClass().getName(), "User[" + user.getUserName() + "] (" + user.getUserType() + ") finish to do the paging");
		return resultPage;
	}

	@Override
	public void injectSessionLoggerToBdos(HttpServletRequest request) {
		
	}
}


package com.wiz.jspforum.util.search;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

public interface SearchManager {
	
	public void init();

	public void create(UserPost post);

	public void update(UserPost post);

	public SearchResult search(SearchArgs args);

	public void delete(UserPost p);
}

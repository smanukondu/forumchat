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

package com.wiz.jspforum.persistence.basic.data.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The Hibernate utility for the function initialization
 */
public class HibernateUtil {

	private SessionFactory sessionFactory = null;
	private static HibernateUtil instance = null;

	/** 
	 * The Singleton pattern for initializing the instance
	 * 
	 * @return the instance of HibernateUtil
	 */
	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
			instance.setSessionFactory(new Configuration().configure().buildSessionFactory());
		}
		return instance;
	}

	/** 
	 * set method for the object of SessionFactory
	 * 
	 * @param the object of SessionFactory
	 */
	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** 
	 * get method for the object of SessionFactory
	 * 
	 * @return the object of SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
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

package com.wiz.jspforum.bizservice.logic.basic.bdo;

import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The utility interface of supporting the session logger object for BDO
 */
public interface AbstractBdoInterface {
	/**
	 * The interface which be exploded to the BDO implement instance for setting the object of logger
	 * 
	 * @param logger the instance of logger which be attached in the session since the user signing on
	 */
	void setSessionLogger(SimpleLog logger);	
}

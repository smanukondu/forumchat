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

package com.wiz.jspforum.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The global utility class to process date or date - format string 
 */
public class DateSimpleFormat {

	private final static String SIMPLE_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
	private final static String SIMPLE_DATETIME_FORMATE = "yyyyMMddHHmmss";
	private final static String SHORT_SIMPLE_DATE_FORMATE = "yyyy-MM-dd";
//	private final static String SIMPLE_DATETIME_FORMATE_WITHOUT_HYPHON = "yyyyMMdd";

	/**
	 * Process the simple Date Format without Hyphen
	 * 
	 * @param date the date
	 * 
	 * @return the string with '-'
	 */
	public final static String simpleDateFormatWithoutHyphon(String date) {
		String[] arrayDate = date.split("-");
		return arrayDate[0] + arrayDate[1] + arrayDate[2];
	}

	/**
	 * Process date object as the simple case format 'yyyy-MM-dd HH:mm:ss' and output string
	 * 
	 * @param date the date object
	 * 
	 * @return the simple date string
	 */
	public final static String formate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMATE);
		return sdf.format(date);
	}

	/**
	 * Process date object as the simple case format 'yyyy-MM-dd' and output string
	 * 
	 * @param date the date object
	 * 
	 * @return the simple date string
	 */
	public final static String simpleFormate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(SHORT_SIMPLE_DATE_FORMATE);
		return sdf.format(date);
	}

	/**
	 * Process the simple date format 'yyyy-MM-dd' string and convert it to the date object
	 * 
	 * @param inputdate_str the input date string
	 * 
	 * @return the simple date object
	 */
	public final static Date simpleFormate(String inputdate_str) {
		SimpleDateFormat sdf = new SimpleDateFormat(SHORT_SIMPLE_DATE_FORMATE);
		Date d = null;
		try {
			d = sdf.parse(inputdate_str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * Process the simple date format 'yyyy-MM-dd' string and convert it to the date object
	 * 
	 * @param dateStr the date string
	 * 
	 * @return the simple date object
	 */
	public final static Date getSimpleDateValue(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(SHORT_SIMPLE_DATE_FORMATE);
		return sdf.parse(dateStr);
	}

	/**
	 * Process date object as the simple case format 'yyyyMMddHHmmss' and output string
	 * 
	 * @param date the date object
	 * 
	 * @return the simple date string
	 */
	public final static String getSimpleDatetimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATETIME_FORMATE);
		return sdf.format(date);
	}
}

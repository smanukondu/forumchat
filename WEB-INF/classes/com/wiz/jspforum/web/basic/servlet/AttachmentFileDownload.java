
package com.wiz.jspforum.web.basic.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AttachmentFileDownload extends HttpServlet {

	public AttachmentFileDownload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file = request.getParameter("file");
		// reads input file from an absolute path
		File downloadFile = new File(file);
		FileInputStream inStream = new FileInputStream(downloadFile);
		// obtains ServletContext
		ServletContext context = request.getServletContext();
		// gets MIME type of the file
		String mimeType = context.getMimeType(file);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		// modifies response
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		// forces download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		// obtains response's output stream
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[1024*4];
		int bytesRead = -1;
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inStream.close();
		outStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

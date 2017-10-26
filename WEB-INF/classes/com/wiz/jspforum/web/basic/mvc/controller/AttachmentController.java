
package com.wiz.jspforum.web.basic.mvc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@RequestMapping(value="/common/downloadimage", method=RequestMethod.GET)
	public void downloadAndShowImage(@RequestParam(required=true, name="image") String image, HttpServletResponse response) throws IOException {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		String folderPath = rb.getString("RESOURCE_POST_ATTACHMENT_PATH");
		String fullServerFilePath = folderPath + image;
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(fullServerFilePath);
		byte[] buffer = new byte[1024*14];
		int length = 0;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
	}
}

package com.yy.ueditor.web;

import com.yy.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class UEditorController {

	@RequestMapping("/config")
	@ResponseBody
	public void getConfigInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json");
			String exec = new ActionEnter(request).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

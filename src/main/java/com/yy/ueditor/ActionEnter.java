package com.yy.ueditor;


import com.google.gson.Gson;
import com.yy.ueditor.define.ActionMap;
import com.yy.ueditor.define.AppInfo;
import com.yy.ueditor.define.BaseState;
import com.yy.ueditor.define.State;
import com.yy.ueditor.hunter.FileManager;
import com.yy.ueditor.hunter.ImageHunter;
import com.yy.ueditor.upload.Uploader;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionEnter {
	private String actionType = null;
	private ConfigManager configManager = null;
	private HttpServletRequest request = null;

	public ActionEnter(HttpServletRequest request) {
		this.request = request;
		this.actionType = request.getParameter("action");
		this.configManager = ConfigManager.getInstance();
	}

	public String exec() {
		String callbackName = this.request.getParameter("callback");
		if (callbackName != null) {
			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}
			return callbackName + "(" + this.invoke() + ");";
		} else {
			return this.invoke();
		}
	}

	public String invoke() {
		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}

		Gson gson = new Gson();
		if (this.configManager == null || StringUtils.isBlank(gson.toJson(this.configManager.getAllConfig()))) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		State state = null;
		int actionCode = ActionMap.getType(this.actionType);
		Map<String, Object> conf = null;
		switch (actionCode) {
		case ActionMap.CONFIG:
			return gson.toJson(this.configManager.getAllConfig());
		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = this.configManager.getConfig(actionCode);
			state = new Uploader(request, conf).doExec();
			break;
		case ActionMap.CATCH_IMAGE:
			conf = configManager.getConfig(actionCode);
			String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;
		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			int start = this.getStartIndex();
			state = new FileManager(conf).listFile(start);
			break;
		}

		return state.toJSONString();
	}

	private int getStartIndex() {
		String start = this.request.getParameter("start");
		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * callback参数验证
	 */
	private boolean validCallbackName(String name) {
		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}
		return false;
	}

}
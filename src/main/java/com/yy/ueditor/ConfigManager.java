package com.yy.ueditor;

import cn.hutool.core.io.file.FileReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yy.ueditor.define.ActionMap;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取并解析config.json配置文件
 *
 */
public final class ConfigManager {

	private JsonObject jsonObject = null;
	private final static String CONFIG_FILE_NAME = "config.json";
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";

	public static ConfigManager getInstance() {
		try {
			return new ConfigManager();
		} catch (Exception e) {
			return null;
		}
	}

	public JsonObject getAllConfig() {
		return this.jsonObject;
	}

	public Map<String, Object> getConfig(int type) {
		Map<String, Object> conf = new HashMap<String, Object>();

		switch (type) {
		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonObject.get("fileMaxSize").getAsLong());
			conf.put("allowFiles", this.jsonObject.get("fileAllowFiles").getAsJsonArray());
			conf.put("fieldName", this.jsonObject.get("fileFieldName").getAsString());
			conf.put("savePath", this.jsonObject.get("filePathFormat").getAsString());
			break;
		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonObject.get("imageMaxSize").getAsLong());
			conf.put("allowFiles", this.jsonObject.get("imageAllowFiles").getAsJsonArray());
			conf.put("fieldName", this.jsonObject.get("imageFieldName").getAsString());
			conf.put("savePath", this.jsonObject.get("imagePathFormat").getAsString());
			break;
		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", this.jsonObject.get("videoMaxSize").getAsLong());
			conf.put("allowFiles", this.jsonObject.get("videoAllowFiles").getAsJsonArray());
			conf.put("fieldName", this.jsonObject.get("videoFieldName").getAsString());
			conf.put("savePath", this.jsonObject.get("videoPathFormat").getAsString());
			break;
		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
			conf.put("maxSize", this.jsonObject.get("scrawlMaxSize").getAsLong());
			conf.put("fieldName", this.jsonObject.get("scrawlFieldName").getAsString());
			conf.put("isBase64", "true");
			conf.put("savePath", this.jsonObject.get("scrawlPathFormat").getAsString());
			break;
		case ActionMap.CATCH_IMAGE:
			conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
			conf.put("filter", this.jsonObject.get("catcherLocalDomain").getAsJsonArray());
			conf.put("maxSize", this.jsonObject.get("catcherMaxSize").getAsLong());
			conf.put("allowFiles", this.jsonObject.get("catcherAllowFiles").getAsJsonArray());
			conf.put("fieldName", this.jsonObject.get("catcherFieldName").getAsString() + "[]");
			conf.put("savePath", this.jsonObject.get("catcherPathFormat").getAsString());
			break;
		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", this.jsonObject.get("imageManagerAllowFiles").getAsJsonArray());
			conf.put("dir", this.jsonObject.get("imageManagerListPath").getAsString());
			conf.put("count", this.jsonObject.get("imageManagerListSize").getAsInt());
			break;
		case ActionMap.LIST_FILE:
			conf.put("allowFiles", this.jsonObject.get("fileManagerAllowFiles").getAsJsonArray());
			conf.put("dir", this.jsonObject.get("fileManagerListPath").getAsString());
			conf.put("count", this.jsonObject.get("fileManagerListSize").getAsInt());
			break;
		}

		conf.put("rootPath", this.jsonObject.get("rootPath").getAsString());
		return conf;
	}

	private void initEnv() throws FileNotFoundException, IOException {
		File file = ResourceUtils.getFile("classpath:" + CONFIG_FILE_NAME);
		FileReader fileReader = new FileReader(file);
		String result = fileReader.readString();
		Gson gson = new Gson();
		this.jsonObject = gson.fromJson(filter(result), JsonObject.class);
	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {
		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
	}

	private ConfigManager() throws FileNotFoundException, IOException {
		this.initEnv();
	}
}

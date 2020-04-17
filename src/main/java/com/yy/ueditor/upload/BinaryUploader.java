package com.yy.ueditor.upload;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yy.ueditor.PathFormat;
import com.yy.ueditor.define.AppInfo;
import com.yy.ueditor.define.BaseState;
import com.yy.ueditor.define.FileType;
import com.yy.ueditor.define.State;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

public class BinaryUploader {
	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(conf.get("fieldName").toString());
		if (file == null || file.isEmpty()) {
			return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
		}

		long maxSize = ((Long) conf.get("maxSize")).longValue();
		if (file.getSize() > maxSize) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String originFileName = file.getOriginalFilename();
		String suffix = FileType.getSuffixByFilename(originFileName);
		if (!validType(suffix, conf.get("allowFiles").toString())) {
			return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
		}

		originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
		String savePath = (String) conf.get("savePath");
		savePath = savePath + suffix;
		savePath = PathFormat.parse(savePath, originFileName);
		String physicalPath = (String) conf.get("rootPath") + savePath;
		File targetFile = new File(physicalPath);
		// 检测是否存在目录
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}

		if (targetFile.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}

		try {
			file.transferTo(targetFile);
			State storageState = new BaseState(true);
			storageState.putInfo("size", targetFile.length());
			storageState.putInfo("title", targetFile.getName());
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", originFileName + suffix);

			return storageState;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		}
	}

	private static boolean validType(String type, String allowTypes) {
		Gson gson = new Gson();
		List<String> list = gson.fromJson(allowTypes, new TypeToken<List<String>>() {}.getType());
		return list.contains(type);
	}
}

package com.fymod.qiniu;

import com.fymod.qiniu.config.APPConstants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class Test {

	public static void uploadToken() {
		String bucket = "bucket-test";
		Auth auth = Auth.create(APPConstants.AK, APPConstants.SK);
		String upToken = auth.uploadToken(bucket);
		System.out.println(upToken);
	}

	public static void main(String[] args) {
//		serverUpload("/Users/dev/Desktop/logo.jpg", null);
//		getFilePath("FiFBzz5H5tn-c_vOoa5DFipdp0PF");
		uploadToken();
	}
	
	/**
	 * @param filePath 如果是Windows情况下，格式是 D:\\qiniu\\test.png
	 * @param filePath 如果是linux或mac，格式是/Users/dev/Desktop/logo.jpg
	 * @param key 如果为null，文件名就是以文件内容的hash值。如果指定，上传文件名就是key，如logo.png
	 */
	public static void serverUpload(String filePath, String key) {
		//华北是zone1, [华东z0] [华南z2] [北美na0] [东南亚as0]
		Configuration cfg = new Configuration(Zone.zone1());
		UploadManager uploadManager = new UploadManager(cfg);
		//生成上传凭证，然后准备上传
		String accessKey = APPConstants.AK;
		String secretKey = APPConstants.SK;
		String bucket = "bucket-test";
		
		String localFilePath = filePath;
		
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		        //ignore
		    }
		}
	}
	
	public static void getFilePath(String fileName) {
		
		String publicUrl = APPConstants.defaultUrl + "/" + fileName;
		
		Auth auth = Auth.create(APPConstants.AK, APPConstants.SK);
		long expireInSeconds = 3600; //1小时，可以自定义链接过期时间
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		System.out.println(finalUrl);
	}
	
	
}

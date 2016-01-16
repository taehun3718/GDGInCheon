package com.sqisoft.ssbr.download.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import com.sqisoft.ssbr.download.service.VirusDownloadService;

public class VirusDownloadServiceImpl implements VirusDownloadService {

	@Override
	public HttpServletResponse downloadFile(HttpServletRequest request
											, HttpServletResponse response
											, String downloadName) {
		//File downloadFile = new File("C:\\app\\" + engineName);
		//File downloadFile = new File("C:\\app\\" + "readme.txt");
		File downloadFile = new File("/www/virusData/" + downloadName);
		response.setContentType("applicaton/download; charset=utf-8");
		response.setContentLength((int)downloadFile.length());
		
		
		// 사용자의 브라우저 종류를 가져온다
		String userAgent = request.getHeader("User-Agent");
		boolean isMsie = userAgent.indexOf("MSIE") > -1;
		
		String fileName = null;
		
		try {
			if ( isMsie ) {
				fileName = URLEncoder.encode(downloadName, "UTF-8");
			}
			
			else {
				fileName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			OutputStream out = response.getOutputStream();
			FileInputStream fis = null;
			
			try { 
				fis = new FileInputStream(downloadFile);
				FileCopyUtils.copy(fis, out);
				out.flush();
			}
			
			finally {
				if ( fis != null ) {
					fis.close();
				}
				if ( out != null ) {
					fis.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}

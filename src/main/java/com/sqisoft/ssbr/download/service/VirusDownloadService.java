package com.sqisoft.ssbr.download.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VirusDownloadService {
	/**
	 * 
	 * 1. 바이러스 엔진을 다운 받는 작업을 수행한다. (완료)
	 * 2. 바이러스 엔진을 받고 나서 사용자의 이력을 DB에 기록한다.
	 * @return HttpServletResponse
	 */
	public HttpServletResponse downloadFile(	HttpServletRequest request,
												HttpServletResponse response, 
												String engineName);
}

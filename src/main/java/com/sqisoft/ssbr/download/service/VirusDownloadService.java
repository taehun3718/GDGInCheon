package com.sqisoft.ssbr.download.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VirusDownloadService {
	/**
	 * 
	 * 1. ���̷��� ������ �ٿ� �޴� �۾��� �����Ѵ�. (�Ϸ�)
	 * 2. ���̷��� ������ �ް� ���� ������� �̷��� DB�� ����Ѵ�.
	 * @return HttpServletResponse
	 */
	public HttpServletResponse downloadFile(	HttpServletRequest request,
												HttpServletResponse response, 
												String engineName);
}

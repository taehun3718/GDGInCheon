package com.sqisoft.ssbr.util;

public class Paging implements Cloneable {

	/**
	 * �˻��� �Խù��� �� ����
	 */
	private int totalArticleCount;
	
	/**
	 * �� ������ ���� ������ �Խù��� ����
	 */
	private int printArticle;
	
	/**
	 * �� �׷쿡 ������ �������� ����
	 */
	private int printPage;
	
	/**
	 * ��������ȣ�� �Ѱ� �޾��� ��
	 * �ڵ����� ���Ǵ� �Խñ��� ���� ��ȣ
	 */
	private int startArticleNumber;
	
	/**
	 * ��������ȣ�� �Ѱ� �޾��� ��
	 * �ڵ����� ���Ǵ� �Խñ��� ������ ��ȣ
	 */
	private int endArticleNumber;

	/**
	 * �˻��� �Խñ��� ����¡ �Ǿ��� �� ������ �� ������ ��
	 * �ø� (�˻��� �Խñ� ���� / �� ���������� ������ �Խñ� ����)
	 */
	private int totalPage;
	
	/**
	 * �˻��� �Խñ��� ����¡ �Ǿ��� �� ������ �� �׷��� ��
	 * �ø� (�� ������ �� / �� �׷쳻�� ������ ������ ����)
	 */
	private int totalGroup;

	/**
	 * ���� �������� ���� �׷��� ��ȣ
	 */
	private int nowGroupNumber;

	/**
	 * ���� �������� ���� �׷��� ���� ������ ��ȣ
	 */
	private int groupStartPage;

	/**
	 * ���� �׷쿡�� ���� �׷����� �Ѿ�� ��
	 * �� �׷쿡�� ���۵Ǵ� �������� ���۹�ȣ
	 */
	private int nextGroupPageNumber;
	
	/**
	 * ���� �׷쿡�� ���� �׷����� �Ѿ�� ��
	 * �� �׷쿡�� ���۵Ǵ� �������� ���� ��ȣ
	 */
	private int prevGroupPageNumber;

	/**
	 * ���� �������� ��ȣ
	 * �⺻���� 0
	 */
	private int pageNo;

	public Paging() {
		this.printArticle = 7;
		this.printPage = 4;
	}
	
	public Paging(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		this.startArticleNumber = (this.pageNo * this.printArticle) + 1;
		this.endArticleNumber = this.startArticleNumber + this.printArticle - 1;

		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}

	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}

	public int getPageNo() {
		return this.pageNo;
	}
	
	public int getTotalCount() {
		return this.totalArticleCount;
	}

	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}

	public int getEndArticleNumber() {
		return this.endArticleNumber;
	}

	public String getPagingList(String link, String pageFormat, String prev,
			String next, String moreParams) {

		StringBuffer buffer = new StringBuffer();

		if (this.nowGroupNumber > 0) {
			buffer.append("<li><a href=\"?" + link + "=" + this.prevGroupPageNumber
					+ "&" + moreParams + "\">" + prev + "</a></li>");
		}

		int nextPrintPage = this.groupStartPage + this.printPage;
		if (nextPrintPage > this.totalPage) {
			nextPrintPage = this.totalPage + 1;
		}

		String pageNumber = "";

		for (int i = this.groupStartPage; i < nextPrintPage; i++) {
			pageNumber = pageFormat.replaceAll("@", i + "");
			if ((i - 1) == this.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			buffer.append("<li><a href=\"?" + link + "=" + (i - 1) + "&"
					+ moreParams + "\">" + pageNumber + "</a></li>");
		}

		if (this.nowGroupNumber < (this.totalGroup - 1)) {
			buffer.append("<li><a href=\"?" + link + "=" + this.nextGroupPageNumber
					+ "&" + moreParams + "\">" + next + "</a></li>");
		}

		return buffer.toString();
	}
	
	public Paging getClone() {
		Paging paging = null;
		
		try {
			paging = (Paging) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return paging;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		Paging paging = new Paging(this.printArticle, this.printPage);
		paging.setPageNumber(this.pageNo + "");
		paging.setTotalArticleCount(this.totalArticleCount);
		
		return paging;
	}

}
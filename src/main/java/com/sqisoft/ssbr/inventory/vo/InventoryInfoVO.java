package com.sqisoft.ssbr.inventory.vo;
/**
 * ----------------------------------------------------------------
 * 재고 시퀀스(inventSeqno) – INT: 1.2.3.4.5…
 * 재고 종류(inventCategory) – VARCHAR(20): 서버/스토리지/CD-R/DVD-R/DISK/ETC
 * 재고 이름(inventName) – VARCHAR(100) : 보안사업팀 시디
 * 재고 수량(inventNum) – INT : 3
 * 재고 메모(inventMemo) – VARCHAR(500) : 납품용 / BMT / CC인증용 
 * 최근 업데이트 일자(inventUpdt) – DATE : 2016-10-11
 * 삭제여부 (inventDeleteYN) CHAR(1) : Y/N
 * ------------------------------------------------------------------
 * Title	:	InventoryInfoVO ValueObject
 * @author TaeHoonKim
 *
 */
public class InventoryInfoVO {
	private int		inventSeqno;
	private String	inventCategory;
	private String	inventName;
	private int 	inventNum;
	private String	inventMemo;
	private String	inventUpdt;
	private String	inventDeleteYN;
	
	public int getInventSeqno() {
		return inventSeqno;
	}
	public void setInventSeqno(int inventSeqno) {
		this.inventSeqno = inventSeqno;
	}
	public String getInventCategory() {
		return inventCategory;
	}
	public void setInventCategory(String inventCategory) {
		this.inventCategory = inventCategory;
	}
	public String getInventName() {
		return inventName;
	}
	public void setInventName(String inventName) {
		this.inventName = inventName;
	}
	public int getInventNum() {
		return inventNum;
	}
	public void setInventNum(int inventNum) {
		this.inventNum = inventNum;
	}
	public String getInventMemo() {
		return inventMemo;
	}
	public void setInventMemo(String inventMemo) {
		this.inventMemo = inventMemo;
	}
	public String getInventUpdt() {
		return inventUpdt;
	}
	public void setInventUpdt(String inventUpdt) {
		this.inventUpdt = inventUpdt;
	}
	public String getInventDeleteYN() {
		return inventDeleteYN;
	}
	public void setInventDeleteYN(String inventDeleteYN) {
		this.inventDeleteYN = inventDeleteYN;
	}
	
	
	
}

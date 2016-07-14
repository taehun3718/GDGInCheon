package com.sqisoft.ssbr.member.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.member.service.MemberService;
import com.sqisoft.ssbr.member.vo.MemberVO;
import com.sqisoft.ssbr.util.LoginAttr;
import com.sqisoft.ssbr.util.SessionStore;

public class MemberServiceImpl implements MemberService{

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	/**
	 * �α����� �����ϴ� �޼ҵ��̴�. DB���� �о� �鿩�� ������ true ������ false ������ �Ѵ�.
	 */
	@Override
	public boolean doLogin(MemberVO memberVO
							, HttpServletRequest request
							, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		//DO login (DB)
		MemberVO vo  = memberDAO.doLogin(memberVO);
		
		boolean logined = vo == null ? false : true;
		if(logined) {
			SessionStore sessionStore = SessionStore.getInstance();
			
			if(!sessionStore.isExists(memberVO.getId())) {
				session.setAttribute(LoginAttr.MEMBER_ATTR, vo);
				sessionStore.putSession(memberVO.getId(), session);
				return true;
			}
			else {
				HttpSession sess = sessionStore.getSession(memberVO.getId());
				if(sess==null) {
					sessionStore.removeSession(memberVO.getId());
				}
				else{
					sessionStore.getSession(memberVO.getId()).invalidate();
				}
				sessionStore.removeSession(memberVO.getId());
				session.setAttribute(LoginAttr.MEMBER_ATTR, vo);
				//...
				sessionStore.putSession(memberVO.getId(), session);
				
				return true;
			}
			/** �α��ν� ����ڿ��� �α��� ���θ� ���� �α� �ƿ��� �ϰ� �Ϸ��� ���, ���� �������� �ʰ� ����.**/
			/*else {
				int  i = JOptionPane
							.showConfirmDialog(null
									, "�̹� �α��� �Ǿ� �ִ� ���̵��Դϴ�. �α� �ƿ��� �Ͻðڽ��ϱ�?"
									, "�α��� Ȯ��"
									, JOptionPane.YES_NO_OPTION);
				//������ ������ ���� �ٸ� ������ ������ �ֱ� ������, Invalidate �� removeSession ó���� �ؾ� �ϸ�
				//���� �������� ��� �α����� �� ���� ���, �α��� �������� ���� �ʰ� �ٸ� �������� ������ ó���ؾ� ��. 
				if(i==0) {
					sessionStore.getSession(memberVO.getId()).invalidate();
					sessionStore.removeSession(memberVO.getId());
					session.setAttribute(LoginAttr.MEMBER_ATTR, memberVO);
					//...
					sessionStore.putSession(memberVO.getId(), session);
					
					return true;
				}
				else if(i==1){
					return false;
				}
			}*/
		}
		return false;
	}
	
	@Override
	public void doLogout(HttpServletRequest request) {
		SessionStore sessionStore = SessionStore.getInstance();
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute(LoginAttr.MEMBER_ATTR);
		
		if(memberVO!=null){
			sessionStore.getSession(memberVO.getId()).invalidate();
			sessionStore.removeSession(memberVO.getId());
		}
	}
}
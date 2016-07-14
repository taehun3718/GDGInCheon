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
	 * 로그인을 수행하는 메소드이다. DB에서 읽어 들여서 있으면 true 없으면 false 리턴을 한다.
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
			/** 로그인시 사용자에게 로그인 여부를 묻고 로그 아웃을 하게 하려는 기능, 현재 구현하지 않고 있음.**/
			/*else {
				int  i = JOptionPane
							.showConfirmDialog(null
									, "이미 로그인 되어 있는 아이디입니다. 로그 아웃을 하시겠습니까?"
									, "로그인 확인"
									, JOptionPane.YES_NO_OPTION);
				//세션은 브라우져 마다 다른 세션을 가지고 있기 때문에, Invalidate 및 removeSession 처리를 해야 하며
				//같은 브라우져일 경우 로그인이 되 있을 경우, 로그인 페이지로 가지 않고 다른 페이지로 가도록 처리해야 함. 
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
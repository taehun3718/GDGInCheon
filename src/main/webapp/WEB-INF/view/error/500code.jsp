<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isErrorPage="true" %>
500 알 수 없는 에러가 발생하였습니다.
해당 메세지를 시스템 담당자에게 전달해주십시오.<br/>

에러 타입 :   <%= exception.getClass().getName() %><br>
에러 메세지 : <%= exception.getMessage() %>


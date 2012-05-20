<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%><%@ 
page trimDirectiveWhitespaces="true"%><%@ 
taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ 
taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var='clntInfo' value='${actionBean.clientInfo}' />
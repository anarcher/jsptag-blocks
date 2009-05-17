<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="springsprout.blocks" prefix="b" %>
<c:out value="${pageContext.request.pathInfo}" />
<c:url value="http://example.com/"></c:url>
<b:blocks>
1
    <b:block name="test">test</b:block>
2
</b:blocks>

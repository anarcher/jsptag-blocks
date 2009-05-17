<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="springsprout.blocks" prefix="b" %>
<b:blocks>
<html>
<body>
<c:out value="${pageContext.request.pathInfo}" />
<b:block name="block1"><div>this is block1 in base.jsp </div></b:block>
<b:block name="block2"><div>this is block2 in base.jsp </div></b:block>
</body>
</html>
</b:blocks>

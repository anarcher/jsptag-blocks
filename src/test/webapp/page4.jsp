<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="springsprout.blocks" prefix="b" %>
<c:set var="pagename"  value="base.jsp" />
<b:blocks with="${ pagename }">
<b:block name="block2">
    <div>this is block2 in page4.jsp </div>
</b:block>
<b:block name="block1"></b:block>
</b:blocks>

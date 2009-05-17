<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="springsprout.blocks" prefix="b" %>
<b:blocks with="page1.jsp">
<b:block name="block2">
    <div>this is block1 in page2.jsp </div>
</b:block>
</b:blocks>

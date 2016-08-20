<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="include.inc.jsp"%>
<c:set var="targetType" value="${empty param.targetType ? 'navTab' : param.targetType}"/>
<div class="panelBar">
	<div class="pages">
		<span>Per Page</span>
		<select name="pageSize" onchange="dwzPageBreak({targetType:'${targetType}',data:{numPerPage:this.value}})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${list.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>

		<span>Total: ${list.total}</span>
	</div>
	
	<div class="pagination" targetType="${targetType}" totalCount="${list.total}" numPerPage="${list.pageSize}" pageNumShown="10" currentPage="${list.pageNum}"></div>
</div>
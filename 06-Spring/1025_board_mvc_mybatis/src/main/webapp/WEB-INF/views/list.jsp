<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 사이트</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<table>
		<tr>
			<th>code</th>
			<th>모델</th>
			<th>가격</th>
		</tr>
		<c:forEach var="product" items="${list}">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/board/detail?code=${product.code}">${product.code}</a></td>
				<td>${product.model}</td>
				<td>${product.price}</td>
				<td><label class="checkbox-inline"> <input
						type="checkbox" name="chk" class="chk" onclick="chkClicked()">
				</label></td>
			</tr>
		</c:forEach>
	</table>

	<script>
		function chkClicked() {

			//체크된 체크박스 전체개수
			const query = 'input[name="chk"]:checked'
			const selectedElements = document.querySelectorAll(query)
			const selectedElementsCnt = selectedElements.length;

			if (selectedElementsCnt > 0) {
				if (confirm("정말로 삭제하시겠습니까?")) {
					//배열생성
					const arr = new Array(selectedElementsCnt);

					document.querySelectorAll('input[name="chk"]:checked').forEach(function(v, i) {
								arr[i] = v.value;
							});
					const form = document.createElement('form');
					form.setAttribute('method', 'post'); //Post 메소드 적용
					form.setAttribute('action', '/delete');

					var input1 = document.createElement('input');
					input1.setAttribute("type", "hidden");
					input1.setAttribute("name", "boardIds");
					input1.setAttribute("value", arr);
					form.appendChild(input1);
					console.log(form);
					document.body.appendChild(form);
					form.submit();
				}
			}
		}}
	</script>
</body>
</html>
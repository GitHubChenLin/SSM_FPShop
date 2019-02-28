<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>

<style>
a, a:hover {
	text-decoration: none;
}

.add_table td {
	height: 50px;
	font-size: 18px;
}

input {
	font-size: 18px;
}

.add_table tr {
	margin: 10px 0;
}

.add_button {
	display: inline-block;
	width: 70px;
	height: 45px;
	line-height: 45px;
	background-color: rgb(247, 132, 131);
	text-align: center;
}

.info_table {
	margin-top: 20px;
	width: 97%;
	border: 1px solid rgb(247, 132, 131);
	table-layout: fixed;
	border-collapse: collapse;
}

.info_table tr {
	border-bottom: 1px solid rgb(247, 132, 131);
}

.info_table th {
	height: 40px;
	padding: 0 5px;
	background-color: rgb(249, 172, 172);
	text-align: left;
}

.info_table td {
	padding: 5px;
	word-wrap: break-word;
	word-break: break-all;
}

.col-man {
	width: 95px;
}

.col-add {
	width: 250px;
}

.col-tel {
	width: 120px;
}

.col-action {
	
}

.modify, .del {
	cursor: pointer;
	color: rgb(110, 126, 141);
}
</style>
<script>
	$(function() {
		$(".modify").click(function() {
			var id = $(this).attr("data-aid");

			var x = document.getElementsByClassName(id);
			var td1 = x[0].innerHTML;//name
			var td2 = x[1].innerHTML;//add
			var td3 = x[2].innerHTML;//tel
			var td4 = x[3].innerHTML;//defaultAdd
			var td5 = x[4].innerHTML;//aid
			$("input[name = 'name']").val(td1);
			$("input[name = 'address']").val(td2);
			$("input[name = 'tel']").val(td3);
			$("input[name = 'aid']").val(td5);

			$(".addOrUpdate").html("修改地址");
		});

		$(".del").click(function() {
			var id = $(this).attr("data-aid");
			if(confirm("确定删除该地址吗？")){
				location.href="${pageContext.request.contextPath}/user/delAdd?aid="+id;
			}
		})
	});
	function setDefault(aid) {
		location.href="${pageContext.request.contextPath}/user/setDefaultAdd?aid="+aid;
	}
</script>
<title>地址管理</title>
</head>
<body>
	<div class="add">
		<div
			style="font-size: 20px; color: rgb(110, 126, 141); margin-bottom: 30px;">地址管理
			Address Management</div>
		<div class="addOrUpdate"
			style="font-size: 15px; color: rgb(241, 55, 53);">新增地址</div>
		<form class="add_form"
			action="${pageContext.request.contextPath}/user/addManage"
			method="POST">
			<table class="add_table">
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="aid" style="display: none;">
						<input type="text" name="name" style="width: 300px;"></td>
				</tr>
				<tr>
					<td>详细地址：</td>
					<td><input type="text" name="address" style="width: 300px;"></td>
				</tr>
				<tr>
					<td>联系方式：</td>
					<td><input type="text" name="tel" style="width: 300px;"></td>
				</tr>
				<!-- <tr>
					<td colspan="2"><input type="checkbox" name="defaultAdd"
						value="1"><span> 设置为默认收货地址</span></td>
				</tr> -->
				<tr>
					<td colspan="2">
						<!-- <div class="add_button">保存</div> --> <input type="submit"
						value="保存">
					</td>
				</tr>
			</table>
		</form>

		<div class="new">
			<table class="info_table" border="0" cellspacing="0" cellpadding="0">
				<colgroup>
					<col class="col-man">
					<col class="col-add">
					<col class="col-tel">
					<col class="col-actions">
				</colgroup>
				<tr>
					<th>收货人</th>
					<th>详细地址</th>
					<th>联系方式</th>
					<th>操作</th>
					<th></th>
				</tr>
				<c:forEach var="add" items="${addList }">
					<tr>
						<td class="${add.aid }">${add.name }</td>
						<td class="${add.aid }">${add.address }</td>
						<td class="${add.aid }">${add.tel }</td>
						<td class="${add.aid }" style="display: none;">${add.defaultAdd }</td>
						<td class="${add.aid }" style="display: none;">${add.aid }</td>

						<td><span class="modify" data-aid="${add.aid }">修改</span> <span>
								| </span> <span class="del" data-aid="${add.aid }">删除</span></td>
						<td class="choosen" style = "text-align: center;"><c:choose>
								<c:when test="${add.defaultAdd == true }">
									<div
										style="text-align: center; margin: 0 auto; display: block; width: 70px; height: 30px; line-height: 30px; font-size: 14px; color: #adadad; background-color: rgb(241, 55, 53)">默认地址</div>
								</c:when>
								<c:when test="${add.defaultAdd == false }">
									<a href="javascript:;" onclick="setDefault('${add.aid}')" style = "font-size:14px;">设置默认</a>
								</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>
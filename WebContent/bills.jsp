<%@page import="model.bills" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h1>Bill Management</h1>
				<form id="formItem" name="formItem">
					Account ID: <input id="accountID"
						name="accountID" type="text" class="form-control form-control-sm"> <br>
					Account name: <input id="accountName" name="accountName"
						type="text" class="form-control form-control-sm"> <br> Bill
					price: <input id="billPrice" name="billPrice"
						type="text" class="form-control form-control-sm"> <br> Bill unit count:
					<input id="billUnit" name="billUnit" type="text"
						class="form-control form-control-sm"> <br> Date: <input
						id="billDate" name="billDate" type="text"
						class="form-control form-control-sm"> <br> <input id="btnSave"
						name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidBillIDSave" name="hidBillIDSave"
						value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
				<%
				bills billObj = new bills();
 					out.print(billObj.readBills());
 					%>
				
	
				</div>
			</div>
		</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/bills.js" type="text/javascript"></script>
</html>
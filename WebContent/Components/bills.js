//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidBillIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "BillsAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onBillSaveComplete(response.responseText, status);
 }
 });
 
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// Account id
if ($("#accountID").val().trim() == "")
 {
 return "Insert Account ID.";
 }
// Account name
if ($("#accountName").val().trim() == "")
 {
 return "Insert Account name.";
 }
// Bill price
if ($("#billPrice").val().trim() == "")
 {
 return "Insert Bill price.";
 }
// is numerical value
var tmpContact = $("#billPrice").val().trim();
if (!$.isNumeric(tmpContact))
 {
 return "Insert a numerical value for Bill price.";
 }

// Bill unit
if ($("#billUnit").val().trim() == "")
 {
 return "Insert bill Unit count.";
 }
// Bill date
if ($("#billDate").val().trim() == "")
 {
 return "Insert date.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidBillIDSave").val($(this).data("billid"));
 $("#accountID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#accountName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#billPrice").val($(this).closest("tr").find('td:eq(2)').text());
 $("#billUnit").val($(this).closest("tr").find('td:eq(3)').text());
 $("#billDate").val($(this).closest("tr").find('td:eq(4)').text());
});

function onBillSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divItemsGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidBillIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("billid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "BillsAPI",
 type : "DELETE",
 data : "billID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onBillDeleteComplete(response.responseText, status);
 }
 });
});

function onBillDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}










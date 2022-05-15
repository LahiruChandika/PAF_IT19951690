package com;

import model.bills;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bills")
public class billService {
	
	bills billObj = new bills();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills() {
		
		return billObj.readBills();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertBills(@FormParam("accountID") String accountID,
							@FormParam("accountName") String accountName,
							@FormParam("billUnit") String billUnit,
							@FormParam("billPrice") String billPrice,
							@FormParam("billDate") String billDate)
	{
		
		String output = billObj.insertBills(accountID, accountName, billUnit, billPrice, billDate);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBills(String billData)
	{
		//Convert input string to a JSON object
		JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		
		//Read values from JSON object
		String billID = billObject.get("billID").getAsString();
		String accountID = billObject.get("accountID").getAsString();
		String accountName = billObject.get("accountName").getAsString();
		String billUnit = billObject.get("billUnit").getAsString();
		String billPrice = billObject.get("billPrice").getAsString();
		String billDate = billObject.get("billDate").getAsString();
		
		String output = billObj.updateBill(billID, accountID, accountName, billUnit, billPrice, billDate);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBills(String billData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String billID = doc.select("billID").text();
		
		String output = billObj.deleteBill(billID);
		return output;
		
	}

}
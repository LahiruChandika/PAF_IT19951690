package model;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bills {
	
	public String insertBills(String accID, String accName, String price, String unit, String date) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into bills(`billID`,`accountID`,`accountName`,`billPrice`,`billUnit`,`billDate`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, accID); 
			preparedStmt.setString(3, accName);
			preparedStmt.setString(4, price);
			preparedStmt.setString(5, unit); 
			preparedStmt.setString(6, date); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readBills();
			
			System.out.println(newItems);

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readBills() 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table class='table table-dark table-hover' border='1'><tr><th>Account Id</th><th>Account Name</th>" +
					"<th>Bill Price</th>" + 
					"<th>Bill Unit</th>" +
					"<th>Bill Date</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from bills"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String billID = Integer.toString(rs.getInt("billID")); 
				String accountID = rs.getString("accountID"); 
				String accountName = rs.getString("accountName"); 
				String billPrice = Integer.toString(rs.getInt("billPrice")); 
				String billUnit = rs.getString("billUnit");
				String billDate = rs.getString("billDate");
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidBillIDUpdate' id='hidBillIDUpdate' value='"+billID+"'>" + billID + "</td>"; 
				output += "<td>" + accountName + "</td>"; 
				output += "<td>" + billPrice + "</td>"; 
				output += "<td>" + billUnit + "</td>";
				output += "<td>" + billDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-outline-warning' data-billid='"+billID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-outline-danger' data-billid='"+billID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the bills."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateBill(String id, String accId, String name, String price, String unit, String date) 

	{ 
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE bills SET accountID=?,accountName=?,billPrice=?,billUnit=?,billDate=? WHERE billID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, accId); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, price); 
			preparedStmt.setString(4, unit);
			preparedStmt.setString(5, date);
			preparedStmt.setInt(6, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readBills();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the bill.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteBill(String billID) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from bills where billID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(billID));
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readBills();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}

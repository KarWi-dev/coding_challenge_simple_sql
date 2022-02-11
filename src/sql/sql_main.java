package sql_gui;

import java.sql.*;

public class sql_main
{
	static Connection conn = null;
	static Statement syn = null;
	static ResultSet result = null;
	
	
	public static void main(String[] args)
	{
		connectdb();
		create_ds();
	}
	
	
	public static Connection connectdb()
	{
		try
		{  
			String url = "jdbc:Sqlite:C:\\Users\\CC-Student\\eclipse-workspace\\sql_gui\\sample.db";
			conn = DriverManager.getConnection(url); 
			
			return conn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void create_ds()
	{
		conn = connectdb();
		try
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("drop table if exists person");
	        statement.executeUpdate("create table person (id integer, name string)");
	        statement.executeUpdate("insert into person values(1, 'karsten')");
	        statement.executeUpdate("insert into person values(2, 'herbert')");	
	        statement.executeUpdate("insert into person values(2, 'martin')");	
	        
	        result = statement.executeQuery("select * from person");
	        
	        while(result.next())
	        {
	        	System.out.println("name = " + result.getString("name"));
	        	System.out.println("id = " + result.getInt("id"));
	        	
	        }
		}
		catch(SQLException e)
	    {
	    	 System.err.println(e.getMessage());
	    }
		
	    finally
	    {
	        try
	        {
	        	if(conn != null)
	        		conn.close();
	        }
	        
	        catch(SQLException e)
	        {
	        	System.err.println(e.getMessage());
	        }
		
	    }
	}
}
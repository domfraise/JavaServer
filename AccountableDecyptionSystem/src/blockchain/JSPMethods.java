package blockchain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static blockchain.Database.*;

public class JSPMethods {
	public  ArrayList<String[]> adminSearch(Connection conn,String keyword,String startDate, String endDate, String startTime,String endTime){
		try{
			return Database.adminSearch(conn, keyword, startDate, endDate, startTime, endTime);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}

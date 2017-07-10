package blockchain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONObject;

public class ServerMethods {
	
	public static byte[] makeRequest(Connection conn,RequestQueue q,String company,String employee,String fileHash,String reason) throws SQLException{
		DecryptionRequest r = new DecryptionRequest(conn, company, employee, fileHash, reason);
		q.add(r);
		/*
		 * TODO insert rpc call to sgx here
		 * recive back decrypted file
		 */
		return r.getFile();

	}
	
	public static ArrayList<String[]> inspect(Connection conn,String user) throws SQLException{
		return Proofs.proveAbsence(conn, user);
	}
}

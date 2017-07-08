package blockchain;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class ServerMethods {
	public static byte[] makeRequest(Connection conn,String company,String employee,String fileHash,String reason) throws SQLException{
		String oldRoot = Database.getRoot(conn);
		Database.insertEntry(conn, fileHash); //hash of decrypted file
		Database.insertRequest(conn, company, employee, reason, fileHash, Database.getTimestamp());
	
		String newRoot  = Database.getRoot(conn);
		int index = Database.getIndex(conn, fileHash);
		JSONObject proofOfPresence = Proofs.provePresence(newRoot, index);
		JSONObject proofOfExtension = Proofs.proveExtension(conn, oldRoot, newRoot);
		byte[] file = Database.getFileBytes(conn, fileHash);
		/*
		 * TODO insert rpc call to sgx here
		 * recive back decrypted file
		 */
		return file;

	}
}

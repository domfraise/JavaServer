package blockchain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONObject;

public class RequestQueue extends LinkedList<DecryptionRequest>{
	private String oldRoot;
	private String newRoot;
	private Connection conn;
	public RequestQueue(Connection conn) throws SQLException{
		super();
		this.conn = conn;
		oldRoot = Database.getRoot(conn);
		newRoot = oldRoot;
	}
	
	public String getOldRoot() {
		return oldRoot;
	}

	public void setOldRoot(String oldRoot) {
		this.oldRoot = oldRoot;
	}

	public String getNewRoot() {
		return newRoot;
	}

	public void setNewRoot(String newRoot) {
		this.newRoot = newRoot;
	}

	@Override
	public boolean add(DecryptionRequest e){
		boolean result = super.add(e);
		try {
			e.addToDatabase();
			String rootGot = Database.getRoot(conn);
			this.newRoot = rootGot;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}catch(NullPointerException e2){
			e2.printStackTrace();
			return false;

		}
		return result;
	}
	
	@Override
	public DecryptionRequest remove(){
		DecryptionRequest request = super.remove();
		int sizeWhenAdded;
		try {
			sizeWhenAdded = Database.getSizeWhenCreated(conn, request.getRequestHash());
			oldRoot = Database.getRootSize(conn, sizeWhenAdded);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
	
	public JSONObject getProofOfExtension() throws SQLException{
		return Proofs.proveExtension(conn, oldRoot, newRoot);
	}
	
	public void sendToDevice(){
		//connect to deive & check its available
		JSONObject proofOfExtension;
		try {
			proofOfExtension = getProofOfExtension();
		} catch (SQLException e) {
			System.out.println("Cannot sent to deivce - DB connection failed");
			e.printStackTrace();
		}
		ArrayList<JSONObject> proofsOfPresence = new ArrayList<JSONObject>();
		ArrayList<byte[]> encFiles = new ArrayList<byte[]>();
		while(!this.isEmpty()){
			DecryptionRequest r = this.remove();
			proofsOfPresence.add(r.getProofOfPresence());
			encFiles.add(r.getFile());
		}
		//TODO
		//send proofs
		//recive decrypted files
		//send to client
		
		this.clear();
		
	}
	
}

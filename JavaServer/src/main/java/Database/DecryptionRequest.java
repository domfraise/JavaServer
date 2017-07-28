package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class DecryptionRequest {
	Connection conn;
	private String company;
	private String employee;
	private String fileHash;
	private byte[] file;
	private String reason;
	private Timestamp timestamp;
	private JSONObject proofOfPresence;
	/**
	 * @param company
	 * @param employee
	 * @param fileHash
	 * @param reason
	 * @throws SQLException 
	 */
	public DecryptionRequest(Connection conn,String company, String employee, String fileHash, String reason) throws SQLException  {
		this.conn = conn;
		this.company = company;
		this.employee = employee;
		this.fileHash = fileHash;
		this.reason = reason;
		this.timestamp = Database.getTimestamp();
		this.file = Database.getFileBytes(conn, fileHash);
		
	}
	/**
	 * adds file hash and request hash to the blockchain
	 * @throws SQLException
	 */
	public void addToDatabase() throws SQLException{
		Database.insertEntry(conn, fileHash);
		Database.insertRequest(conn,company, employee, fileHash, reason,timestamp);
		this.proofOfPresence = Proofs.provePresence(Database.getRoot(conn), Database.getIndex(conn, fileHash));
	}
	
	/**
	 * @return the proofOfPresence
	 */
	public JSONObject getProofOfPresence() {
		return proofOfPresence;
	}
	/**
	 * @param proofOfPresence the proofOfPresence to set
	 */
	public void setProofOfPresence(JSONObject proofOfPresence) {
		this.proofOfPresence = proofOfPresence;
	}
	private Timestamp timeWhenCompleted;
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the employee
	 */
	public String getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	/**
	 * @return the fileHash
	 */
	public String getFileHash() {
		return fileHash;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * @param fileHash the fileHash to set
	 */
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the timeWhenCompleted
	 */
	public Timestamp getTimeWhenCompleted() {
		return timeWhenCompleted;
	}
	/**
	 * @param timeWhenCompleted the timeWhenCompleted to set
	 */
	public void setTimeWhenCompleted(Timestamp timeWhenCompleted) {
		this.timeWhenCompleted = timeWhenCompleted;
	}
	/**
	 * @return the timeWhenRequested
	 */

	
	public String getRequestHash(){
		return TreeOps.hash(company.concat(employee).concat(reason).concat(fileHash).concat(timestamp.toString()));
	}
	
	@Override
	public String toString() {
		return "DecryptionRequest [company=" + company + ", employee=" + employee + ", fileHash=" + fileHash
				+ ", reason=" + reason + ", timestamp=" + timestamp + "]";
	}
}

package blockchain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

import static blockchain.Database.*;
import static blockchain.TreeOps.extend;
import static blockchain.Proofs.*;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.util.GT;

public class DatabaseTest {
	private Connection conn;
	@Before
	public void testConnection() {
		try{
			conn = getConnection();
		}catch(SQLException e){
			fail("connection failed");
		}
	}
	//	@Test
	//	public void testGetPow2(){
	//		assertEquals(4,Database.getPowOf2(3));
	//	}
	//	@Test
	//	public void testGetPow21(){
	//		assertEquals(4,Database.getPowOf2(4));
	//	}
	//	@Test
	//	public void testGetPow22(){
	//		assertEquals(8,Database.getPowOf2(6));
	//	}
	//	@Test
	//	public void testGetPow23(){
	//		assertEquals(8,Database.getPowOf2(7));
	//	}
	@Test
	public void testGetPow24(){
		assertEquals(8,Database.getPowOf2(8));
	}
	@Test
	public void testGetlog(){
		for(int i=0;i<10;i++){
			System.out.println("input:"+i+" :"+getPowOf2(i));
		}
	}
//			@Test
	public void createTables(){

		try {
//			PreparedStatement createTree = conn.prepareStatement("CREATE TABLE Tree(node varchar(64),left_child varchar(64),right_child varchar(64),size_when_created Integer,path_length Integer)");
			//				PreparedStatement createLog = conn.prepareStatement("CREATE TABLE Log(leaf_value varchar(64),index Integer)");
			//			PreparedStatement createRoots = conn.prepareStatement("CREATE TABLE Roots(RTH varchar(64),size Integer)");
			PreparedStatement createRequests = conn.prepareStatement("CREATE TABLE Requests(id SERIAL,company varchar(64),employee varchar(64),hash varchar(64),reason varchar(200) NOT NULL,timestamp timestamp)");
//			PreparedStatement createFiles = conn.prepareStatement("CREATE TABLE Files(id SERIAL,owner varchar(64),file bytea,timestamp timestamp,hash varchar(64))");


//			createFiles.executeUpdate();			
			createRequests.executeUpdate();

			//				createLog.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
//			@Test
	public void dropTables(){
		try {
			PreparedStatement d1 = conn.prepareStatement("DROP TABLE Requests");

			d1.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void clearTable(){
		PreparedStatement del;
		try {
			del = conn.prepareStatement("Delete FROM requests");
			del.executeUpdate();
		}catch(SQLException e){
			
		}
	}

	//		@Test
	public void showTables(){
		try {
			PreparedStatement createTree = conn.prepareStatement("SELECT n.nspname as \"Schema\",c.relname as \"Name\",CASE c.relkind WHEN 'r' THEN 'table' WHEN 'v' THEN 'view' WHEN 'i' THEN 'index' WHEN 'S' THEN 'sequence' WHEN 's' THEN 'special' END as \"Type\",pg_catalog.pg_get_userbyid(c.relowner) as \"Owner\"FROM pg_catalog.pg_class c    LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE c.relkind IN ('r','')    AND n.nspname <> 'pg_catalog'    AND n.nspname <> 'information_schema'    AND n.nspname !~ '^pg_toast'AND pg_catalog.pg_table_is_visible(c.oid)ORDER BY 1,2;");
			ResultSet r = createTree.executeQuery();
			while(r.next()){
				System.out.println(r.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	  @Test
	public void testrRth(){

		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");
			Database.insertEntry(conn, "9");
			Database.insertEntry(conn, "10");
			Database.insertEntry(conn, "11");
			Database.insertEntry(conn, "12");
			Database.insertEntry(conn, "13");
			Database.insertEntry(conn, "14");
			Database.insertEntry(conn, "15");
			Database.insertEntry(conn, "16");
			Database.insertEntry(conn, "17");
			Database.insertEntry(conn, "18");
			Database.insertEntry(conn, "19");
			Database.insertEntry(conn, "20");
			PreparedStatement roots = conn.prepareStatement("SELECT * FROM roots ORDER BY size ASC");
			ResultSet rootsSet = roots.executeQuery();
			Tree o = new Tree();
			for(int i=1;i<21;i++){
				o = extend(o,Integer.toString(i));
				rootsSet.next();
				assertEquals(o.getValue(), rootsSet.getString(1));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	//	@Test
	public void testInsert1(){
		try {

			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");
			Database.insertEntry(conn, "9");
			Database.insertEntry(conn, "10");
			Database.insertEntry(conn, "11");

			PreparedStatement nodes = conn.prepareStatement("SELECT * FROM Tree ");
			ResultSet nodesSet = nodes.executeQuery();
			String hash12 = TreeOps.hash("12");
			while(nodesSet.next()){
				System.out.println("node: "+nodesSet.getString(1)+" left: "+nodesSet.getString(2)+" right: "+nodesSet.getString(3)+" size: "+nodesSet.getInt(4)+" path length: "+nodesSet.getInt(5));
			}
			PreparedStatement roots = conn.prepareStatement("SELECT * FROM roots ");
			ResultSet rootsSet = roots.executeQuery();
			while(rootsSet.next()){
				System.out.println("root: "+rootsSet.getString(1)+" size: "+rootsSet.getString(2));
			}

			PreparedStatement log = conn.prepareStatement("SELECT * FROM log ");
			ResultSet logSet = log.executeQuery();
			while(logSet.next()){
				System.out.println("entry: "+logSet.getString(1)+" index: "+logSet.getString(2));
			}
			PreparedStatement del;
			del = conn.prepareStatement("Delete FROM Tree");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM log");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM roots");
			del.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Initial insersion failed");
		}
	}




	@Test
	public void seeTable(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");
			Database.insertEntry(conn, "9");
			Database.insertEntry(conn, "10");
			Database.insertEntry(conn, "11");
			PreparedStatement nodes = conn.prepareStatement("SELECT * FROM Tree");
			ResultSet nodesSet = nodes.executeQuery();
			while(nodesSet.next()){
				System.out.println("node: "+nodesSet.getString(1)+" left: "+nodesSet.getString(2)+" right: "+nodesSet.getString(3)+" size: "+nodesSet.getInt(4)+" path length: "+nodesSet.getInt(5));
			}
			PreparedStatement log = conn.prepareStatement("SELECT * FROM log");
			ResultSet logSet = log.executeQuery();
			while(nodesSet.next()){
				System.out.println("log entry: "+logSet.getString(1));
			}
			PreparedStatement hashes = conn.prepareStatement("SELECT * FROM Tree");
			ResultSet hashSet = hashes.executeQuery();
			while(nodesSet.next()){
				System.out.println("root entry: "+hashSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


//	@Test
	public void insertFiles(){
		String user = "domfraise";
		try {
			insertFileAsPath(conn, user, "C:\\Users\\Dom\\Documents\\myfile1.txt");
			insertFileAsPath(conn, user, "C:\\Users\\Dom\\Documents\\myfile2.txt");
			insertFileAsPath(conn, user, "C:\\Users\\Dom\\Documents\\myfile3.txt");
			insertFileAsPath(conn, user, "C:\\Users\\Dom\\Documents\\myfile4.txt");
			insertFileAsPath(conn, user, "C:\\Users\\Dom\\Documents\\myfile5.txt");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void insertRequests(){
		
		try {
			PreparedStatement nodes = conn.prepareStatement("INSERT INTO Requests (company,employee,hash,reason, timestamp) VALUES(?,?,?,?,?)");
			nodes.setString(1, "Google");
			nodes.setString(2, "dave");
			nodes.setString(3, hashFile("C:\\Users\\Dom\\Documents\\myfile1.txt"));
			nodes.setString(4, "I think he's a terrorist");
			nodes.setTimestamp(5, getTimestamp());
			nodes.executeUpdate();
			
			nodes.setString(1, "Google");
			nodes.setString(2, "alan");
			nodes.setString(3, hashFile("C:\\Users\\Dom\\Documents\\myfile4.txt"));
			nodes.setString(4, "I think he's a flight risk");
			nodes.setTimestamp(5, getTimestamp());
			nodes.executeUpdate();

	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLeaves(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			ArrayList<String> actual = getLeaves(conn);
			ArrayList<String> expected = new ArrayList<String>();
			expected.add("1");
			expected.add("2");
			expected.add("3");
			expected.add("4");
			expected.add("5");
			expected.add("6");
			assertEquals(expected,actual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetMyFiles(){
		try {
			ArrayList<String> files = getMyFiles(conn, "domfraise");
			ArrayList<String> expected = new ArrayList<String>();
			Path path = Paths.get("C:\\Users\\Dom\\Documents\\myfile1.txt");
			byte[] file = Files.readAllBytes(path);
			expected.add(TreeOps.hash(file));
			path = Paths.get("C:\\Users\\Dom\\Documents\\myfile2.txt");
			file = Files.readAllBytes(path);
			expected.add(TreeOps.hash(file));
			path = Paths.get("C:\\Users\\Dom\\Documents\\myfile3.txt");
			file = Files.readAllBytes(path);
			expected.add(TreeOps.hash(file));
			path = Paths.get("C:\\Users\\Dom\\Documents\\myfile4.txt");
			file = Files.readAllBytes(path);
			expected.add(TreeOps.hash(file));
			path = Paths.get("C:\\Users\\Dom\\Documents\\myfile5.txt");
			file = Files.readAllBytes(path);
			expected.add(TreeOps.hash(file));
			assertEquals(expected, files);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetRequest(){
		try {
			String[] actual = getRequest(conn, hashFile("C:\\Users\\Dom\\Documents\\myfile1.txt"));
			String [] expected = new String[5];
			expected[0] = "Google";
			expected[1] = "dave";
			expected[2] = hashFile("C:\\Users\\Dom\\Documents\\myfile1.txt");
			expected[3] = "I think he's a terrorist";
			
			
			for(int i=0;i<4;i++){
				assertEquals(expected[i], actual[i]);
			}
			System.out.println(actual[4]);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@After
	public void removeEntries(){
		PreparedStatement del;
		try {
			del = conn.prepareStatement("Delete FROM Tree");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM log");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM roots");
			del.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

package blockchain;

import static blockchain.Database.getConnection;
import static blockchain.Database.hashFile;
import static blockchain.Database.insertEntry;
import static blockchain.Proofs.proveAbsence;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestQueueTest {
	private Connection conn;
	private RequestQueue q;
	DecryptionRequest r1;
	DecryptionRequest r2;
	DecryptionRequest r3;
	DecryptionRequest r4;
	DecryptionRequest r5;
	String f1,f2,f3,f4,f5;
	@Before
	public void setUp() {
		try{
			conn = getConnection();
			q = new RequestQueue(conn);
			f1 = Database.hashFile("C:\\Users\\Dom\\Documents\\myfile1.txt");
			r1 = new DecryptionRequest(conn, "Google", "Alan", f1, "Terrorist");
			f2 = Database.hashFile("C:\\Users\\Dom\\Documents\\myfile2.txt");
			r2 = new DecryptionRequest(conn, "Google", "Alan", f2, "Terrorist");
			f3 = Database.hashFile("C:\\Users\\Dom\\Documents\\myfile3.txt");
			r3 = new DecryptionRequest(conn, "Google", "Alan", f3, "Terrorist");		
			f4 = Database.hashFile("C:\\Users\\Dom\\Documents\\myfile4.txt");
			r4 = new DecryptionRequest(conn, "Google", "Alan", f4, "Terrorist");
			f5 = Database.hashFile("C:\\Users\\Dom\\Documents\\myfile5.txt");
			r5 = new DecryptionRequest(conn, "Google", "Alan", f5, "Terrorist");
			


		}catch(SQLException e){
			e.printStackTrace();
			fail("connection failed");
		}catch(Exception r){
			r.printStackTrace();
		}
		
	}
	@Test
	public void testRTHMatch() {
		String actual;
		try {
			q.add(r1);q.add(r2);q.add(r3);q.add(r4);q.add(r5);
			 actual = Database.getRoot(conn);
			 q.clear();
		} catch (SQLException e) {
			actual = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tree t = new Tree();
		t = TreeOps.extend(t, f1);
		t = TreeOps.extend(t, r1.getRequestHash());
		t = TreeOps.extend(t, f2);
		t = TreeOps.extend(t, r2.getRequestHash());
		t = TreeOps.extend(t, f3);
		t = TreeOps.extend(t, r3.getRequestHash());
		t = TreeOps.extend(t, f4);
		t = TreeOps.extend(t, r4.getRequestHash());
		t = TreeOps.extend(t, f5);
		t = TreeOps.extend(t, r5.getRequestHash());
		assertEquals(t.getValue(), actual);
		
	}

	@Test
	public void testQueueSetUp(){
	
		assertEquals("",q.getOldRoot());
		assertEquals(q.getOldRoot(),q.getNewRoot());
	}
	
	@Test
	public void testQueueAddMultiple(){
		q.add(r1);
		q.add(r2);
		q.add(r3);
		q.add(r4);
		q.add(r5);
		assertEquals("",q.getOldRoot());
		Tree t = new Tree();
		t = TreeOps.extend(t, f1);
		t = TreeOps.extend(t, r1.getRequestHash());
		t = TreeOps.extend(t, f2);
		t = TreeOps.extend(t, r2.getRequestHash());
		t = TreeOps.extend(t, f3);
		t = TreeOps.extend(t, r3.getRequestHash());
		t = TreeOps.extend(t, f4);
		t = TreeOps.extend(t, r4.getRequestHash());
		t = TreeOps.extend(t, f5);
		t = TreeOps.extend(t, r5.getRequestHash());

		assertEquals(t.getValue(),q.getNewRoot());
		PreparedStatement del;

		try {
			del = conn.prepareStatement("Delete FROM Tree");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM log");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM roots");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM requests");
			del.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueueAdd(){
		q.add(r1);
		assertEquals("",q.getOldRoot());
		assertEquals(TreeOps.hash(f1.concat(r1.getRequestHash())),q.getNewRoot());
		PreparedStatement del;

		try {
			del = conn.prepareStatement("Delete FROM Tree");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM log");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM roots");
			del.executeUpdate();
			del = conn.prepareStatement("Delete FROM requests");
			del.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test 
	public void removeFromQueue(){
		q.add(r1);
		q.remove();
		assertEquals(q.getNewRoot(), q.getNewRoot());
		assertEquals(q.getNewRoot(), TreeOps.hash(f1.concat(r1.getRequestHash())));
	}
	
	@Test 
	public void removeFromQueueMultiple(){
		q.add(r1);
		q.add(r2);
		q.add(r3);
		q.remove();
		assertEquals(TreeOps.hash(f1.concat(r1.getRequestHash())), q.getOldRoot());
		Tree t = new Tree();
		t = TreeOps.extend(t, f1);
		t = TreeOps.extend(t, r1.getRequestHash());
		t = TreeOps.extend(t, f2);
		t = TreeOps.extend(t, r2.getRequestHash());
		t = TreeOps.extend(t, f3);
		t = TreeOps.extend(t, r3.getRequestHash());
		assertEquals(q.getNewRoot(), t.getValue());
	}
	
	
	@Test
	public void testProveAbsence(){
		try {
			q.add(r1);
			q.add(r4);
			
		
			
			
			ArrayList<String[]> actual = proveAbsence(conn,"domfraise");
			String [] expected = new String[5];
			expected[0] = "Google";
			expected[1] = "Alan";
			expected[2] = hashFile("C:\\Users\\Dom\\Documents\\myfile1.txt");
			expected[3] = "Terrorist";
			
			
			for(int i=0;i<4;i++){
				assertEquals(expected[i], actual.get(0)[i]);
			}
			System.out.println(actual.get(0)[4]);
			
			expected[0] = "Google";
			expected[1] = "Alan";
			expected[2] = hashFile("C:\\Users\\Dom\\Documents\\myfile4.txt");
			expected[3] = "Terrorist";
			
			
			for(int i=0;i<4;i++){
				assertEquals(expected[i], actual.get(1)[i]);
			}
			System.out.println(actual.get(1)[4]);
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
			del = conn.prepareStatement("Delete FROM requests");
			del.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

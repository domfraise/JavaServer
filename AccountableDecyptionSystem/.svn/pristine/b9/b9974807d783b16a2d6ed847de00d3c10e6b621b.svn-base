package blockchain;

import static blockchain.Database.getConnection;
import static blockchain.Proofs.proveExtension;
import static blockchain.Proofs.provePresence;
import static blockchain.Proofs.writeJsonToFile;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProofsTest {
	private Connection conn;
	@Before
	public void testConnection() {
		try{
			conn = getConnection();
		}catch(SQLException e){
			fail("connection failed");
		}
	}

	@Test
	public void testProofPresence(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			//			System.out.println(provePresence(root.getString(1), 3).toJSONString());
			//			assertEquals("{\"Left\":\"6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918\",\"Right\":{\"Left\":\"3\"}}",provePresence(root.getString(1), 3).toJSONString());
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
		}
	}

	//	@Test
	public void testProofPresence2(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			//			System.out.println(provePresence(root.getString(1), 0).toJSONString());
			assertEquals("{\"Left\":{\"Right\":\"2\"},\"Right\":\"86e50149658661312a9e0b35558d84f6c6d3da797f552a9657fe0558ca40cdef\"}",provePresence(root.getString(1), 0).toJSONString());
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
		}
	}
	@Test
	public void testProofPresence3(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			System.out.println(provePresence(root.getString(1), 1).toJSONString());
			//			assertEquals("{\"Left\":{\"Left\":\"1\"},\"Right\":\"86e50149658661312a9e0b35558d84f6c6d3da797f552a9657fe0558ca40cdef\"}",provePresence(root.getString(1), 1).toJSONString());
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
		}
	}

	@Test
	public void testProofPresence4(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			for(int i=0;i<8;i++){
				System.out.println(provePresence(root.getString(1), i).toJSONString());

			}
			//			System.out.println(provePresence(root.getString(1), 3).toJSONString());
			//			assertEquals("{\"Left\":{\"Left\":\"6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918\",\"Right\":{\"Left\":\"3\"}},\"Right\":\"7688b6ef52555962d008fff894223582c484517cea7da49ee67800adc7fc8866\"}",provePresence(root.getString(1), 3).toJSONString());
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
		}
	}
	@Test
	public void testProovePresenceEmpty(){
		PreparedStatement getRoot;
		try {
			getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			assertNull(provePresence("", 0));



		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			//should throw illegal argument
		}
		catch (SQLException e) {
			fail();
		}
	}

	@Test
	public void testProovePresenceWrongHash(){
		PreparedStatement getRoot;
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			assertNull(provePresence("wrong hash", 0));





		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			fail();
		}
		catch (SQLException e) {
			fail();
		}finally{
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

	@Test
	public void testProovePresenceNotPresent(){
		PreparedStatement getRoot;
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			getRoot = conn.prepareStatement("SELECT * FROM roots WHERE size = 3 LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();

			assertNull(provePresence(root.getString(1), 3));





		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			fail();
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		}finally{
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

	@Test
	public void testProofPresence5(){
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
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			for(int i=0;i<18;i++){
				System.out.println(provePresence(root.getString(1), i).toJSONString());

			}
			//			assertEquals("{\"Left\":{\"Left\":\"6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918\",\"Right\":{\"Left\":\"3\"}},\"Right\":\"7688b6ef52555962d008fff894223582c484517cea7da49ee67800adc7fc8866\"}",provePresence(root.getString(1), 3).toJSONString());
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
		}
	}

	@Test
	public void testProofPresencePrevious(){
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
			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 8 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			for(int i=0;i<8;i++){
				System.out.println(provePresence(root.getString(1), i).toJSONString());

			}
			//			assertEquals("{\"Left\":{\"Left\":\"6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918\",\"Right\":{\"Left\":\"3\"}},\"Right\":\"7688b6ef52555962d008fff894223582c484517cea7da49ee67800adc7fc8866\"}",provePresence(root.getString(1), 3).toJSONString());
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
		}
	}


	@Test
	public void testExtension(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");

			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 4 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			String g = root.getString(1);

			getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 6 ORDER BY size DESC LIMIT 1");
			root = getRoot.executeQuery(); root.next();
			String h = root.getString(1);
			JSONObject proofs = proveExtension(conn, g, h);
			System.out.println(proofs.toJSONString());

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
		}
	}

	@Test
	public void testExtension2(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");

			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 5 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			String g = root.getString(1);

			getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 8 ORDER BY size DESC LIMIT 1");
			root = getRoot.executeQuery(); root.next();
			String h = root.getString(1);
			JSONObject proofs = proveExtension(conn, g, h);
			System.out.println(proofs.toJSONString());



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
		}
	}

	@Test
	public void testExtension3(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			Database.insertEntry(conn, "6");
			Database.insertEntry(conn, "7");
			Database.insertEntry(conn, "8");

			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 1 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			String g = root.getString(1);

			getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 7 ORDER BY size DESC LIMIT 1");
			root = getRoot.executeQuery(); root.next();
			String h = root.getString(1);
			JSONObject proofs = proveExtension(conn, g, h);
			System.out.println(proofs.toJSONString());

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
		}
	}

	@SuppressWarnings("unchecked")
	@Test 
	public void fullTestProofs(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");
			Database.insertEntry(conn, "5");
			String ll = TreeOps.hash("1".concat("2"));
			String rr = TreeOps.hash("3".concat("4"));
			String l = TreeOps.hash(ll.concat(rr));
			String r = "5";
			String root = TreeOps.hash(l.concat(r));

			//prove presence of index 2
			JSONObject expected = new JSONObject();
			expected.put("RTH", root);
			expected.put("Index", 2);
			expected.put("Value", "3");
			JSONObject tree = new JSONObject();
			JSONObject treeL = new JSONObject();
			JSONObject treeLR = new JSONObject();
			JSONObject hash = new JSONObject();
			hash.put("Hash", "4");
			treeLR.put("Right", hash);
			JSONObject leaf = new JSONObject();
			leaf.put("Leaf", "3");
			treeLR.put("Left", leaf);
			hash = new JSONObject();
			hash.put("Hash", ll);
			treeL.put("Left", hash);


			treeL.put("Right", treeLR);
			tree.put("Left",treeL);
			hash = new JSONObject();
			hash.put("Hash", "5");
			tree.put("Right", hash);

			expected.put("Proof", tree);
			JSONObject actual = provePresence(root, 2);
			assertEquals(expected, actual);

			writeJsonToFile( actual,"C:\\Users\\Dom\\Desktop\\presenceIndex2Size5.JSON");

			//proveextension size 2 to size 5
			expected = new JSONObject();
			expected.put("OldRTH", ll);
			expected.put("NewRTH", root);

			JSONObject old = new JSONObject();
			old.put("Hash", ll);
			JSONObject newTree = new JSONObject();


			JSONObject left = new JSONObject();
			JSONObject leftLeft = new JSONObject();
			leftLeft.put("Hash", ll);
			left.put("Left", leftLeft);
			JSONObject leftRight = new JSONObject();
			leftRight.put("Hash", rr);
			left.put("Right", leftRight);
			newTree.put("Left",	left);
			JSONObject Right = new JSONObject();
			Right.put("Hash", "5");

			newTree.put("Right", Right);

			expected.put("OldProof", old);
			expected.put("NewProof", newTree);

			actual = proveExtension(conn,ll, root);
			assertEquals(expected, proveExtension(conn,ll, root));


			writeJsonToFile( actual,"C:\\Users\\Dom\\Desktop\\extensionSize2ToSize5.JSON");








		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testExtensionWrongHashes(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");


			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 3 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			String g = root.getString(1);

			getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 2 ORDER BY size DESC LIMIT 1");
			root = getRoot.executeQuery(); root.next();
			String h = root.getString(1);
			assertNull(proveExtension(conn, g, h));


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
		}
	}

	@Test
	public void testExtensionNotExtension(){
		try {
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");
			Database.insertEntry(conn, "3");
			Database.insertEntry(conn, "4");


			PreparedStatement getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 2 ORDER BY size DESC LIMIT 1");
			ResultSet root = getRoot.executeQuery(); root.next();
			String g = root.getString(1);

			getRoot = conn.prepareStatement("SELECT * FROM roots Where size = 2 ORDER BY size DESC LIMIT 1");
			root = getRoot.executeQuery(); root.next();
			String h = "some random string";
			assertNull(proveExtension(conn, g, h));


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
		}
	}
	
	@Test
	public void testExtensionEmptyInitially(){
		try {
			String g = Database.getRoot(conn);
			Database.insertEntry(conn, "1");
			String h = Database.getRoot(conn);
			JSONObject actual = proveExtension(conn, g, h);
			
			JSONObject expected = new JSONObject();
			expected.put("OldRTH", "");
			expected.put("NewRTH", "1");
			JSONObject oldProof = new JSONObject();
			expected.put("OldProof", oldProof);
			JSONObject newProof = new JSONObject();
			newProof.put("Hash","1");
			expected.put("NewProof", newProof);
			assertEquals(expected,actual);
			
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
		}
	}
	
	@Test
	public void testExtensionEmptyInitially2(){
		try {
			String g = Database.getRoot(conn);
			Database.insertEntry(conn, "1");
			Database.insertEntry(conn, "2");

			String h = Database.getRoot(conn);
			JSONObject actual = proveExtension(conn, g, h);
			
			JSONObject expected = new JSONObject();
			expected.put("OldRTH", "");
			expected.put("NewRTH", TreeOps.hash("1".concat("2")));
			JSONObject oldProof = new JSONObject();
			expected.put("OldProof", oldProof);
			JSONObject newProof = new JSONObject();
			newProof.put("Hash",TreeOps.hash("1".concat("2")));
			expected.put("NewProof", newProof);
			assertEquals(expected,actual);
			
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

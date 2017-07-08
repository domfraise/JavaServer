package blockchain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import static blockchain.TreeOps.hash;
import static blockchain.TreeOps.extend;
import org.junit.Test;

public class TreeTest {

	@Test
	public void testTreeCreation() {
		Tree expected = new Tree(hash(""));
		Tree actual = new Tree();
		actual = TreeOps.extend(actual,hash(""));
		assertEquals(expected,actual);
	}
	
	@Test
	public void testextendSinglet() {

		Tree actual = new Tree(hash("1"));
		Tree expected =  new Tree(hash(hash("1").concat(hash("2"))),new Tree(hash("1")),new Tree(hash("2")));

		actual = TreeOps.extend(actual,hash("2"));
		assertEquals(expected,actual);
	}
	

	
	@Test
	public void testExtendPowOf2() {

		Tree expected = new Tree(hash("1".concat("4")), new Tree("1",new Tree("2"),new Tree("3")),new Tree ("4"));
		Tree actual = new Tree("1",new Tree("2"),new Tree("3"));

		actual = TreeOps.extend(actual,"4");
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testmultipleExtentions() {

		Tree expected = new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")));
		Tree actual = new Tree();
		actual = TreeOps.extend(actual,"1");
		actual = TreeOps.extend(actual,"2");
		actual = TreeOps.extend(actual,"3");
//		assertEquals(actual, new Tree(hash(hash("1".concat("2")).concat("3")),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree("3")));
		TreeOps.proofOfPresence.clear();

		actual = TreeOps.extend(actual,"4");
		
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testmultipleExtentions2() {
		TreeOps.proofOfPresence.clear();

		Tree expected = new Tree ( hash(hash(hash("1".concat("2")).concat(hash("3".concat("4")))).concat("5")  )  ,              new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")) ) ,   new Tree("5")  );
		Tree actual = new Tree();
		actual = TreeOps.extend(actual,"1");
		actual = TreeOps.extend(actual,"2");
		actual = TreeOps.extend(actual,"3");
//		assertEquals(actual, new Tree(hash(hash("1".concat("2")).concat("3")),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree("3")));

		actual = TreeOps.extend(actual,"4");
		TreeOps.proofOfPresence.clear();
		actual = TreeOps.extend(actual,"5");

		
		assertEquals(expected,actual);
	}
	 @Test
	 public void testNunLeaves(){
			Tree expected = new Tree ( hash(hash(hash("1".concat("2")).concat(hash("3".concat("4")))).concat("5")  )  ,              new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")) ) ,   new Tree("5")  );
			assertEquals(expected.getNumLeaves(),5);
	 }
	 
	 @Test
	 public void testNunLeaves2(){
			Tree expected = new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")));
			assertEquals(expected.getNumLeaves(),4);
	 }
//	 
//	 @Test
//	 public void testToArray(){
//			Tree expected = new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")));
//			System.out.println(TreeOps.bfsTraveral(expected));
//	 }
	 
	 @Test
	 public void testPowOftwo(){
			Tree expected = new Tree ( hash(hash(hash("1".concat("2")).concat(hash("3".concat("4")))).concat("5")  )  ,              new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")) ) ,   new Tree("5")  );
			assertEquals(expected.getPowOf2(),4);
	 }
	 @Test
	 public void testPowOftwo2(){
			Tree expected = new Tree ( hash(hash(hash("1".concat("2")).concat(hash("3".concat("4")))).concat("5")  )  ,              new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")) ) ,   new Tree("5")  );
			assertEquals(expected.getPowOf2(),4);
	 }
	 @Test
	 public void testPowOftwo3(){
			Tree expected =  new Tree(hash(hash("1").concat(hash("2"))),new Tree(hash("1")),new Tree(hash("2")));
			assertEquals(expected.getPowOf2(),2);
	 }

	 @Test
	 public void testProofOfPresence(){
			Tree expected = new Tree ( hash(hash(hash("1".concat("2")).concat(hash("3".concat("4")))).concat("5")  )  ,              new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")) ) ,   new Tree("5")  );
			ArrayList<String> expectedProof = new ArrayList<String>();
			String p1 = "88cd668c2056e926cf9f6dad3acbeebf0c1e093da5ab7aceb244e65661d7e35e";
			expectedProof.add(p1);
			
			assertEquals(TreeOps.provePresence(expected, 4).get(0),expectedProof.get(0));
	 }
	 
	 @Test
	 public void testProofOfPresenceSymetric(){
			Tree expected = new Tree(hash(hash("1".concat("2")).concat(hash("3".concat("4")))),    new Tree(hash("1".concat("2")),new Tree("1"),new Tree("2"))  ,new Tree (hash("3".concat("4")),new Tree("3"),new Tree("4")));
			ArrayList<String> expectedProof = new ArrayList<String>();
			String p2 = "3";

			String p1 = "6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918";
			expectedProof.add(p1);
			expectedProof.add(p2);
			
			assertEquals(TreeOps.provePresence(expected, 3).get(0),expectedProof.get(0));
			assertEquals(TreeOps.provePresence(expected, 3).get(1),expectedProof.get(1));

	 }
	 
	 @Test
	 public void testProofOfPresence3(){
		 Tree actual = new Tree();
			actual = TreeOps.extend(actual,"1");
			actual = TreeOps.extend(actual,"2");
			actual = TreeOps.extend(actual,"3");
			actual = TreeOps.extend(actual,"4");
			actual = TreeOps.extend(actual,"5");
			actual = TreeOps.extend(actual,"6");
			actual = TreeOps.extend(actual,"7");
			actual = TreeOps.extend(actual,"8");
			actual = TreeOps.extend(actual,"9");
			actual = TreeOps.extend(actual,"10");
//			System.out.println(TreeOps.bfsTraveral(actual));
//			TreeOps.provePresence(actual, 4).forEach(x -> System.out.println(Arrays.toString(x)));
			
	 }
	 
	 @Test
	 public void testProofOfExtesnion(){
		 Tree actual = new Tree();
		 actual = TreeOps.extend(actual,"1");
		 actual = TreeOps.extend(actual,"2");
		 actual = TreeOps.extend(actual,"3");
		 actual = TreeOps.extend(actual,"4");
		 String p1 = actual.getValue();

		 String[] entries = {"5"};
		 Tree additions = new Tree(); additions = extend(additions,"5");
		 String p2 = additions.getValue();

		 ArrayList<String> proofs = TreeOps.proveExtension(actual, entries);
		 assertEquals(p1,proofs.get(0));
		 assertEquals(p2,proofs.get(1));

		 
	 }
	 
	 @Test
	 public void testProofOfExtesnion1(){
		 Tree actual = new Tree();
		 actual = TreeOps.extend(actual,"1");
		 actual = TreeOps.extend(actual,"2");
		 actual = TreeOps.extend(actual,"3");
		 actual = TreeOps.extend(actual,"4");
		 String p1 = actual.getValue();
		 
		 String[] entries = {"5","6"};
		 Tree additions = new Tree();
		 additions = TreeOps.extend(additions,"5");
		 additions = TreeOps.extend(additions,"6");
		 String p2 = additions.getValue();
		 ArrayList<String> proofs = TreeOps.proveExtension(actual, entries);
		 assertEquals(p1,proofs.get(0));
		 assertEquals(p2,proofs.get(1));

		 
	 }
	 
	 
	 @Test
	 public void testProofOfExtesnion2(){
		 Tree actual = new Tree();
		 actual = TreeOps.extend(actual,"1");
		 actual = TreeOps.extend(actual,"2");
		 actual = TreeOps.extend(actual,"3");
		 actual = TreeOps.extend(actual,"4");
		 actual = TreeOps.extend(actual,"5");
		 
		 Tree proof1 = new Tree();
		 proof1 = TreeOps.extend(proof1,"1");
		 proof1 = TreeOps.extend(proof1,"2");
		 proof1 = TreeOps.extend(proof1,"3");
		 proof1 = TreeOps.extend(proof1,"4");
		 String p1 = proof1.getValue();

		 
		 String[] entries = {"6"};
		 Tree proof2 = new Tree();
		 proof2 = TreeOps.extend(proof2, "5");
		 String p2 =proof2.getValue();
		
		 Tree proof3 = new Tree(); proof3 = extend(proof3,"6");
		 String p3 = proof3.getValue();

		 
		 ArrayList<String> proofs = TreeOps.proveExtension(actual, entries);
		 assertEquals(p1,proofs.get(0));
		 assertEquals(p2,proofs.get(1));
		 assertEquals(p3,proofs.get(2));

	 }
	 
	 @Test
	 public void testProofOfExtesnion3(){
		 Tree actual = new Tree();
		 actual = TreeOps.extend(actual,"1");
		 actual = TreeOps.extend(actual,"2");
		 actual = TreeOps.extend(actual,"3");
		 actual = TreeOps.extend(actual,"4");
		 actual = TreeOps.extend(actual,"5");
		 actual = TreeOps.extend(actual,"6");

		 Tree proof1 = new Tree();
		 proof1 = TreeOps.extend(proof1,"1");
		 proof1 = TreeOps.extend(proof1,"2");
		 proof1 = TreeOps.extend(proof1,"3");
		 proof1 = TreeOps.extend(proof1,"4");
		 String p1 = proof1.getValue();
		 
		 Tree proof2 = new Tree();
		 proof2 = TreeOps.extend(proof2, "5");
		 proof2 = TreeOps.extend(proof2, "6");
		 String p2 = proof2.getValue();
		 
		 Tree proof3 = new Tree();
		 proof3 = TreeOps.extend(proof3, "7");
		 proof3 = TreeOps.extend(proof3, "8");
		 String p3 = proof3.getValue();
		 
		 String[] entries = {"7","8"};

		 ArrayList<String> proofs = TreeOps.proveExtension(actual, entries);
		 assertEquals(p1,proofs.get(0));
		 assertEquals(p2,proofs.get(1));
		 assertEquals(p3,proofs.get(2));
	 
	 }
	 
	 @Test 
	 public void testProofExtensionLarge(){
		 String[] extensions = {"7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
		
		 ArrayList<String> proofs = new ArrayList<String>();
		
		 Tree o = new Tree();
		 o = extend(o,"1");o = extend(o,"2");o = extend(o,"3");o = extend(o,"4");o = extend(o,"5");o = extend(o,"6");
		 
		 Tree t1 = new Tree();
		 t1  = extend(t1,"1");t1 = extend(t1,"2");t1 = extend(t1,"3");t1 = extend(t1,"4");
		 String p1 = t1.getValue();
		 proofs.add(p1);
		 
		 Tree t2 = new Tree();
		 t2 = extend(t2,"5");t2 = extend(t2, "6");
		 String p2 =t2.getValue();
		 proofs.add(p2);
		 
		 Tree t3 = new Tree();
		 t3 = extend(t3,"7");t3 = extend(t3, "8");
		 String p3 = t3.getValue();
		 proofs.add(p3);
		 
		 Tree t4 = new Tree();
		 t4 = extend(t4,"9");t4 = extend(t4,"10");t4 = extend(t4,"11");t4 = extend(t4,"12");t4 = extend(t4,"13");t4 = extend(t4,"14");t4 = extend(t4,"15");t4 = extend(t4,"16");
		 String p4 = t4.getValue();
		 proofs.add(p4);
		 
		 Tree t5 = new Tree();
		 t5 = extend(t5,"17");t5 = extend(t5,"18");t5 = extend(t5,"19");t5 = extend(t5,"20");t5 = extend(t5,"21");t5 = extend(t5,"22");t5 = extend(t5,"23");
		 t5 = extend(t5,"24");t5 = extend(t5,"25");t5 = extend(t5,"26");t5 = extend(t5,"27");t5 = extend(t5,"28");t5 = extend(t5,"29");t5 = extend(t5,"30");t5 = extend(t5,"31");t5 = extend(t5,"32");
		 String p5 = t5.getValue();
		 proofs.add(p5);
		 
		 ArrayList<String> proofsActual = TreeOps.proveExtension(o, extensions);
		 for (int i = 0;i<proofsActual.size();i++){
			 assertEquals("Large proof fail on i = "+i,proofsActual.get(i),proofs.get(i));
			 
		 }
	
	 }
	  @Test
	  public void testrRth(){

			 Tree o = new Tree();
			 o = extend(o,"1");o = extend(o,"2");
			 assertEquals("6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918",o.getValue());
			 o = extend(o,"3");
			 assertEquals("375c39afce841a66ab1e5fc0e088f45b0173030b3376a2a67fda0e5e7799cf5b",o.getValue());

			 o = extend(o,"4");
			 assertEquals("88cd668c2056e926cf9f6dad3acbeebf0c1e093da5ab7aceb244e65661d7e35e",o.getValue());
			 o = extend(o,"5");
			 assertEquals("e9cd3285af60af3230947e8b5c6ab7d2c7d392754e74d9f78a837b65f8c18413",o.getValue());
			 o = extend(o,"6");
			 assertEquals("ae3429272efe63b78fa8d9a5c36a5164522a66146089487dd1a7a4b48798627f",o.getValue());

			 o = extend(o,"7");
			 assertEquals("8004756b6a2d9d1e1333d864c9412c9276da636c878f7f3dcad8e44cb9fd1ccc",o.getValue());	
			 o = extend(o,"8");
			 assertEquals("5cd190530acea64c32e5c777d3ab607866be5012dfb34cb2c5aa7f3d89dca7c5",o.getValue());
			 o = extend(o,"9");
			 assertEquals("19eb752741c8aed69af05ed2534cb8d860dfccf432db9f354ed037b5855461e5",o.getValue());	
			 o = extend(o,"10");
			 assertEquals("8c9714a2e269a2931cdd77fc949ee366ee524eb4b39ca359b2b3141fa646d67d",o.getValue());
			 o = extend(o,"11");
			 assertEquals("049606bfc2bdf7445dda8f836101d50149a55ee7424e0dd4441a474cbdc4902d",o.getValue());
	  }
	 
	 
//	 @Test
//	 public void jsonTest(){
//		 Tree actual = new Tree();
//
//		 actual = TreeOps.extend(actual,"1");
//		 actual = TreeOps.extend(actual,"2");
//		 actual = TreeOps.extend(actual,"3");
//		 actual = TreeOps.extend(actual,"4");
//		 actual = TreeOps.extend(actual,"5");
//		 actual = TreeOps.extend(actual,"6");
//		 String[] extensions = {"7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
//		 Tree extended = actual;
//		 for(String i:extensions){
//			 extended = extend(extended,i);
//		 }
//
////		 System.out.println(TreeOps.bfsTraveral(actual));
//		 for(int i = 0;i<extensions.length;i++)
//		 TreeOps.generateProofOfPresence(extended,i,"C:\\Users\\Dom\\Desktop\\jsonLarge"+i+".JSON");
//	 }
	 
	 @Test
	 public void presence01(){
		 Tree old = new Tree();
		 Tree newTree = extend(old,"1");
		 TreeOps.generateProofOfPresence(newTree, 0, "C:\\Users\\Dom\\Desktop\\01presence.JSON");

	 }
	 @Test
	 public void extension01(){
		 Tree old = new Tree();
		 Tree newTree = extend(old,"1");
		 TreeOps.generateSingleExtensionProof(old,newTree, "C:\\Users\\Dom\\Desktop\\01extension.JSON");
	 }
	 @Test
	 public void presence12(){
		 Tree old = new Tree();
		 old = extend(old,"1");
		 Tree newTree = extend(old,"2");
		 TreeOps.generateProofOfPresence(newTree, 1, "C:\\Users\\Dom\\Desktop\\12presence.JSON");
	 }
	 @Test
	 public void extension12(){
		 Tree old = new Tree();
		 old = extend(old,"1");
		 Tree newTree = extend(old,"2");
		 TreeOps.generateSingleExtensionProof(old,newTree, "C:\\Users\\Dom\\Desktop\\12extension.JSON");

	 }
	 @Test
	 public void presence23(){
		 Tree old = new Tree();
		 old = extend(old,"1");	old = extend(old,"2");
		 Tree newTree = extend(old,"3");
		 TreeOps.generateProofOfPresence(newTree, 2, "C:\\Users\\Dom\\Desktop\\23presence.JSON");
	 }
	 @Test
	 public void extension23(){
		 Tree old = new Tree();
		 old = extend(old,"1");	old = extend(old,"2");
		 Tree newTree = extend(old,"3");
		 TreeOps.generateSingleExtensionProof(old,newTree, "C:\\Users\\Dom\\Desktop\\23extension.JSON");

	 }
	 @Test
	 public void presence34(){
		 Tree old = new Tree();
		 old = extend(old,"1");	old = extend(old,"2");old = extend(old,"3");
		 Tree newTree = extend(old,"4");
		 TreeOps.generateProofOfPresence(newTree, 3, "C:\\Users\\Dom\\Desktop\\34presence.JSON");
	 }
	 @Test
	 public void extension34(){
		 Tree old = new Tree();
		 old = extend(old,"1");	old = extend(old,"2");old = extend(old,"3");
		 Tree newTree = extend(old,"4");
		 TreeOps.generateSingleExtensionProof(old,newTree, "C:\\Users\\Dom\\Desktop\\34extension.JSON");

	 }
	 
//	  @Test
//	 public void jsonTestPresence(){
//		 Tree actual = new Tree();
//
//		 actual = TreeOps.extend(actual,"1");
//		 actual = TreeOps.extend(actual,"2");
//		 Tree extended = actual;extended = extend(extended,"3");
//	
//		 TreeOps.generateProofOfPresence(extended, 2, "C:\\Users\\Dom\\Desktop\\12-3presence.JSON");
//	 }
//	  @Test
//		 public void jsonTestPresence2(){
//			 Tree actual = new Tree();
//
//			 actual = TreeOps.extend(actual,"1");
//			 actual = TreeOps.extend(actual,"2");
//			 Tree extended = actual;extended = extend(extended,"3");
//		
//			 TreeOps.generateProofOfPresence(extended, 2, "C:\\Users\\Dom\\Desktop\\12-3presence.JSON");
//		 }
//	 @Test
//	 public void jsonTest2(){
//		 Tree actual = new Tree();
//
//		 actual = TreeOps.extend(actual,"1");
//		 actual = TreeOps.extend(actual,"2");
//		 Tree extended = actual;extended = extend(extended,"3");
//	
//		 TreeOps.generateSingleExtensionProof(actual, extended, "C:\\Users\\Dom\\Desktop\\12-3extension.JSON");
//	 }
//	 
//	 @Test
//	 public void jsonTest3(){
//		 Tree actual = new Tree();
//
//		 actual = TreeOps.extend(actual,"1");
//		 actual = TreeOps.extend(actual,"2");
//		 actual = TreeOps.extend(actual,"3");
//		 actual = TreeOps.extend(actual,"4");
//		 actual = TreeOps.extend(actual,"5");
//		 Tree extended = actual;extended = extend(extended,"6");
//	
//		 TreeOps.generateSingleExtensionProof(actual, extended, "C:\\Users\\Dom\\Desktop\\json12345-6.JSON");
//	 }
}

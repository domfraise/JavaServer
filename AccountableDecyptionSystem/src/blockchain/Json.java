package blockchain;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
public class Json {
	public static void writeToJsonFile(String filename){
		JSONObject obj = new JSONObject();
		obj.put("rootHash", "somehash");
		JSONArray entry1 = new JSONArray();
		entry1.add("proof1");
		entry1.add("LEFT");
		JSONArray entry2 = new JSONArray();
		entry2.add("proof2");
		entry2.add("LEFT");
		JSONArray entry3 = new JSONArray();
		entry3.add("proof3");
		entry3.add("RIGHT");
		
		JSONArray proofs = new JSONArray();
		proofs.add(entry1);
		proofs.add(entry2);
		proofs.add(entry3);
		
		obj.put("proofs", proofs);
		

        try (FileWriter file = new FileWriter("C:\\Users\\Dom\\Desktop\\b-password.txt")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);
		
		
	}
	
	public static void main(String[] args){
		writeToJsonFile("");
	}
}

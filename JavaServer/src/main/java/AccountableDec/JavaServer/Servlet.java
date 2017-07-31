package AccountableDec.JavaServer;

import static Database.Database.adminSearch;
import static Database.Database.getConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.json.simple.JSONObject;

import Database.Database;
import Database.DecryptionRequest;
import Database.Proofs;

public class Servlet extends HttpServlet {
	Connection conn;
	Set<String[]> basket = new HashSet<String[]>();
	Map<String,byte[]> decryptedFiles = new HashMap<String,byte[]>();

	public void init() throws ServletException{
		try {
			conn = Database.getConnection();
		} catch (SQLException e) {
			System.out.println("couldn't connect to  DB");
			e.printStackTrace();
		}
	}

	public void destroy(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Handles all requests to the server, hanldes accordingly
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		System.out.println(request.getServletPath());
		response.setStatus(HttpStatus.OK_200);
		Scanner scanner = null;
		if(request.getServletPath().equals("/") || request.getServletPath().equals("/adminSearch.html") ){
			scanner = new Scanner(new File("adminSearch.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}else if(request.getServletPath().equals("/basket.html")){
			response.getWriter().println( viewBasket(request));
		}
		else if(request.getServletPath().equals("/search")){
			response.getWriter().println(getSearchResults(request));
		}else if(request.getServletPath().equals("/addToBasket")){
			response.getWriter().println(addToBasket(request));
		}else if(request.getServletPath().equals("/del")){
			delFromBasket(request);
			response.getWriter().println(viewBasket(request));
		}else if(request.getServletPath().equals("/requestDec")){
			requestDecryptions(request);
			scanner = new Scanner(new File("adminSearch.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}else if(request.getServletPath().equals("/userInspection")){
			scanner = new Scanner(new File("userInspection.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}else if(request.getServletPath().equals("/viewRequests")){
			response.getWriter().println(viewDecryptions(request));
		}else if (request.getServletPath().equals("/proofsInfo")){
			scanner = new Scanner(new File("proofsInfo.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}else if (request.getServletPath().equals("/getProofPresence")){
			response.getWriter().println(getProofPresence(request));
		}else if (request.getServletPath().equals("/proofsPresence")){
			scanner = new Scanner(new File("proofsPresence.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}

		//		scanner.close();
	}


	public String getProofPresence(HttpServletRequest r) throws FileNotFoundException{
		String html  = "";
		String line;
		String user = r.getParameter("name");
		try{
			int index = Database.getIndex(conn, r.getParameter("leaf"));
			String proof = Proofs.provePresence(r.getParameter("RTH"),index).toJSONString();
			//		generateProofFile(r);
			Scanner scanner = new Scanner(new File("proofsPresence.html"));

			while(scanner.hasNextLine()){
				line = scanner.useDelimiter(">").nextLine();
				html = html.concat(line);

				if(line.contains("<!-- proof -->")){
					//				html = html.concat("	<a href=\"someproof.json\" download >Download Proof</a>");
					html = html.concat("<div class=\"field\">\r\n" + 
							"  <div class=\"control\">\r\n" + 
							"    <textarea class=\"textarea is-primary\" type=\"text\" >"+proof+"</textarea>\r\n" + 
							"  </div>\r\n" + 
							"</div>");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NullPointerException ex){
			Scanner scanner = new Scanner(new File("proofsPresence.html"));

			while(scanner.hasNextLine()){
				line = scanner.useDelimiter(">").nextLine();
				html = html.concat(line);

				if(line.contains("<!-- proof -->")){
					//				html = html.concat("	<a href=\"someproof.json\" download >Download Proof</a>");
					html = html.concat("<div class=\"field\">\r\n" + 
							"  <div class=\"control\">\r\n" + 
							"    <textarea class=\"textarea is-primary\" type=\"text\" >Proof Generation Failed - Leaf not found in tree with the given RTH. "
							+ "Check your RTH is a current or past hash. If in doubt use the hash"
							+ "provided by the device.</textarea>\r\n" + 
							"  </div>\r\n" + 
							"</div>");
				}
			}
		}
		return html;
	}
	//	public HttpServletResponse downloadProof(HttpServletRequest request,HttpServletResponse response) throws IOException{
	//		File file = new File("C:\\Users\\Dom\\Documents\\someproof.json");
	//		FileInputStream fileIn = new FileInputStream(file);
	//		ServletOutputStream out = response.getOutputStream();
	//
	//		byte[] outputByte = new byte[4096];
	//		//copy binary contect to output stream
	//		while(fileIn.read(outputByte, 0, 4096) != -1)
	//		{
	//			out.write(outputByte, 0, 4096);
	//		}
	//		fileIn.close();
	////		out.flush();
	//		out.close();
	//	   
	//		return response;
	//	}

	//	public void generateProofFile(HttpServletRequest r){
	//
	//		JSONObject proof;
	//		try {
	//			proof = Database.Proofs.proveAbsence(conn, r.getParameter("name"));
	//			System.out.println(proof.toJSONString());
	////			Database.Proofs.writeJsonToFile(proof, r.getParameter("name")+".json");
	//			Database.Proofs.writeJsonToFile(proof,"someproof2.txt");
	//
	//
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//
	//	}
	public String viewDecryptions(HttpServletRequest r) throws FileNotFoundException{
		String html  = "";
		String line;
		String user = r.getParameter("name");
		//		generateProofFile(r);
		Scanner scanner = new Scanner(new File("userInspection.html"));

		while(scanner.hasNextLine()){
			line = scanner.useDelimiter(">").nextLine();
			html = html.concat(line);
			if(line.contains("<!--table-->")){
				try{
					html = html.concat(generateInspectionTable(r));
				}catch (SQLException e) {
					html="";
					e.printStackTrace();
				}
			}
		}
		return html;
	}

	public String generateInspectionTable(HttpServletRequest r) throws SQLException{
		ArrayList<String[]> requests = Proofs.getRequests(conn, r.getParameter("name"));
		String html = "";int i = 0;
		for(String[] row:requests){
			System.out.println(i);

			html=html.concat("					<tr>\r\n" + 
					"						<td>"+row[1]+"</td>\r\n" + 
					"						<td>"+row[0]+"</td>\r\n" + 
					"						<td>"+row[4]+"</td>\r\n" + 
					"						<td>"+row[3]+"</td>\r\n" + 
					"						<td>"+row[2]+" </td>\r\n "+
					"					</tr>	");
		}
		return html;
	}

	public void requestDecryptions(HttpServletRequest r){

		try{
			Set<DecryptionRequest> requests = new HashSet<DecryptionRequest>();
			for(Iterator<String[]> i = basket.iterator();i.hasNext();){
				String[] row = i.next();
				DecryptionRequest req = new DecryptionRequest(conn, r.getParameter("company"), r.getParameter("name"), row[3], row[2]);
				requests.add(req);
				req.addToDatabase();
				//TODO Generate proofs - send through rpc - Recieve back decrypted files
				decryptedFiles.put(req.getFileHash(), req.getFile());//value need to be changed to decrypted file
			}

		}catch (SQLException e) {
			System.err.println("DB connection failed");
			e.printStackTrace();

		}
	}

	/**
	 * Removes a request that was added from the search page from the field variable basket
	 * Triggered by clicking the delete button on basket.html
	 * @param r the http request
	 */
	public void delFromBasket(HttpServletRequest r){
		for(Iterator<String[]> i = basket.iterator();i.hasNext();){
			String[] row  = i.next();
			if(row[3].equals(r.getParameter("del"))){
				i.remove();
			}
		}
	}

	/**
	 * Displays the basket.html page with any items in the basket inserted into the table
	 * @param r http request
	 * @return String the html file in a string to be written to the response writer
	 * @throws FileNotFoundException
	 */
	public String viewBasket(HttpServletRequest r) throws FileNotFoundException{
		String html  = "";
		String line;

		Scanner scanner = new Scanner(new File("basket.html"));

		while(scanner.hasNextLine()){
			line = scanner.useDelimiter(">").nextLine();
			html = html.concat(line);
			if(line.startsWith("<!--table-->")){
				html = html.concat(generateBasketTable(r));
			}
		}
		return html;
	}

	/**
	 * Inserts each item in the basket into a html string to be inserted into the basket.html file
	 * @param r http request
	 * @return String the html file in a string to be written to the response writer

	 */
	public String generateBasketTable(HttpServletRequest r){
		String html = "";
		for(String[] row:basket){
			html=html.concat("					<tr>\r\n" + 
					"						<td>"+row[0]+"</td>\r\n" + 
					"						<td>"+row[1]+"</td>\r\n" + 
					"						<td>"+row[2]+"</td>\r\n" + 
					"						<td>"+row[3]+"</td>\r\n" + 
					"						<td><button class=\"delete is-large\" name=\"del\" type=\"submit\" value="+row[3]+"></button></td>\r\n" + 
					"					</tr>	");
		}
		return html;
	}

	/**
	 * Adds a requests to the decryption basket from search page
	 * @param r http request
	 * @return String html file adminSearch in a string. 
	 * @throws FileNotFoundException
	 */
	public String addToBasket(HttpServletRequest r) throws FileNotFoundException{
		//		System.out.println(r.getParameter("name0"));
		Map<String,String[]> params = r.getParameterMap();
		Set<Map.Entry<String, String[]>> entrySet = params.entrySet();
		for(Map.Entry<String, String[]> entry: entrySet){
			if(entry.getKey().substring(0,5).equals("added") && entry.getValue()[0].equals("on")){
				String[] item = new String[4];
				String index = entry.getKey().substring(5,6);
				System.out.println(index);
				item[0] = params.get("name"+index)[0];
				item[1] = params.get("timestamp"+index)[0];
				item[2] = params.get("reason"+index)[0];
				item[3] = params.get("hash"+index)[0];
				basket.add(item);
			}

		}
		Scanner scanner = new Scanner(new File("adminSearch.html"));
		String html  = "";
		String line;
		while(scanner.hasNextLine()){
			line = scanner.useDelimiter(">").nextLine();
			html = html.concat(line);
			if(line.startsWith("<body>")){
				html = html.concat("<script type=\"text/javascript\">alert( \"Item(s) added to basket \nGo to Decryption Basket to view\",\"stuff\" );</script>");
			}
		}
		for(String[] i:basket){
			System.out.println(Arrays.toString(i));
		}
		return html;
	}

	/**
	 * Generates search results and diplayes them in a table to be added to basket
	 * @param r http request
	 * @return String adminSearch.html in a string with search results inserted 
	 * @throws IOException
	 */
	public String getSearchResults(HttpServletRequest r) throws IOException{
		String html  = "";
		String line;

		Scanner scanner = new Scanner(new File("adminSearch.html"));

		while(scanner.hasNextLine()){
			line = scanner.useDelimiter(">").nextLine();
			html = html.concat(line);
			if(line.startsWith("<!--table-->")){
				html = html.concat(generateSearchTable(r));
			}
		}
		return html;
	}

	/**
	 * Generates tables with rows for each result from seach
	 * @param request
	 * @return String html table to be inserted into the htmlfile
	 */
	public String generateSearchTable(HttpServletRequest request){

		try {

			ArrayList<String[]> results = adminSearch(conn, request.getParameter("name"), request.getParameter("startDate"), request.getParameter("endDate"), request.getParameter("startTime"), request.getParameter("endTime"));

			String html = "<table class=\"table\">\r\n" + 
					"  <thead>\r\n" + 
					"    <tr>\r\n" + 
					"      <th>User</th>\r\n" + 
					"      <th>Timestamp</abbr></th>\r\n" + 
					"      <th>Insert Justification for request</abbr></th>\r\n" + 
					"      <th>Add to basket</th>\r\n" + 
					"    </tr>\r\n" + 
					"  </thead>\r\n" + 
					"  <tbody>";
			//rows go here
			String endHtml = "		"
					+ ""
					+ "<br>\r\n" + 
					"		\r\n" + 
					"		<div class=\"columns\">\r\n" + 
					"			<div class=\"column\">\r\n" + 
					"			\r\n" + 
					"			</div>\r\n" + 
					"			<div class=\"column\" align=\"center\">\r\n" + 
					"				<button class=\"button is-primary\" type = \"submit\" name = \"addToBasket\"  >\r\n" + 
					"				addToBasket\r\n" + 
					"				</button>\r\n" + 
					"			</div>\r\n" + 
					"			<div class=\"column\">\r\n" + 
					"			\r\n" + 
					"			</div>\r\n" + 
					"		\r\n" + 
					"		</div></tbody></table>";


			for(int i = 0;i<results.size();i++)
			{
				String hiddenVars=
						"			<input type=\"hidden\" name=\"name"+i+"\" value = \""+results.get(i)[0]+"\">" +
								"			<input type=\"hidden\" name=\"timestamp"+i+"\" value = \""+results.get(i)[1]+"\">" +
								"			<input type=\"hidden\" name=\"hash"+i+"\" value = \""+results.get(i)[2]+"\">" ;
				endHtml =endHtml.concat(hiddenVars);
				html = html.concat(
						"<tr>\r\n" + 
								"			<td> "+results.get(i)[0]+"</td>\r\n" + 
								"			<td>"+results.get(i)[1]+"</td>\r\n" + 


						"			<td>\r\n" + 
						"				<div class=\"control\">\r\n" + 
						"				<input class=\"input\" type=\"text\" name = \"reason"+i+"\" placeholder=\"Justification\">\r\n" + 
						"				</div>\r\n" + 
						"			</td>\r\n" + 
						"			<td>\r\n" + 
						"				<div>\r\n" + 
						"					<label class=\"checkbox\">\r\n" + 
						" 				 <input type=\"checkbox\" name=\"added"+i+"\">\r\n" + 
						" 					 Add To Basket\r\n" + 
						"					</label>\r\n" + 
						"				</div>\r\n" + 
						"		\r\n" + 
						"		</tr>");
			}

			return html.concat(endHtml);
		} catch (SQLException e) {
			e.printStackTrace();
			return "<b>Database access failed</b>";

		}
	}


}

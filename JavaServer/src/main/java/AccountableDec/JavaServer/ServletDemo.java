package AccountableDec.JavaServer;

import static Database.Database.adminSearch;
import static Database.Database.getConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

public class ServletDemo extends HttpServlet {
	Set<String[]> basket = new HashSet<String[]>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		System.out.println(request.getServletPath());
		response.setStatus(HttpStatus.OK_200);
		if(request.getServletPath().equals("/")){
			Scanner scanner = new Scanner(new File("C:\\Users\\Dom\\OneDrive\\Documents\\Bham CS\\Project\\HTMLFiles\\adminSearch.html"));
			response.getWriter().println(scanner.useDelimiter("\\A").next());
		}else if(request.getServletPath().equals("/search")){
			
			response.getWriter().println(getSearchResults(request));
		}else if(request.getServletPath().equals("/addToBasket")){
			response.getWriter().println(addToBasket(request));
		}
	}
	
	public String addToBasket(HttpServletRequest r) throws FileNotFoundException{
//		System.out.println(r.getParameter("name0"));
		Map<String,String[]> params = r.getParameterMap();
		Set<Map.Entry<String, String[]>> entrySet = params.entrySet();
		for(Map.Entry<String, String[]> entry: entrySet){
			System.out.println(entry.getKey()+" "+entry.getValue()[0]);
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
	
	public String getSearchResults(HttpServletRequest r) throws IOException{
		String html  = "";
		String line;

		Scanner scanner = new Scanner(new File("C:\\Users\\Dom\\OneDrive\\Documents\\Bham CS\\Project\\HTMLFiles\\adminSearch.html"));
	
		while(scanner.hasNextLine()){
			line = scanner.useDelimiter(">").nextLine();
			html = html.concat(line);
			if(line.startsWith("<!--table-->")){
				 html = html.concat(generateTable(r));
			}
		}
		return html;
	}
	
	/**
	 * Generates tables with rows for each result from seach
	 * @param request
	 * @return
	 */
	public static String generateTable(HttpServletRequest request){
		Connection conn = null;
		try {
			conn = getConnection();
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
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

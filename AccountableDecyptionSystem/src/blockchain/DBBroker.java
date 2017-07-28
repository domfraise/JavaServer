package blockchain;


import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;

// See this url for more information:
// http://tomcat.apache.org/tomcat-5.5-doc/jndi-datasource-examples-howto.html
public class DBBroker extends HttpServlet
{
	private static DataSource datasource = null;

	/**
	 * Setup our connection pool when this servlet is started.
	 * Note that this servlet must be started before any other servlet that tries to
	 * use our database connections.
	 */
	public void init() throws ServletException
	{
		try
		{
			InitialContext initialContext = new InitialContext();
			// actual jndi name is "jdbc/postgres"
			datasource = (DataSource) initialContext.lookup( "java:/comp/env/jdbc/postgres" );

			if ( datasource == null )
			{
				String message = "Could not find our DataSource in DBBroker. We're about to have problems.";
				System.err.println("*** " + message);
				throw new Exception(message);
			}
		}
		catch (Exception e)
		{
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * Dole out the connections here.
	 */
	public static synchronized Connection getConnection() 
			throws SQLException
	{
		return datasource.getConnection();
	}

	/**
	 * Must close the database connection to return it to the pool.
	 */
	public static synchronized void freeConnection(Connection connection)
	{
		try
		{
			connection.close();
		}
		catch (Exception e)
		{
			System.err.println("DBBroker: Threw an exception closing a database connection");
			e.printStackTrace();
		}
	}

}


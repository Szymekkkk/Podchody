package com.mycompany.podchody;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Import Database Connection Class file
import com.mycompany.podchody.DatabaseConnection;
// Servlet Name
public class newTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            Connection con = DatabaseConnection.initializeDatabase();

            String Name = request.getParameter("Name");
            String Point_ID = request.getParameter("Point_ID");
            String Treshhold = request.getParameter("Treshhold");

            PreparedStatement st = con
                    .prepareStatement("INSERT INTO Test (`Test_ID`, `Name`, `Point_ID`, `Treshhold`) " +
                            "VALUES (NULL,?,?,?);");
            st.setString(1,Name);
            st.setString(2,Point_ID);
            st.setString(3,Treshhold);

            st.executeUpdate();
            st.close();
            con.close();
            response.sendRedirect("/Podchody/tests.jsp");
            //response.sendRedirect(request.getHeader("referer"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
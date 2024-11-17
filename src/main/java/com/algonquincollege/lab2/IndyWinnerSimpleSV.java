package com.algonquincollege.lab2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DAO.impl.IndyWinnerDAOImpl;
import com.algonquincollege.lab2.Model.IndyWinner;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * A simple servlet to display a paginated list of Indianapolis 500 winners in an HTML table.
 * 
 * <p>This servlet uses JDBC through the {@link IndyWinnerDAO} interface and its implementation
 * {@link IndyWinnerDAOImpl} to query the database for Indy 500 winners. It formats the retrieved
 * data into an HTML table for display.</p>
 * 
 * <p>Note: This servlet is designed for demonstration purposes and does not guarantee:
 * <ul>
 *   <li>Thread safety</li>
 *   <li>Adherence to SOLID principles</li>
 *   <li>Comprehensive error handling</li>
 *   <li>Advanced features like pagination controls</li>
 * </ul>
 * </p>
 * 
 * <p>Access the servlet at the endpoint <code>/IndyWinnerSimpleSV</code>.</p>
 * 
 * @author guokai shi
 */
@WebServlet(urlPatterns = {"/IndyWinnerSimpleSV"})
public class IndyWinnerSimpleSV extends HttpServlet {

    private IndyWinnerDAO dao;

    /**
     * Initializes the servlet and sets up the DAO instance.
     */
    @Override
    public void init() {
        dao = new IndyWinnerDAOImpl();
    }

    /**
     * Handles HTTP GET requests to display a paginated list of Indy 500 winners.
     * 
     * <p>This method retrieves winners from the database based on the current page and formats
     * the results into an HTML table. It also provides a "Continue" button for pagination.</p>
     * 
     * @param request  the {@link HttpServletRequest} containing the client request
     * @param response the {@link HttpServletResponse} for sending the HTML response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve the current page number from the request
        String pageParam = request.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        int limit = 10; // Number of records per page
        int offset = (page - 1) * limit; // Calculate the offset for pagination

        // Fetch winners from the database
        List<IndyWinner> winners = dao.getIndyWinners(offset, limit);

        // Prepare the HTML response
        StringBuilder buffer = new StringBuilder();
        formatPageHeader(buffer);

        if (winners.isEmpty()) {
            buffer.append("<p>No winners found.</p>");
        } else {
            formatWinnersTable(buffer, winners);
        }

        // Add pagination controls
        buffer.append("<br>");
        buffer.append("<form action=\"IndyWinnerSimpleSV\" method=\"GET\">");
        buffer.append("<input type=\"hidden\" name=\"page\" value=\"" + (page + 1) + "\">");
        buffer.append("<input type=\"submit\" value=\"Continue\">");
        buffer.append("</form>");
        buffer.append("</html>");

        // Send the response
        response.getWriter().write(buffer.toString());
    }

    /**
     * Formats the header section of the HTML response.
     * 
     * @param buffer the {@link StringBuilder} used to build the HTML content
     */
    private void formatPageHeader(StringBuilder buffer) {
        buffer.append("<html><head><title>Indy 500 Winners</title></head>");
        buffer.append("<h2><center>Indianapolis 500 Winners</center></h2><br>");
    }

    /**
     * Formats the winners' data into an HTML table.
     * 
     * @param buffer  the {@link StringBuilder} used to build the HTML content
     * @param winners a list of {@link IndyWinner} objects to display
     */
    private void formatWinnersTable(StringBuilder buffer, List<IndyWinner> winners) {
        buffer.append("<center><table border>");
        buffer.append("<tr><th>Year</th><th>Driver</th><th>Average Speed</th><th>Country</th></tr>");

        for (IndyWinner winner : winners) {
            buffer.append("<tr>");
            buffer.append("<td>").append(winner.getYear()).append("</td>");
            buffer.append("<td>").append(winner.getDriver()).append("</td>");
            buffer.append("<td>").append(winner.getAverageSpeed()).append("</td>");
            buffer.append("<td>").append(winner.getCountry()).append("</td>");
            buffer.append("</tr>");
        }

        buffer.append("</table></center>");
    }
}

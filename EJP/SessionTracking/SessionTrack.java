import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class SessionTrack extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 0;
        }

        String userIDKey = "userID";
        String userID = (String) session.getAttribute(userIDKey);
        if (userID == null) {
            userID = "ABCD";
            session.setAttribute(userIDKey, userID);
        }

        visitCount++;
        session.setAttribute("visitCount", visitCount);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String docType = "<!doctype html>";

        out.println(docType);
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Session Infomation</h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Session info</th><th>value</th></tr>");
        out.println("<tr><td>Number of visits</td><td>" + visitCount + "</td></tr>");
        out.println("<tr><td>Creation Time</td><td>" + createTime + "</td></tr>");
        out.println("<tr><td>Time of Last Access</td><td>" + lastAccessTime + "</td></tr>");
        out.println("</table>");
        out.println("</body></html>");
    }
}
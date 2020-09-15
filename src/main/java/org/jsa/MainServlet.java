package org.jsa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/hello")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        String userName = req.getParameter("userName");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        if (userName == null){
            printWriter.write("Hello, anonymous"+"<br>");

        }else {
            printWriter.write("hello, "+ userName+ "<br>");
        }
        printWriter.write("page was visited "+(visitCounter)+ "   times.");
        if (visitCounter > 15){
            session.setAttribute("visitCounter", 0);
        }
        printWriter.close();
    }



}


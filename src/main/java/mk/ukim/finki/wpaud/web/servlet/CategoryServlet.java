package mk.ukim.finki.wpaud.web.servlet;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    @Autowired
    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        // Ctrl + Shift + F9 - fast rerun app for small changes

        // Writing the answer in the response object
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h2>User Info</h2>");
        pw.format("IP Address: %s </br>", ipAddress);
        pw.format("Client agent: %s", clientAgent);
        pw.println("<h2>Categories</h2>");
        pw.println("<ul>");
        categoryService.listCategories().forEach(r-> pw.format("<li>%s (%s)</li>",r.getName(), r.getDescription()));
        pw.println("</ul>");
        pw.println("<h3>Add New Category</h3>");
        pw.println("<form method='post' action='/servlet/category'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>"+
                "<label for='desc'>Description:</label><input id='desc' type='text' name='desc'/>" +
                "<input type='submit' value='Submit'/>"+
                "</form>");
        pw.println("</body>");
        pw.println("<html>");
        pw.println("</body>");
        pw.println("</html>");
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name"); // name atributot na form input-ot
        String categoryDesc = req.getParameter("desc");
        categoryService.create(categoryName, categoryDesc);
        // koga pravime redirect pravime GET baranje (za da se pokaze novata apdejtirana lista na korisnikot)
        resp.sendRedirect("/servlet/category");
    }
}

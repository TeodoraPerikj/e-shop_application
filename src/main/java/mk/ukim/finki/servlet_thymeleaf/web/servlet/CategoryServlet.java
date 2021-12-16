package mk.ukim.finki.servlet_thymeleaf.web.servlet;

import mk.ukim.finki.servlet_thymeleaf.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "categoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        PrintWriter writer = resp.getWriter();

        writer.println("<html>");

        writer.println("<head>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("<h3>User Info:</h3>");
        writer.format("IP Address: %s <br>",ipAddress);
        writer.format("User-Agent: %s",clientAgent);

        writer.println("<h3>Categories</h3>");
        writer.println("<ul>");

        categoryService.listCategories().forEach(category ->
                writer.format("<li>%s (%s)</li>",category.getName(),category.getDescription()));

        writer.println("</ul>");

        writer.println("<h3>Add Category</h3>");
        writer.println("<form method='POST' action='/servlet/category'>");
        writer.println("<label for='ime'>Name:</label>");
        writer.println("<input id='ime' type='text' name='name'>");
        writer.println("<label for='opis'>Description:</label>");
        writer.println("<input id='opis' type='text' name='desc'>");
        writer.println("<input type='submit' value='Submit'>");
        writer.println("</form>");

        writer.println("</body>");

        writer.println("</html>");

        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String desc = req.getParameter("desc");

        categoryService.create(name,desc);

        resp.sendRedirect("/servlet/category");

    }
}

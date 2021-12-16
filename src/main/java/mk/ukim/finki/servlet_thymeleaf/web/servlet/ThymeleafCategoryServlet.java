package mk.ukim.finki.servlet_thymeleaf.web.servlet;

import mk.ukim.finki.servlet_thymeleaf.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "thymeleafCategoryServlet",urlPatterns = "/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;
    private CategoryService categoryService;

    public ThymeleafCategoryServlet(SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {

        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("ipAddress",req.getRemoteAddr());
        webContext.setVariable("clientAgent",req.getHeader("User-Agent"));
        webContext.setVariable("categories",categoryService.listCategories());

        springTemplateEngine.process("category.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String desc = req.getParameter("desc");

        categoryService.create(name,desc);

        resp.sendRedirect("/servlet/thymeleaf/category");

    }

}

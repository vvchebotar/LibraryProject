package org.chebotar.libraryapp.filter;

import org.chebotar.libraryapp.conn.PostgreSQLConnUtils;
import org.chebotar.libraryapp.utils.MyUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {

    public JDBCFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }

        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();

        Collection<? extends ServletRegistration> values = servletRegistrations.values();

        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Only open connections for the special requests need
        // connection. (For example, the path to the servlet, JSP, ..)
        // Avoid open connection for commons request
        // (for example: image, css, javascript,... )

        if (needJDBC(req)) {
            System.out.println("Open Connection for: " + req.getServletPath());

            Connection conn = null;
            try {
                conn = PostgreSQLConnUtils.getConnection();
                conn.setAutoCommit(false);
                MyUtils.storeConnection(request, conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                PostgreSQLConnUtils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                PostgreSQLConnUtils.closeQuietly(conn);
            }
        }

        // With commons requests (images, css, html, ..)
        // No need to open the connection.
        else {
            // Allow request to go forward
            // (Go to the next filter or target)
            chain.doFilter(request, response);
        }
    }
}
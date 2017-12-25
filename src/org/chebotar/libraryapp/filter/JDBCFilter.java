package org.chebotar.libraryapp.filter;
 
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
 
import org.chebotar.libraryapp.conn.ConnectionUtils;
import org.chebotar.libraryapp.utils.MyUtils;
 
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
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
       //
       // Servlet Url-pattern: /spath/*
       //
       // => /spath
       String servletPath = request.getServletPath();
       //System.out.println("servletPath:"+ servletPath);
       // => /abc/mnp
       String pathInfo = request.getPathInfo();
       //System.out.println("pathInfo:"+ pathInfo);
       String urlPattern = servletPath;
       //System.out.println("urlPattern:"+ urlPattern);
       if (pathInfo != null) {
           // => /spath/*
           urlPattern = servletPath + "/*";
       }
 
       // Key: servletName.
       // Value: ServletRegistration
       Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
               .getServletRegistrations();
       //for (Map.Entry<String, ? extends ServletRegistration> s : servletRegistrations.entrySet()) {
    	//    System.out.println("servletRegistrations:"+ s.getKey() + " " + s.getValue());
    	//}

 
       // Collection of all servlet in your webapp.
       Collection<? extends ServletRegistration> values = servletRegistrations.values();
       	//for (ServletRegistration s : values) {
   	    //System.out.println("values:"+ s);
   		//}
       for (ServletRegistration sr : values) {
           Collection<String> mappings = sr.getMappings();
           //for (String s : mappings) {
           //    System.out.println("mappings:"+ s);
           //    }
           if (mappings.contains(urlPattern)) {
               return true;
           }
       }
       return false;
   }
 
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
	   
       HttpServletRequest req =  (HttpServletRequest) request;
       //
       // Only open connections for the special requests need
       // connection. (For example, the path to the servlet, JSP, ..)
       //
       // Avoid open connection for commons request
       // (for example: image, css, javascript,... )
       
       if (this.needJDBC(req)) {
           System.out.println("Open Connection for: " + req.getServletPath());
           
           Connection conn = null;
           try {
               // Create connection
               conn = ConnectionUtils.getConnection();
 
               // Set Auto commit to false
               conn.setAutoCommit(false);
 
               // Store connection in attribute of request.сохраняем соединение в request 
               MyUtils.storeConnection(request, conn);
 
               // Allow request to go forward
               // (Go to the next filter or target)
               chain.doFilter(request, response);
 
               // Commit change.
               conn.commit();
           } catch (Exception e) {
               e.printStackTrace();
               ConnectionUtils.rollbackQuietly(conn);
               throw new ServletException();
           } finally {
               ConnectionUtils.closeQuietly(conn);
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
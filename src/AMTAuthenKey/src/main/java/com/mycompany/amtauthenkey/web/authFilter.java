
package com.mycompany.amtauthenkey.web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.util.logging.resources.logging;

/**
 *
 * @author kevin moreira
 */
public class authFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public authFilter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

         //if on unrestricted, do not filter (css, js etc on this folder
        String path = request.getRequestURI().substring( request.getContextPath().length() );
        
        if ( path.startsWith( "/pages/unrestricted" ) || path.startsWith("/registerControl") ) {
            chain.doFilter( request, response );
            return;
        }
        
        //getting session from request
        HttpSession session = request.getSession();
        /**
         * if username doesnt exist, redirection to login page
         */
        if ( session.getAttribute("username") == null) {
             request.getRequestDispatcher( "/loginControl").forward( request, response );
          }
        else {
            //if there is a username, the client is connected -> he can acces the site         
            request.getRequestDispatcher("/mainControl").forward(request, response);
        }
        
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
    }
}

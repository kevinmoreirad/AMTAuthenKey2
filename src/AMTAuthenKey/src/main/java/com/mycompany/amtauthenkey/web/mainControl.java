/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.web;

import com.mycompany.amtauthenkey.model.AuthKey;
import com.mycompany.amtauthenkey.servicies.AuthKeyManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

/**
 *
 * @author kevin moreira
 */
public class mainControl extends HttpServlet {

    
    @EJB
    private AuthKeyManager authKeyManager; 
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
                System.out.println("do Get mainControl");
        //getting the username of the connected client.
        String username = (String)request.getSession().getAttribute("username");
        
        
        AuthKey authKey = authKeyManager.getRandomAuthKey(username);
        
        
        //giving the jsp the control of request and response
        request.getRequestDispatcher("pages/pageView.jsp").forward(request, response);
    };
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try (PrintWriter out = response.getWriter()) {
                    
                     //getting the username of the connected client.
                    String username = (String)request.getSession().getAttribute("username");
                    
                    System.out.println("do Post mainControl");
                    String logout = (String)request.getParameter("logout");
                    String newUniqueKey = (String)request.getParameter("newUniqueKey");
                    if(logout != null && logout.equals("logout"))
                    {
                        request.getSession().removeAttribute("username");
                        request.getSession().removeAttribute("password");
                        request.getRequestDispatcher("loginControl").forward(request, response);
                    }
                    else
                    {
                        if(newUniqueKey != null && newUniqueKey.equals("newUniqueKey"))
                        {
                            
                            String startD = (String)request.getParameter("startDate");
                            String endD =   (String)request.getParameter("endDate");
                            String[] listeStart = startD.split("-");
                            String[] listeEnd = endD.split("-");
                            
                            Date startDate = new Date(Integer.valueOf(listeStart[0]),
                                    Integer.valueOf(listeStart[1]),
                                    Integer.valueOf(listeStart[2]));
                            Date endDate = new Date(Integer.valueOf(listeEnd[0]),
                                                      Integer.valueOf(listeEnd[1]),
                                                      Integer.valueOf(listeEnd[2]));
                           
                            AuthKey newAuthKey = authKeyManager.getRandomAuthKey(username, startDate, endDate);
                            request.setAttribute("theAuthKey", newAuthKey);
                        }
                        request.getRequestDispatcher("pages/pageView.jsp").forward(request, response);  
                    }
                                
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

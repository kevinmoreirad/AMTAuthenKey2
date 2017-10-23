/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.web;

import com.mycompany.amtauthenkey.model.AuthKey;
import com.mycompany.amtauthenkey.servicies.buisness.AuthKeyManager;
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
                    
                    //setting values by default 
                    if(request.getSession().getAttribute("currentPage") == null)
                    {
                        request.getSession().setAttribute("currentPage", "1");
                    }
                    if(request.getSession().getAttribute("nbRows") == null)
                    {
                        request.getSession().setAttribute("nbRows", "10");
                    }
                    if(request.getSession().getAttribute("orderBy") == null)
                    {
                        request.getSession().setAttribute("orderBy", "id ASC");
                    }
                    if(request.getSession().getAttribute("lastOrderBy") == null)
                    {
                        request.getSession().setAttribute("lastOrderBy", "id");
                    }
                    
                    int nbRow = Integer.valueOf((String)request.getSession().getAttribute("nbRows"));
                    
                     //getting the username of the connected client.
                    String username = (String)request.getSession().getAttribute("username");

                    //getting request parameters who come from jsp pages
                    String logout = (String)request.getParameter("logout");
                    String newUniqueKey = (String)request.getParameter("newUniqueKey");
                    String newMultiKey = (String)request.getParameter("newMultiKey");
                    String changePage = (String)request.getParameter("changePage");
                    String orderBy = (String)request.getParameter("orderBy");
                    String numberRows = ((String)request.getParameter("numberRows"));
                    String modifyKey =(String)request.getParameter("modifyKey");
                    String deleteKey =(String)request.getParameter("deleteKey");
                                        
                    int numberKeys = authKeyManager.getNbAuthKeys();
                    //if we come from logout we remove attributes, 
                    //so the filter didnt send us again on main page
                    if(logout != null && logout.equals("logout"))
                    {
                        request.getSession().removeAttribute("username");
                        request.getSession().removeAttribute("password");
                        request.getRequestDispatcher("loginControl").forward(request, response);
                    }
                    else // if not logout
                    {
                        //if we add a new key unique
                        if(newUniqueKey != null && newUniqueKey.equals("newUniqueKey"))
                        {  
                            String startD = (String)request.getParameter("startDate");
                            String endD =   (String)request.getParameter("endDate");
                            
                            /*correction if the user put a start date after the
                                end date we switch the two dates*/
                            if(startD.compareTo(endD) > 0){
                                String temp = startD;
                                startD = endD;
                                endD = temp;
                            }
                            
                            authKeyManager.addRandomAuthKey(username, startD, endD);                                             
                        }
                        else if(newMultiKey != null && newMultiKey.equals("newMultiKey"))
                        {  
                            int nbKeys = Integer.valueOf((String)request.getParameter("numberKeys"));
                            
                            authKeyManager.addRandomAuthKey(username, nbKeys);                                             
                        }
                        else if(changePage != null)
                        {
                            int currPage = Integer.valueOf((String)request.getSession().getAttribute("currentPage"));
                            if(changePage.equals("left") && currPage > 1)
                            {
                                request.getSession().setAttribute("currentPage", String.valueOf(currPage-1));
                            }
                            else if(changePage.equals("right") && currPage*nbRow < authKeyManager.getNbAuthKeys())
                            {
                                request.getSession().setAttribute("currentPage", String.valueOf(currPage+1));
                            }
                        }
                        else if(orderBy != null)
                        {
                            String sqlOrdering = null;
                            String lastOrderBy = (String)request.getSession().getAttribute("lastOrderBy");
                            if(orderBy.equals("auth"))
                            {
                                sqlOrdering = "auth_key";
                            }
                            else if(orderBy.equals("owner"))
                            {
                                sqlOrdering = "usr_nom";
                            }
                            else if(orderBy.equals("start"))
                            {
                                sqlOrdering = "init_date";
                            }
                            else if(orderBy.equals("end"))
                            {
                                sqlOrdering = "end_date";
                            }
                            //if already push, change the 
                            if(orderBy.equals(lastOrderBy))
                            {
                                sqlOrdering = sqlOrdering + " DESC";
                                request.getSession().setAttribute("lastOrderBy", "");
                            }
                            else
                            {
                                sqlOrdering = sqlOrdering + " ASC";
                                request.getSession().setAttribute("lastOrderBy", orderBy);
                            }
                                
                            request.getSession().setAttribute("orderBy", sqlOrdering);
                        }
                        else if(numberRows != null)
                        {
                            request.getSession().setAttribute("nbRows", numberRows);
                            nbRow = Integer.valueOf((String)request.getSession().getAttribute("nbRows"));
                        }
                        else if(modifyKey!= null)
                        {
                            String startD = (String)request.getParameter("startDate");
                            String endD =   (String)request.getParameter("endDate");
                            
                            /*correction if the user put a start date after the
                                end date we switch the two dates*/
                            if(startD.compareTo(endD) > 0){
                                String temp = startD;
                                startD = endD;
                                endD = temp;
                            }
                            
                            authKeyManager.modifyKey(modifyKey, startD, endD);
                        }
                        else if(deleteKey!= null)
                        {
                            authKeyManager.deleteKey(deleteKey);
                        }
                        request.getSession().setAttribute("authKeys", 
                                authKeyManager.getAuthKeyList(nbRow, 
                                        Integer.valueOf((String)request.getSession().getAttribute("currentPage")), 
                                        (String)request.getSession().getAttribute("orderBy")));
                        
                        request.getSession().setAttribute("nbKeys", String.valueOf(authKeyManager.getNbAuthKeys()));
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

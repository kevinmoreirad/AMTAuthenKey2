/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.web;

import com.mycompany.amtauthenkey.servicies.buisness.RegisterManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
/**
 *
 * @author kevin moreira
 */
public class registerControl extends HttpServlet {

    @EJB
    private RegisterManager registerManager;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordBis = request.getParameter("passwordBis");
            
            if(username !=null && password != null && !username.isEmpty()
                    && !password.isEmpty() && password.equals(passwordBis)
                    && !registerManager.isUserAlreadyOnDataBase(username))
            {
                
                registerManager.addUser(username, password);
                request.getRequestDispatcher("loginControl").forward(request, response);
            }
            else
            {
                //giving the jsp the control of request and response
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            }
    }

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

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordBis = request.getParameter("passwordBis");
            
            if(username !=null && password != null && !username.isEmpty()
                    && !password.isEmpty() && password.equals(passwordBis)
                    && !registerManager.isUserAlreadyOnDataBase(username))
            {
                try {//encryption the password
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.update(password.getBytes());
                    String encryptedPassword = new String(messageDigest.digest());
                    
                    registerManager.addUser(username, encryptedPassword);
                    request.getRequestDispatcher("loginControl").forward(request, response);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(registerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                //giving the jsp the control of request and response
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
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

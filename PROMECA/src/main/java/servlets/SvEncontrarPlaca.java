/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Automovil;
import logica.Controladora;

/**
 *
 * @author KEVIN
 */
@WebServlet(name = "SvEncontrarPlaca", urlPatterns = {"/SvEncontrarPlaca"})
public class SvEncontrarPlaca extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Automovil autoEncontrado = control.encontrarAuto(request.getParameter("placa"));

        buscarAutomovil(request, response, autoEncontrado);

    }

    private static void buscarAutomovil(HttpServletRequest request, HttpServletResponse response, Automovil autoEncontrado) throws IOException {
        if (null != autoEncontrado) {
             HttpSession misession = request.getSession(true);
             misession.setAttribute("placa", request.getParameter("placa"));
            response.sendRedirect("reparacion.jsp");
        }
       else {
            response.sendRedirect("automovil.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}

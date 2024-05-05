/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Mecanica;
import logica.Reparacion;


/**
 *
 * @author ERIK
 */
@WebServlet(name = "SvAutomovil", urlPatterns = {"/SvAutomovil"})
public class SvAutomovil extends HttpServlet {
      Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {{
         }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        crearAutomovil(request);

        response.sendRedirect("reparacion.jsp");
    }

    private void crearAutomovil(HttpServletRequest request) {
        List<Reparacion> reparaciones = new ArrayList<>();
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String anioFab = request.getParameter("anioFab");
        String propietario = request.getParameter("propietario");

        Mecanica  mecanica = control.obtenerMecanica();
        HttpSession misession = request.getSession(true);
        misession.setAttribute("placa",placa);
        control.crearAutomovil(placa, marca, anioFab,propietario,reparaciones,mecanica);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
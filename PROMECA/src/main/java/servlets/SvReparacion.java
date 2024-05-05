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
import logica.Automovil;
import logica.Controladora;
import logica.Reparacion;
import persistencia.ReparacionJpaController;

/**
 *
 * @author USER
 */
@WebServlet(name = "SvReparacion", urlPatterns = {"/SvReparacion"})
public class SvReparacion extends HttpServlet {

    Controladora control = new Controladora();



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Reparacion> listaReparaciones = new ArrayList<>();

        listaReparaciones = control.getReparaciones();
       
        HttpSession misession = request.getSession();
        misession.setAttribute("listaReparaciones", listaReparaciones);
  
        System.out.println("Usuario: " + listaReparaciones.get(0));

        response.sendRedirect("verReparacion.jsp");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        crearReparacion(request);


        response.sendRedirect("inicio.jsp");
    }

    private void crearReparacion(HttpServletRequest request) {
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        String placa = (String) request.getSession().getAttribute("placa");
        Automovil automovil = control.encontrarAuto(placa);
        control.crearReparacion(descripcion,costo,automovil);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

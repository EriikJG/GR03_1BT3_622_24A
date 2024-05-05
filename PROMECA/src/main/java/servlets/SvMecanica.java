/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import logica.Mecanica;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author YOMISMO XD
 */
@WebServlet(name = "SvMecanica", urlPatterns = {"/SvMecanica"})
public class SvMecanica extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        crearMecanica();

        response.sendRedirect("inicio.jsp");
    }

    private void crearMecanica() {
        String nombre = "MecaJobs";
        String direccion = "La Ecuatoriana";
        String correo = "mecapro1212@gmail.com";
        List<Automovil> autos = new ArrayList<>();
        control.crearMecanica(nombre, direccion, correo,autos);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package servlets;

import logica.Automovil;
import logica.Controladora;
import logica.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {
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
        List<Automovil> autos = new ArrayList<>();
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String telefono= request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        control.crearUsuario(cedula, nombre,apellido,correo,telefono,direccion ,autos);
        Usuario usuario = control.encontrarUsuario(cedula);
        HttpSession misession = request.getSession(true);
        misession.setAttribute("propietario", usuario);
        response.sendRedirect("automovil.jsp");
    }
}

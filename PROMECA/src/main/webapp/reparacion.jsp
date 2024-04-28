<%@page import="logica.Mecanica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyprimeraparte.jsp"%>

<h1>Registro Reparaciones</h1>
<p>Este es el apartado para añadir la reparación </p>

<form class="user" action="SvReparacion" method="POST">
         
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="descripcion" name="descripcion"
                   placeholder="descripcion">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="costo" name ="costo"
                   placeholder="costo">
        </div>                                                     
    </div>
                                
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Registro
    </button>
                               
</form>


<%@ include file="components/bodyfinal.jsp"%>

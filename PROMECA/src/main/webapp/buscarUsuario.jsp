<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyprimeraparte.jsp"%>
<%@ include file="components/placa.jsp"%>
<h1>Agregar Usuario</h1>
<p>Este es el apartado para añadir el Propietario del Automovil</p>

<form class="user" action="SvEncontrarUsuario" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="cedula" name="cedula" required
                   placeholder="cedula">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
         Buscar Usuario
    </button>

</form>


<%@ include file="components/bodyfinal.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyprimeraparte.jsp"%>
<%@ include file="components/placa.jsp"%>
<h1>Agregar Usuario</h1>
<p>Este es el apartado para añadir el Propietario del Automovil</p>

<form class="user" action="SvUsuario" method="POST">
    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="cedula" name="cedula"
                   placeholder="cedula">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="nombre">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name ="apellido"
                   placeholder="apellido">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="email" class="form-control form-control-user" id="correo" name="correo"
                   placeholder="correo">
        </div>
        <div class="col-sm-6 mb-3">
        <input type="tel" id="telefono" name="telefono" pattern="09[0-9]{8}" required  placeholder="telefono">/>
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Direccion">
        </div>

    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Agregar Usuario
    </button>

</form>


<%@ include file="components/bodyfinal.jsp"%>

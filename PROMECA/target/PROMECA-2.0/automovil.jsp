<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyprimeraparte.jsp"%>
<%@ include file="components/placa.jsp"%>
<h1>Agregar Automovil</h1>
<p>Este es el apartado para añadir el Automovil al sistema</p>

            <form class="user" action="SvAutomovil" method="POST">
                                <div class="form-group col">
                                    <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="placa" name="placa"
                                            placeholder="Placa">
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="marca" name ="marca"
                                            placeholder="Marca">
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="anioFab" name="anioFab"
                                            placeholder="Año de Fabricacion">
                                    </div> 
                                     <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="propietario" name="propietario"
                                            placeholder="Propietario">
                                    </div> 
                                                                     
                                </div>
                                
                                <button class="btn btn-primary btn-user btn-block" type="submit">
                                    Agregar Automovil
                                </button>
                               
                            </form>


<%@ include file="components/bodyfinal.jsp"%>

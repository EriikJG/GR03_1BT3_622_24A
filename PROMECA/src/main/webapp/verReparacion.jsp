<%@page import="java.util.ArrayList"%>
<%@page import="logica.Reparacion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyprimeraparte.jsp"%>


<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Ver Reparaciones</h1>
                    <p class="mb-4">A continuación podrá visualizar la lista completa de Reparaciones</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Reparaciones</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Costo</th>
                                            <th>Descripcion</th>
                                            <th style="width: 210px">Acción</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Costo</th>
                                            <th>Descripcion</th>
                                            <th style=" width: 210px">Acción</th>
                                        </tr>
                                    </tfoot>
                                   
                                    <%
                                        List<Reparacion> listaReparaciones = new ArrayList<>();
                                         listaReparaciones = (List) request.getSession().getAttribute("listaReparaciones");
                                    %>
                                    
                                    <tbody>
                                        <% for (Reparacion repa : listaReparaciones) {%>
                                        <tr>
                                            <td id="id_repa"<%= repa.getId() %>"><%= repa.getId()%>   </td>
                                            <td><%=repa.getCosto()%></td>
                                            <td><%=repa.getDescripcion()%></td>
                                           
                                            <td style="display: flex; width: 230px;">
                                                <form name="eliminar" action="inicio.jsp" method="POST"> <!-- esto es para mandar el codigo al servlet -->
                                                            <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right: 5px; "> 
                                                              <i class="fas fa-trash-alt"></i>  Eliminar
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=repa.getId()%>"> <!-- esto es para mandar el codigo al servlet -->
                                                </form>  
                                                 <form name="editar" action="inicio.jsp" method="GET"> <!-- esto es para mandar el codigo al servlet -->
                                                            <button type="submit" class="btn btn-primary btn-user btn-block"; style="margin-left: 5px;"> 
                                                              <i class="fas fa-pencil-alt"></i>  Editar
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=repa.getId()%>"> <!-- esto es para mandar el codigo al servlet -->
                                                </form>                                             
                                            </td>
                                            
                                            
                                            
                                        </tr>      
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

 




<%@ include file="components/bodyfinal.jsp"%>
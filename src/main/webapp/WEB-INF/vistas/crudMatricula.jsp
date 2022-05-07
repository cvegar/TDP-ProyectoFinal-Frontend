<jsp:include page="intranetValida.jsp" />
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrapValidator.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/myDesing.css"/>
    <link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrapValidator.css"/>

    <title>Intranet Colegio</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />
<div class="container">&nbsp;<br>&nbsp;<br>&nbsp;<br>
</div>

<div class="container">
    <h3>Registro tu Matricula</h3>
    <div class="col-md-23" >
        <form id="idFormElimina" action="eliminaCrudSeccion">
            <input type="hidden" id="id_elimina" name="id">
        </form>

        <form accept-charset="UTF-8"  action="consultaCrudMatricula" class="simple_form" id="defaultForm2"  method="post">
            <div class="row" style="height: 70px">

                <div class="col-md-4">
                    <c:if test="${sessionScope.MENSAJE != null }">
                        <div class="alert alert-success" id="success-alert">
                            <button type="button" class="close" data-dismiss="alert">x</button>
                                ${sessionScope.MENSAJE}
                        </div>
                    </c:if>
                    <c:remove var="MENSAJE"/>
                </div>
            </div>

            <div class="row" >
                <div class="col-md-12" >
                    <div class="content" >
                            <c:forEach items="${matricula}" var="x">
                                    <div class="card margin-bottom-20">
                                        <div class="card-body bgc-lightblue">
                                                ${x.materia.nombre}
                                        </div>
                                        <button type='button' data-toggle='modal' onclick="editar('${x.codigo}','${x.aula.aulaName}','${x.docente.nombreCompleto}','${x.aula.cantMax}','${x.idSeccion}');" class='btn btn-success lightblue br-color bgc-darkgray bottom-detalle' style='background-color:hsla(233, 100%, 100%, 0);'>
                                            <samp>Detalle</samp>
                                        </button>
                                        </div>
                            </c:forEach>

                    </div>
                </div>
            </div>
        </form>
    </div>


    <div class="modal fade" id="idModalRegistra" >
        <div class="modal-dialog" style="width: 60%">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header" style="padding: 35px 50px">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Seccion</h4>
                </div>
                <div class="modal-body" style="padding: 20px 10px;">
                    <form id="id_form_registra" accept-charset="UTF-8" action="registraActualizaCrudSeccion" class="form-horizontal"     method="post">
                        <div class="panel-group" id="steps">
                            <!-- Step 1 -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos del Aula</a></h4>
                                </div>
                                <div id="stepOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <input type="hidden" name="idSeccion" id="codigo" value="0">
                                            <label class="col-lg-3 control-label" for="id_reg_codigo">codigo</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_reg_codigo" name="codigo" placeholder="Ingrese el codigo" type="text" maxlength="20"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_reg_estado">estado</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_reg_estado" name="estado" placeholder="Ingrese el estado" type="text" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_reg_aula">Aula</label>
                                            <div class="col-lg-5">
                                                <select id="id_reg_aula" name="aula" class='form-control'>
                                                    <option value=" ">[Seleccione]</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_reg_profesor">Docente</label>
                                            <div class="col-lg-5">
                                                <select id="id_reg_profesor" name="docente" class='form-control'>
                                                    <option value=" ">[Seleccione]</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_reg_materia">Materia</label>
                                            <div class="col-lg-5">
                                                <select id="id_reg_materia" name="materia" class='form-control'>
                                                    <option value=" ">[Seleccione]</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                                <button type="submit" class="btn btn-primary" id="idBtnRegistra">REGISTRA</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>

    </div>

    <div class="modal fade" id="idModalActualiza" >
        <div class="modal-dialog" style="width: 60%">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header" style="padding: 35px 50px">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Seccion</h4>
                </div>
                <div class="modal-body" style="padding: 20px 10px;">
                    <form id="id_form_actualiza" accept-charset="UTF-8"  action="registraActualizaCrudMatricula" class="form-horizontal"     method="post">
                        <div class="panel-group" id="steps">
                            <!-- Step 1 -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">Datos del Seccion</a></h4>
                                </div>
                                <div id="stepOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <input type="hidden" name="idSeccion" id="id_act_idseccion" >
                                            <input type="hidden" name="idAlumno" id="id_act_idalumno">
                                            <label class="col-lg-3 control-label" for="id_ID">Seccion</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_ID" readonly="readonly" name="seccion" type="text" maxlength="8"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_act_seccion">Aula</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_act_seccion" readonly="readonly" name="codigo" placeholder="Ingrese el codigo" type="text" maxlength="20"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_act_profesor">Profesor</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_act_profesor"  readonly="readonly" name="estado" placeholder="Ingrese la estado" type="text" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="id_act_vacantes">Vacantes</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" id="id_act_vacantes" readonly="readonly" name="estado" placeholder="Ingrese la estado" type="text" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                                <button type="submit" class="btn btn-primary" id="idBtnActualiza">MATRICULAR</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>

                </div>
            </div>
        </div>

    </div>


    <div class="modal fade" id="idModalElimina" >
        <div class="modal-dialog" style="width: 60%">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header" style="padding: 35px 50px">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4><span class="glyphicon glyphicon-ok-sign"></span> Eliminar Seccion</h4>
                </div>

                <div class="modal-footer">
                    <button type="button" id="idBtnElimina" name="modalDe" class="btn btn-primary">ELIMINAR</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

</div>


<script type="text/javascript">
    $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
        $("#success-alert").slideUp(500);
    });

    $.getJSON("listaAula",{},function(data){
        $.each(data,function(i, obj){
            $("#id_reg_aula").append("<option value='" + obj.idAula+ "'>"+obj.aulaName+"</option>");
            $("#id_act_aula").append("<option value='" + obj.idAula+ "'>"+obj.aulaName+"</option>");
        });
    });
    $.getJSON("listaDocentes",{},function(data){
        $.each(data,function(i, obj){
            $("#id_reg_profesor").append("<option value='" + obj.idUsuario+ "'>"+obj.nombreCompleto+"</option>");
            $("#id_act_profesor").append("<option value='" + obj.idUsuario+ "'>"+obj.nombreCompleto+"</option>");
        });
    });
    $.getJSON("listaMateria",{},function(data){
        $.each(data,function(i, obj){
            $("#id_reg_materia").append("<option value='" + obj.idMateria+ "'>"+obj.nombre+"</option>");
            $("#id_act_materia").append("<option value='" + obj.idMateria+ "'>"+obj.nombre+"</option>");
        });
    });

</script>


<script type="text/javascript">
    $("#idBtnElimina").click(function(){
        $("#idFormElimina").submit();
    });
    function eliminar(id){
        $('#idModalElimina').modal('show');
        $('#id_elimina').val(id);
    }

    $(document).on("click",".btnEliminar",function(){
        //acci�n por defecto
        event.preventDefault();
        //obtener el valor del HREF del bot�n actual
        var ruta;
        ruta=$(this).attr('href');
        //asignar el atributo HREF al bot�n(A) con ID btnEliminar
        $("#btnEliminar").attr('href',ruta);
        $("#idModalElimina").modal("show");
    })

    function registrar(){
        $('#idModalRegistra').modal("show");
    }

    function editar(seccion,aula,profesor,vacantes,idseccion){
        var userId = localStorage.getItem('user');
        $('input[id=id_ID]').val(seccion);
        $('input[id=id_act_seccion]').val(aula);
        $('input[id=id_act_profesor]').val(profesor);
        $('input[id=id_act_vacantes]').val(vacantes);
        $('input[id=id_act_idseccion]').val(idseccion);
        $('input[id=id_act_idalumno]').val(userId);
        $('#idModalActualiza').modal("show");
    }

    $(document).ready(function() {
        $('#tableEditorial').DataTable();
    } );
</script>

<script type="text/javascript">
    $('#id_form_registra').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            codigo: {
                selector : '#id_reg_codigo',
                validators: {
                    notEmpty: {
                        message: 'El codigo es un campo obligatorio'
                    },
                    stringLength :{
                        message:'El codigo es de 3 a 5 caracteres',
                        min : 3,
                        max : 5
                    }
                }
            },
            titulo: {
                selector: '#id_reg_titulo',
                validators:{
                    notEmpty:{
                        message:'El apellido parterno es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            estado:{
                selector:"#id_reg_estado",
                validators:{
                    notEmpty:{
                        message:'El estado es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            tipo:{
                selector:"#id_reg_tipo",
                validators:{
                    notEmpty:{
                        message:'El apellido marterno es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            tipoLibro: {
                selector : '#id_reg_tipoLibro',
                validators: {
                    notEmpty: {
                        message: 'El sistema de evaluacion es un campo obligatorio'
                    }
                }
            },

        }
    });
</script>

<script type="text/javascript">
    $('#id_form_actualiza').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            codigo: {
                selector : '#id_reg_codigo',
                validators: {
                    notEmpty: {
                        message: 'El codigo es un campo obligatorio'
                    },
                    stringLength :{
                        message:'El codigo es de 3 a 5 caracteres',
                        min : 3,
                        max : 5
                    }
                }
            },
            titulo: {
                selector: '#id_act_titulo',
                validators:{
                    notEmpty:{
                        message:'El apellido parterno es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            estado:{
                selector:"#id_act_estado",
                validators:{
                    notEmpty:{
                        message:'El apellido marterno es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            tipo:{
                selector:"#id_act_tipo",
                validators:{
                    notEmpty:{
                        message:'El apellido marterno es obligatorio'
                    },

                    stringLength:{
                        max:50,
                        min:2,
                        message:'no supera el maximo'
                    }
                }
            },
            tipoLibro: {
                selector : '#id_act_tipoLibro',
                validators: {
                    notEmpty: {
                        message: 'El sistema de evaluacion es un campo obligatorio'
                    }
                }
            },

        }
    });
</script>
</body>
</html>








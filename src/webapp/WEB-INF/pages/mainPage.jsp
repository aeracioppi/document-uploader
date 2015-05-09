<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="es">
<head>
<title>Document Manager - Página Principal</title>
<script src="resources/jquery/jquery-2.1.3.js"></script>
<meta charset="utf-8" />
<link rel="stylesheet" href="resources/css/html5doc-reset.min.css" />
<link rel="stylesheet" href="resources/jquery-ui/jquery-ui.min.css">
<script src="resources/jquery-ui/jquery-ui.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<!--[if lt IE 9]>
			     <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script src="resources/js/mainpage.js"></script>
<link rel="stylesheet" href="resources/css/mainPage.css" />
</head>
<body>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Página principal</a></li>
			<li><a onClick="loadDocuments()" href="#tabs-2">Listar
					Documentos</a></li>
			<li><a onClick="clearFormData()" href="#tabs-3">Alta de Documento</a></li>
			<li><a onClick="loadSearchFields()" href="#tabs-4">Buscar Documento</a></li>
		</ul>

		<div id="tabs-1">
			<p>Algún tipo de bienvenida</p>
			<video></video>
		</div>

		<div id="tabs-2">
			<div align="center">
				<h1>Listado de Documentos</h1>
				<table id="documentsDisplay" class="listTable">
					<thead>
						<tr>
							<th class="hidden">ID</th>
							<th>Nombre</th>
							<th>Fecha de Subida</th>
							<th>Tipo del Documento</th>
							<th>Owner</th>
							<th colspan="3">Documento</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<div id="tabs-3">

			<div align="center">
			        <form method="post" action="mainPage/documentUpload" enctype="multipart/form-data">
			            <table class="searchTable">
			                <tr>
			                    <td>Seleccione el archivo:</td> <!-- Validar no vacío -->
			                    <td class="formElement"><input id="fileField" type="file" name="fileUpload"/></td>
			                </tr>
			                <tr>
			                    <td>Tipo de Documento:</td> <!-- Validar no vacío -->
			                    <td class="formElement">
			                    	<select  id="documentType">
									    <option value="opt1">Publico</option>
									    <option value="opt2">Privado</option>
									    <option value="opt3">Draft</option>
									</select>
								</td>
			                </tr>
			                <tr>
			                    <td>Owner:</td> <!-- Validar no vacío -->
			                    <td class="formElement">
			                    	<select id="owner">
									    <option value="opt1">Ana</option>
									    <option value="opt2">Maria</option>
									    <option value="opt3">Juan</option>
									</select>
			                    </td>
			                </tr>
			                <tr>
			                    <td>Descripción:</td> 
			                    <td class="formElement"><input type="text" id="description"/></td>
			                </tr>
			                <tr>
			                    <td colspan="2" align="center"><input id="upload" type="button" value="Upload" onclick="uploadFormData()"/></td>
			            </table>
			        </form>
    		</div>
    	</div>
    	
		<div id="tabs-4">
			<div align="center">
				<h1>Buscador de Documentos</h1>
				<table class="searchTable">
			                <tr>
			                    <td>Nombre del archivo:</td> 
			                    <td class="formElement"><input type="text" id="sDocumentName"/></td>
			                </tr>
			                <tr>
			                    <td>Fecha:</td>
			                    <td class="formElement">
			                    	<input type=text id="sDate">
								</td>
			                </tr>
			                <tr>
			                    <td>Owner:</td> 
			                    <td class="formElement">
			                    	<select id="sOwner">
									    <option value="opt1">Ana</option>
									    <option value="opt2">Maria</option>
									    <option value="opt3">Juan</option>
									</select>
			                    </td>
			                </tr>
			                <tr>
			                    <td colspan="2" align="center"><input id="searchDocs" type="button" value="Buscar" /></td>
			                </tr>
			    </table>
				<table id="documentsSearch" class="listTable">
					<thead>
						<tr>
							<th class="hidden">ID</th>
							<th>Nombre</th>
							<th>Fecha de Subida</th>
							<th>Tipo del Documento</th>
							<th>Owner</th>
							<th colspan="3">Documento</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="details-form" class="ui-widget hidden">
 		  <label id="dialogId" class="hidden"></label>
		  <form>
		    <fieldset>
		    	<tr>
		    		<td>
				      <label >Nombre del archivo</label>
				      <input type="text" id="dialogName" class="text ui-widget-content ui-corner-all">
		      		</td>
		      	</tr>
		      	<tr>
		    		<td>
				      <label >Fecha de Subida</label>
				      <input type="text" id="dialogUpload" class="text ui-widget-content ui-corner-all">
		      		</td>
		      	</tr>
		      	<tr>
		    		<td>
				      <label >Tipo del Documento</label>
				      <input type="text" id="dialogType" class="text ui-widget-content ui-corner-all">
		      		</td>
		      	</tr>
		      	<tr>
		    		<td>
				      <label >Owner</label>
				      <input type="text" id="dialogOwner" value="" class="text ui-widget-content ui-corner-all">
		      		</td>
		      	</tr>
		      	<tr>
		    		<td>
				      <label >Descripcion</label>
				      <input type="text" id="dialogDesc" value="" class="text ui-widget-content ui-corner-all">
		      		</td>
		      	</tr>
		 
		      <!-- Allow form submission with keyboard without duplicating the dialog button -->
		      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
		    </fieldset>
		  </form>
	</div>
	
</body>
</html>

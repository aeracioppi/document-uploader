/**
 * 
 */

$(function() {
	$("#tabs").tabs({
		beforeLoad : function(event, ui) {
			ui.jqXHR.error(function() {
				ui.panel.html("No se pudo cargar el contenido de la sección.");
			});
		}
	});

	$("#sDate").datepicker({
		dateFormat : 'dd/mm/yy',
	});

	$('#searchDocs').click(function() {
		searchDocuments();
	});
	
});

function loadDocuments() {
	clearTable('documentsDisplay');
	$.ajax({
		type : "GET",
		cache : false,
		contentType : false,
		processData : false,
		url : 'mainPage/getAllDocuments',
		success : function(data) {
			$.each(data, function(index, item) {
				loadDocumentTableElement('documentsDisplay', item);
			});
		},
		error : function(e) {
			alert('Error: No se pudo obtener la lista de documentos.');
		}
	});
};

function uploadFormData() {
	var oMyForm = new FormData();
	oMyForm.append("fileUpload", fileField.files[0]);
	oMyForm.append("documentType", $("#documentType option:selected").text());
	oMyForm.append("owner", $("#owner option:selected").text());
	oMyForm.append("description", $("#description").val());
	$.ajax({
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		url : 'mainPage/documentUpload',
		data : oMyForm,
		success : function() {
			clearFormData();
			alert("Archivo cargado exitosamente");
		},
	});
}

function searchDocuments() {
	clearTable('documentsSearch');
	if(validateSearchFields()){
			$.ajax({
				type : "POST",
				url : 'mainPage/searchDocuments',
				data : {
					name : $('#sDocumentName').val(),
					owner : $('#sOwner option:selected').text(), 
					date : $("#sDate").datepicker({ dateFormat: 'dd-MM-yyyy' }).val()
				},
				success : function(data) {
					if(data.length > 0){
						alert("Se encontraron resultados.");
					} else {
						alert("No se encontraron resultados.");
					}
					$.each(data, function(index, item) {
						loadDocumentTableElement('documentsSearch', item);
					});
				},
				error : function(e) {
					alert('Error al realizar la búsqueda.');
				}
			});
	}
};


function openDetail(documentID){
			$.ajax({
				type : "GET",
				url : 'mainPage/getDetails',
				data : {
					documentId : documentID,
				},
				success : function(data) {
					loadDialogContent(data);
					$('#details-form').dialog({
					      autoOpen: false,
					      height: 350,
					      width: 230,
					      modal: true,
					      title: 'Detalles',
					      buttons: {
					        //"Modificar documento": funcionQueSeEjecutaAlEditar,
					        Cerrar: function() {
					          $(this).dialog( "close" );
					        }
					      },
					});
					$('#details-form').dialog('open');
				},
				error : function(e) {
					alert('Error al realizar la búsqueda.');
				}
			});
};

function loadDialogContent(data){
	console.log(data);
	$("#dialogId").val(data.documentID);
	$("#dialogName").prop('disabled', true);
	$("#dialogName").val(data.document_name);
	$("#dialogUpload").prop('disabled', true);
	$("#dialogUpload").val(data.document_upload);
	$("#dialogType").prop('disabled', true);
	$("#dialogType").val(data.document_type);
	$("#dialogOwner").prop('disabled', true);
	$("#dialogOwner").val(data.document_owner);
	$("#dialogDesc").prop('disabled', true);
	$("#dialogDesc").val(data.document_desc);
}

function deleteDocument(documentID) {
	$.ajax({
		type : "POST",
		url : 'mainPage/delete',
		data : {
			documentId : documentID,
		},
		success : function() {
			alert("Documento eliminado");
			loadDocuments();
		},
	});
}

function validateSearchFields(){
	var result = true;
	var error = "Existen errores: \n"; 
	if(!$('#sDocumentName').val()){
		error += "El nombre del archivo se encuentra vacio. Complete el campo. \n";
		result = false;
	}
	if(!result){
		alert(error);
	}
	return result;
}

function validateUploadFields(){
	
}

function loadDocumentTableElement(tableId, item) {
	$('#' + tableId).append(
			'<tr id='+item.documentID+'><td class="hidden"></td><td>'
					+ item.document_name + '</td>' + '<td>'
					+ item.document_upload + '</td>' + '<td>'
					+ item.document_type + '</td>' + '<td>'
					+ item.document_owner + '</td>'
					+ '<td><a href="mainPage/download/' + item.documentID+ '"> Descargar Documento </a></td>'
					+ '<td><a href="#bye" onclick="deleteDocument('+ item.documentID + ');"> Eliminar </a></td>'
					+ '<td><a href="#get" onclick="openDetail(' + item.documentID+ ');"> Detalle </a></td>' + '</tr>');

}

function clearTable(tableID) {
	$('#' + tableID + ' tbody').remove();
}

function loadSearchFields() {
	clearTable('documentsSearch');
	$('#sDate').datepicker('setDate', new Date());
}

function clearFormData() {
	$("#fileField").val("");
	$("#description").val("");
}

package com.manager.document.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.manager.document.model.Document;
import com.manager.document.service.DocumentService;

@Controller
@RequestMapping(value="/mainPage")
public class DocumentController {
	
	Logger log = Logger.getLogger(DocumentController.class);
	private DocumentService service;
	
	@Autowired
	public DocumentController(DocumentService service){
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnMainPage(){
		return new ModelAndView("mainPage");
	}
	
	@RequestMapping(value="/getAllDocuments")
	@ResponseBody
	public List<Document> getAllDocuments(){
		log.info("Buscando el listado de Documentos...");
		return service.getDocumentsList();
	}
	
	@RequestMapping(value="/documentUpload", method = RequestMethod.POST)
	public ModelAndView uploadNewDocument(HttpServletRequest request,
            				   @RequestParam CommonsMultipartFile[] fileUpload) throws Exception{
		CommonsMultipartFile aFile = fileUpload[0];
		log.info("Subiendo el documento..."+ aFile.getName());
		log.info(request.getParameter("description"));
		service.saveDocument(aFile, request.getParameter("documentType"), request.getParameter("owner"), request.getParameter("description"));
		return new ModelAndView("mainPage");
	}
	
	@RequestMapping(value="/download/{documentId}")
	public void downloadDocument(@PathVariable("documentId")
    Integer documentId, HttpServletResponse response){
			Document doc = service.getDocument(documentId);
			log.info("Descargando el documento solicitado: "+ doc.getDocument_name());
			
			response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getDocument_name()+ "\"");
			response.setContentType("application/force-download");
			try {
			      org.apache.commons.io.IOUtils.copy(new ByteArrayInputStream(doc.getDocument_file()), response.getOutputStream());
			      response.flushBuffer();
			    } catch (IOException ex) {
			      log.info("Error writing file to output stream. Filename was"+doc.getDocument_name(), ex);
			      throw new RuntimeException("IOError writing file to output stream");
			    }
	}
	
	@RequestMapping(value="/getDetails")
	@ResponseBody
	public Document getDocumentInfo(@RequestParam("documentId") int documentId){
			log.info("Buscando informacion sobre el documento con ID: "+ documentId);
			Document doc = service.getDocument(documentId);
			log.info("Descripcion: "+doc.getDocument_desc());
			doc.setDocument_file(null); // No quiero devolver el archivo.
			return doc;
	}
	
	@RequestMapping(value="/delete")
	public String deleteDocument(@RequestParam("documentId") Integer documentId){
			service.deleteDocument(documentId);
			return "sucess";
	}

	
	@RequestMapping(value="/searchDocuments")
	@ResponseBody
	public List<Document> searchDocuments(	@RequestParam("name") String name,
											@RequestParam("owner") String owner,
											@RequestParam("date") String date){
		log.info("Buscando documentos con nombre :"+name+" owner: "+owner+" fecha: "+date);
		return service.searchDocuments(name, owner, date);
	}
											
}
	

				


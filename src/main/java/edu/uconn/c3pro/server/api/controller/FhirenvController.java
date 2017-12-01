package edu.uconn.c3pro.server.api.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FhirenvController {
	
	final static ArrayList<String> fhirMessageQueue = new ArrayList<String>();
 
    @RequestMapping(method = RequestMethod.POST, value = "/c3pro/fhirenc/*")
    public ResponseEntity<HttpStatus> sendMessageToQueue(@RequestBody String message) {
    		fhirMessageQueue.add(message);
    		return ResponseEntity.ok(HttpStatus.OK);
    }
}
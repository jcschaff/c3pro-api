package edu.uconn.c3pro.server.api.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uconn.c3pro.server.api.entities.Contract;
import edu.uconn.c3pro.server.api.entities.Observation;
import edu.uconn.c3pro.server.api.entities.Patient;
import edu.uconn.c3pro.server.api.entities.Questionnaire;
import edu.uconn.c3pro.server.api.entities.QuestionnaireResponse;

/**
 * HTTP/1.1 GET /c3pro/fhir/Questionnaire/{{questionnaire id}}
 * HTTP/1.1 POST /c3pro/fhir/QuestionnaireResponse
 * HTTP/1.1 POST /c3pro/fhir/Contract
 * HTTP/1.1 POST /c3pro/fhir/Observation
 * HTTP/1.1 PUT /c3pro/fhir/Patient/{{patient id}}
 */

@Controller
public class QuestionareController {
	final static HashMap<String, Patient> patients = new HashMap<String, Patient>();
	final static HashMap<String, Questionnaire> questionaires = new HashMap<String,Questionnaire>();
	final static ArrayList<QuestionnaireResponse> questionaireResponses = new ArrayList<QuestionnaireResponse>();
	final static ArrayList<Contract> contracts = new ArrayList<Contract>();
	final static ArrayList<Observation> observations = new ArrayList<Observation>();
 
    @RequestMapping(method = RequestMethod.GET, value = "/c3pro/fhir/Questionnaire/{id}")
    @ResponseBody
    public ResponseEntity<Questionnaire> findById(@PathVariable String id) {
    		Questionnaire questionaire = new Questionnaire(id, RandomStringUtils.randomAlphabetic(4)); 
        return ResponseEntity.ok(questionaire);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/c3pro/fhir/QuestionnaireResponse")
    public ResponseEntity<HttpStatus> sendQuestionnaireResponseToQueue(@RequestBody QuestionnaireResponse questionaireResponse) {
    		questionaireResponses.add(questionaireResponse);
    		return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/c3pro/fhir/Contract")
    public ResponseEntity<HttpStatus> sendContractToQueue(@RequestBody Contract contract) {
    		contracts.add(contract);
    		return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/c3pro/fhir/Observation")
    public ResponseEntity<HttpStatus> sendObservationToQueue(@RequestBody Observation observation) {
    		observations.add(observation);
    		return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/c3pro/fhir/Patient/{id}")
    public ResponseEntity<HttpStatus> updatePatient(@RequestBody Patient patient) {
    		patients.put(patient.getId(),patient);
    		return ResponseEntity.ok(HttpStatus.OK);
    }

}
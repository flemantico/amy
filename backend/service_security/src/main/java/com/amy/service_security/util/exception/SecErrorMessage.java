package com.amy.service_security.util.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecErrorMessage {
    private String code ;
    private List<Map<String, String >> messages ;
    
	public SecErrorMessage() {
	}
	public SecErrorMessage(String code, List<Map<String, String>> messages) {
		this.code = code;
		this.messages = messages;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Map<String, String>> getMessages() {
		return messages;
	}
	public void setMessages(List<Map<String, String>> messages) {
		this.messages = messages;
	}
	
	 public final static String formatMessage(String status, BindingResult result){
	        List<Map<String,String>> errors = result.getFieldErrors().stream()
	                .map(err ->{
	                    Map<String,String>  error =  new HashMap <>();
	                    error.put(err.getField(), err.getDefaultMessage());
	                    return error;

	                }).collect(Collectors.toList());
            SecErrorMessage errorMessage = new SecErrorMessage(status, errors);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString="";
	        try {
	            jsonString = mapper.writeValueAsString(errorMessage);
	        } catch (JsonProcessingException  e) {
	            e.printStackTrace();
	        }
	        return jsonString;
	    }
}
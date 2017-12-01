package edu.uconn.c3pro.server.api.entities;

public class QuestionnaireResponse {
	private String id;
	private String content;

	public QuestionnaireResponse(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}

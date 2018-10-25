package com.gs.test.camel.model;

/**
 * A simple class to model a notification.
 * 
 * @author Skyler Layne
 *
 */
public class Notification {

	private String title;
	private String body;
	private Long visitId;

	public Notification() {
	}

	public Notification(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	@Override
	public String toString() {
		return "Notification [title=" + title + ", body=" + body + ", visitId=" + visitId + "]";
	}

}

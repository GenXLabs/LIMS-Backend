package com.kiu.lims.entity;

public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String content;

    // Getter for 'from' field
    public String getFrom() {
        return from;
    }

    // Setter for 'from' field
    public void setFrom(String from) {
        this.from = from;
    }

    // Getter for 'to' field
    public String getTo() {
        return to;
    }

    // Setter for 'to' field
    public void setTo(String to) {
        this.to = to;
    }

    // Getter for 'subject' field
    public String getSubject() {
        return subject;
    }

    // Setter for 'subject' field
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Getter for 'content' field
    public String getContent() {
        return content;
    }

    // Setter for 'content' field
    public void setContent(String content) {
        this.content = content;
    }
}

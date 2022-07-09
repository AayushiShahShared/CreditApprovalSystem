package com.credapp.entities;

public class Message {

private String content;
private String type;
private String cssClass;

// constructors
public Message(String content, String type, String cssClass) {
        this.content = content;
        this.type = type;
        this.cssClass = cssClass;
    }

//Getters and Setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    
//    tostring overloading
    @Override
    public String toString(){
        StringBuilder builder =new StringBuilder(); 
        builder.append("Message : [ content=").append(content)
                .append(", message type=").append(type)
                .append(" ]"); 
 return builder.toString();
   
 
 
    }
}

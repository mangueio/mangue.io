package io.mangue.dtos;

import java.io.Serializable;

/**
 * Created by misael on 29/10/2015.
 */
public class MessageDTO implements Serializable{

    public MessageDTO(){}
    public MessageDTO(String header){this.header = header;}
    public MessageDTO(String header, Object payload){this.header = header; this.payload = payload;}
    public MessageDTO(String header, String executor, Object payload){this.header = header; this.executor = executor; this.payload = payload;}
    public String header;
    public String executor;
    public Object payload;

    @Override
    public String toString() {
        if(header != null)
            return header.toString();
        return super.toString();
    }
}

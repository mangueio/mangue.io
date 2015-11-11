package io.mangue.dtos;

import java.io.Serializable;

/**
 * Created by misael on 29/10/2015.
 */
public class MessageDTO implements Serializable{

    public MessageDTO(){}
    public MessageDTO(String message){this.message = message;}
    public String message;
}

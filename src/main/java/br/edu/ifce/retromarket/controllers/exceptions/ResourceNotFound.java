package br.edu.ifce.retromarket.controllers.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound (String msg){
        super(msg);
    }
}

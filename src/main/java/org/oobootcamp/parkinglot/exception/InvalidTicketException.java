package org.oobootcamp.parkinglot.exception;

public class InvalidTicketException extends RuntimeException{
    public InvalidTicketException() {
        super("invalid ticket");
    }
}

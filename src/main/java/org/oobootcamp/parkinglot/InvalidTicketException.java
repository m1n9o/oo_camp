package org.oobootcamp.parkinglot;

public class InvalidTicketException extends Exception{
    public InvalidTicketException() {
        super("invalid ticket");
    }
}

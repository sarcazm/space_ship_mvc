package com.ship.exceptions;

public class ShipValidateException extends Exception{

    public ShipValidateException() {
        super("error validate data ship");
    }

    public ShipValidateException(String message) {
        super(message);
    }
}

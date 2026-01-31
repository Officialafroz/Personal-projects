package com.elevata.gsrtc.service;

public class InvalidTripCodeException extends RuntimeException {
  public InvalidTripCodeException(String message) {
    super(message);
  }
}

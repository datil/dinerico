package com.dinerico.pos.exception;

import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */

public class ValidationError extends Exception {

  private HashMap<String,Integer> mapMessage;

  public ValidationError(String systemMessage, HashMap mapMessage) {
    super(systemMessage);
    this.mapMessage = mapMessage;
  }

  public ValidationError(String systemMessage) {
    super(systemMessage);
  }

  public HashMap<String, Integer> getMapMessage() {
    return mapMessage;
  }
}
package com.denis.monich.mazeapi.dto;

public enum OperationType {

  START("Start"),
  GO_EAST("GoEast"),
  GO_NORTH("GoNorth"),
  GO_SOUTH("GoSouth"),
  GO_WEST("GoWest");

  public final String operation;

  OperationType(String operation) {
    this.operation = operation;
  }

}

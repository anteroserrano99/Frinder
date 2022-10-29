package com.graph.finder.model;

public enum Sex {


  MALE("MALE"),
  FEMALE("FEMALE"),
  OTHER("OTHER");

  private final String nameLiteral;

  Sex(String nameLiteral) {
    this.nameLiteral = nameLiteral;
  }

  public String getNameLiteral() {
    return nameLiteral;
  }
}

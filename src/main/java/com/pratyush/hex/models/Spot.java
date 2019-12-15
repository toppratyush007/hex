package com.pratyush.hex.models;

import com.pratyush.hex.constants.Flag;
import lombok.Getter;

public class Spot {
  @Getter
  private Flag flag;

  public Spot(Flag flag) {
    this.flag = flag;
  }

  public String getRepresentation() {
    if(flag == null)
      return " ";
    else if (flag==Flag.RED)
      return "R";
    else
      return "B";
  }

  public boolean isEmpty(){
    return flag == null;
  }
}

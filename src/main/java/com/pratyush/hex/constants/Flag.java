package com.pratyush.hex.constants;

public enum Flag {
  RED,BLUE;

  public static Flag oppositeFlag(Flag currentFlag) {
    if(currentFlag == Flag.BLUE)
      return Flag.RED;
    else
      return Flag.BLUE;
  }

  public static boolean toGoRight(Flag flag){
    return flag == Flag.RED;
  }
}

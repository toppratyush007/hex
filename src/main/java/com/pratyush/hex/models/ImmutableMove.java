package com.pratyush.hex.models;

import com.pratyush.hex.constants.Flag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ImmutableMove {
  @Getter
  private int x;
  @Getter
  private int y;
  @Getter
  private Flag flag;
}

package com.pratyush.hex.models.player;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.ImmutableMove;
import com.pratyush.hex.models.board.BoardObserver;
import lombok.Data;

@Data
public abstract class Player implements BoardObserver {
  protected String name;
  protected Flag flag;

  public abstract ImmutableMove awaitMove(Board board);
}

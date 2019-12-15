package com.pratyush.hex.models.player;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.helpers.SolvingStrategy;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.ImmutableMove;

public class CompPlayer extends Player{

  private SolvingStrategy solvingStrategy;

  public CompPlayer(Flag flag, SolvingStrategy solvingStrategy){
    this.name = "Computer";
    this.flag = flag;
    this.solvingStrategy = solvingStrategy;
  }

  @Override
  public ImmutableMove awaitMove(Board board) {
    return solvingStrategy.getBestMove(flag, board);
  }

  @Override
  public void boardUpdated(Board board) {
    // nothing to do.
  }
}

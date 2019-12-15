package com.pratyush.hex.helpers;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.ImmutableMove;

public interface SolvingStrategy {
  ImmutableMove getBestMove(Flag flag, Board board);
}

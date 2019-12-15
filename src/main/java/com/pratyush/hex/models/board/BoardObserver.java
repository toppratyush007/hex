package com.pratyush.hex.models.board;

import com.pratyush.hex.models.board.Board;

public interface BoardObserver {
  void boardUpdated(Board board);
}

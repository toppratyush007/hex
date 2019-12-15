package com.pratyush.hex.models.board;

import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.board.BoardObserver;

import java.util.Arrays;
import java.util.List;

public class BoardSubject {
  private List<BoardObserver> boardObservers;
  private Board board;

  public BoardSubject(BoardObserver boardObserver1, BoardObserver boardObserver2) {
    this.boardObservers = Arrays.asList(boardObserver1, boardObserver2);
  }

  public void updateBoard(Board board) {
    this.board = board;
    execute();
  }

  private void execute() {
    for (BoardObserver boardObserver : boardObservers) {
      boardObserver.boardUpdated(board);
    }
  }
}

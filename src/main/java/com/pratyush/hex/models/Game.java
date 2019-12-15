package com.pratyush.hex.models;

import static com.pratyush.hex.constants.UserMessages.ENDED_GAME;
import static com.pratyush.hex.constants.UserMessages.INVALID_PLAYER;

import com.pratyush.hex.exception.InvalidMoveException;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.board.BoardSubject;
import com.pratyush.hex.models.player.Player;
import com.pratyush.hex.utils.BoardCalculationsUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {

  private List<Player> players;
  private Player winner;
  private Board board;
  private Player currentTurnHolder;
  private BoardSubject boardSubject;

  public Game(
      List<Player> players,
      Player currentTurnHolder,
      int dimension) {
    this.players = players;
    this.currentTurnHolder = currentTurnHolder;
    this.board = new Board(dimension);
    this.boardSubject = new BoardSubject(players.get(0), players.get(1));
    this.winner = null;
  }

  public void registerMove(Player player) throws InvalidMoveException {
    checkIfPlayingPredicateIsInvalid(currentTurnHolder != player,
        INVALID_PLAYER);
    checkIfPlayingPredicateIsInvalid(winner != null, ENDED_GAME);
    ImmutableMove immutableMove = player.awaitMove(board.deepCopy());
    log.info("Move registered by player {} at x {} y {}", player.getName(), immutableMove.getX(),
        immutableMove.getY());
    board.markOccupied(immutableMove);
    boardSubject.updateBoard(board);
    updateIfGameWonByCurrentTurnHolder();
    reverseTurns();
  }

  private void updateIfGameWonByCurrentTurnHolder() {
    if (BoardCalculationsUtil.checkIfSidesConnectedByColor(currentTurnHolder.getFlag(), board)) {
      log.info("Game won by player {}", currentTurnHolder.getName());
      winner = currentTurnHolder;
    }
  }

  private void checkIfPlayingPredicateIsInvalid(boolean b, String exceptionMessage)
      throws InvalidMoveException {
    if (b) {
      throw new InvalidMoveException(exceptionMessage);
    }
  }

  private void reverseTurns() {
    if (players.get(0) == currentTurnHolder) {
      currentTurnHolder = players.get(1);
    } else {
      currentTurnHolder = players.get(0);
    }
  }

  public boolean hasEnded() {
    return this.winner != null;
  }

  public void play() throws InvalidMoveException {
    this.registerMove(currentTurnHolder);
  }
}

package com.pratyush.hex.models.player;

import static com.pratyush.hex.constants.UserMessages.GET_X_INPUT;
import static com.pratyush.hex.constants.UserMessages.GET_Y_INPUT;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.models.ImmutableMove;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HumanPlayer extends Player {

  public HumanPlayer(Flag flag) {
    this.name = "human";
    this.flag = flag;
  }

  @Override
  public ImmutableMove awaitMove(Board board) {
    ImmutableMove immutableMove;
    do {
      Integer x = getInput(board, GET_X_INPUT);
      Integer y = getInput(board, GET_Y_INPUT);

      immutableMove = new ImmutableMove(x, y, getFlag());
    } while (!board.isValid(immutableMove));

    return immutableMove;
  }

  private Integer getInput(Board board, String getXInput) {
    Integer x = null;
    do {
      log.info(getXInput);
      try {
        x = new Scanner(System.in).nextInt();
      } catch (Exception e) {
        log.error("Error in input ", e);
      }
    } while (!board.isValidPosition(x));
    return x;
  }

  @Override
  public void boardUpdated(Board board) {
    board.paint();
  }
}

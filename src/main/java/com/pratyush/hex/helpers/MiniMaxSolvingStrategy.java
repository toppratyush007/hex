package com.pratyush.hex.helpers;

import static com.pratyush.hex.constants.Flag.oppositeFlag;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.board.Board;
import com.pratyush.hex.utils.BoardCalculationsUtil;
import com.pratyush.hex.models.ImmutableMove;
import com.pratyush.hex.models.Spot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MiniMaxSolvingStrategy implements SolvingStrategy{

  @Override
  public ImmutableMove getBestMove(Flag flag, Board board) {
    ImmutableMove bestMove = null;
    Spot[][] spots = board.getSpots();
    int score = -100000;
    for(int row=0;row<spots.length;row++)
      for(int col=0;col<spots[row].length;col++) {
        if(spots[row][col].isEmpty()) {
          int value = optimiseByPlacingElementAt(board, spots, row, col, flag, flag, true, 1);
          if (value > score) {
            score = value;
            bestMove = new ImmutableMove(row, col, flag);
          }
        }
      }
    return bestMove;

  }

  private int getBestMove(final Flag flag, final Board board, final boolean isMaximising, final int depth) {
    if(depth>3)
      return heuristicCalculation(flag, isMaximising, board);

    Integer scoreIfFlagAlreadyConnects = getScoreIfFlagAlreadyConnects(flag, board, isMaximising);
    if (scoreIfFlagAlreadyConnects != null)
      return scoreIfFlagAlreadyConnects;
    return getScoreByMaximisation(flag, board, isMaximising, depth);
  }

  private int getScoreByMaximisation(Flag flag, Board board, boolean isMaximising, int depth) {
    int score = isMaximising? -1: 1;
    Spot[][] spots = board.getSpots();
    for (int row = 0; row < spots.length; row++) {
      for (int col = 0; col < spots[row].length; col++) {
        if (spots[row][col].isEmpty()) {
          if (isMaximising) {
            int value = optimiseByPlacingElementAt(board, spots, row, col, oppositeFlag(flag), flag,
                isMaximising == true, depth + 1);
            score = Math.max(score, value);
          } else {
            int value = optimiseByPlacingElementAt(board, spots, row, col, oppositeFlag(flag),
                oppositeFlag(flag), isMaximising == true, depth + 1);
            score = Math.min(score, value);
          }
        }
      }
    }
    return score;
  }

  private int optimiseByPlacingElementAt(Board board, Spot[][] spots, int row, int col, Flag flag2,
      Flag flag3, boolean b, int i) {
    spots[row][col] = new Spot(flag2);
    int value = getBestMove(flag3, board, b, i);
    spots[row][col] = new Spot(null);
    return value;
  }

  private Integer getScoreIfFlagAlreadyConnects(Flag flag, Board board, boolean isMaximising) {
    if(isMaximising && BoardCalculationsUtil.checkIfSidesConnectedByColor(flag, board)){
      return 10000;
    }
    else if(!isMaximising && BoardCalculationsUtil.checkIfSidesConnectedByColor(flag, board)){
      return -10000;
    }
    return null;
  }

  private int heuristicCalculation(Flag originalFlag, boolean isMaximising, Board board) {
    int score = BoardCalculationsUtil.maxLengthChainByFlag(originalFlag, board);
    score = isMaximising?score*10:score*-10;
    return score;
  }

  private boolean isMaximising(Flag originalFlag, Flag currentFlag) {
    return originalFlag == currentFlag;
  }


}

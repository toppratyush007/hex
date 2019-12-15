package com.pratyush.hex.utils;

import static com.pratyush.hex.constants.Flag.toGoRight;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.board.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardCalculationsUtil {

  public static boolean checkIfSidesConnectedByColor(Flag flag, Board board) {

    if (flag == Flag.RED) {
      for (int i = 0; i < board.getSpots().length; i++) {
        if (checkIfChainConnectsTwoEnds(Flag.RED, board, i, 0)) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < board.getSpots()[0].length; i++) {
        if (checkIfChainConnectsTwoEnds(Flag.BLUE, board, 0, i)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean chainExists(final int row, final int col, final Flag flag, Board board) {
    if (isInvalidCoordValue(row, board)) {
      return false;
    } else if (isInvalidCoordValue(col, board)) {
      return false;
    } else if (checkIfFlagAtPositionIsNotEqual(row, col, flag, board)) {
      return false;
    } else if (checkIfHasToBeGoneInDirectionAndAlreadyEnd(col, board, toGoRight(flag))) {
      return true;
    } else if (checkIfHasToBeGoneInDirectionAndAlreadyEnd(row, board, !toGoRight(flag))) {
      return true;
    } else {
      if (toGoRight(flag)) {
        return checkIfAnyTrue(
            checkIfChainConnectsTwoEnds(flag, board, row - 1, col + 1),
            checkIfChainConnectsTwoEnds(flag, board, row, col + 1),
            checkIfChainConnectsTwoEnds(flag, board, row + 1, col + 1));
      } else {
        return checkIfAnyTrue(
            checkIfChainConnectsTwoEnds(flag, board, row + 1, col - 1),
            checkIfChainConnectsTwoEnds(flag, board, row + 1, col),
            checkIfChainConnectsTwoEnds(flag, board, row + 1, col + 1));
      }
    }
  }

  private static boolean isInvalidCoordValue(int row, Board board) {
    return row < 0 || row >= board.getSpots().length;
  }

  private static boolean checkIfChainConnectsTwoEnds(Flag flag, Board board, int i, int i2) {
    return chainExists(i, i2, flag, board);
  }

  private static boolean checkIfHasToBeGoneInDirectionAndAlreadyEnd(int row, Board board, boolean b) {
    return b && row == board.getSpots().length - 1;
  }

  public static int maxLengthChainByFlag(Flag flag, Board board) {
    int ans = 0;
    if (flag == Flag.RED) {
      ans = maximiseAnswerForFlag(board, ans, Flag.RED);
    } else {
      ans = maximiseAnswerForFlag(board, ans, Flag.BLUE);
    }
    return ans;
  }

  // TODO : optimise this
  private static int maximiseAnswerForFlag(Board board, int ans, Flag flag) {
    for (int i = 0; i < board.getSpots().length; i++) {
      for (int j = 0; j < board.getSpots().length; j++) {
        ans = Math.max(ans, maxChainLengthForFlag(i, j, flag, board));
      }
    }
    return ans;
  }

  private static int maxChainLengthForFlag(final int row, final int col, final Flag flag, Board board) {
    if (isInvalidCoordValue(row, board)) {
      return 0;
    } else if (isInvalidCoordValue(col, board)) {
      return 0;
    } else if (checkIfFlagAtPositionIsNotEqual(row, col, flag, board)) {
      return 0;
    } else if (checkIfHasToBeGoneInDirectionAndAlreadyEnd(col, board, toGoRight(flag))) {
      return 1;
    } else if (checkIfHasToBeGoneInDirectionAndAlreadyEnd(row, board, !toGoRight(flag))) {
      return 1;
    } else {
      if (toGoRight(flag)) {
        return getMaxOf(calculateValueFrom(flag, board, row - 1, col + 1),
            calculateValueFrom(flag, board, row, col + 1),
            calculateValueFrom(flag, board, row + 1, col + 1));
      } else {
        return getMaxOf(calculateValueFrom(flag, board, row + 1, col - 1),
            calculateValueFrom(flag, board, row + 1, col),
            calculateValueFrom(flag, board, row + 1, col + 1));
      }
    }
  }

  private static int calculateValueFrom(Flag flag, Board board, int i, int i2) {
    return maxChainLengthForFlag(i, i2, flag, board) + 1;
  }

  private static boolean checkIfFlagAtPositionIsNotEqual(int row, int col, Flag flag, Board board) {
    return board.getSpots()[row][col].getFlag() != flag;
  }

  private static int getMaxOf(int l1, int l2, int l3) {
    return Math.max(l1, Math.max(l2, l3));
  }

  private static boolean checkIfAnyTrue(boolean dfs, boolean dfs1, boolean dfs2) {
    return dfs || dfs1 || dfs2;
  }


}

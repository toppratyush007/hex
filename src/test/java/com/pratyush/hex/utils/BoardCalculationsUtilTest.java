package com.pratyush.hex.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.models.ImmutableMove;
import com.pratyush.hex.models.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
public class BoardCalculationsUtilTest {

  Board board;

  @Before
  public void setup(){
    board = new Board(3);
    board.markOccupied(new ImmutableMove(0,0, Flag.RED));
    board.markOccupied(new ImmutableMove(0,1, Flag.RED));
    board.markOccupied(new ImmutableMove(1,1, Flag.BLUE));
    board.markOccupied(new ImmutableMove(0,2, Flag.RED));
  }


  @Test
  public void checkIfSidesConnectedByColor() {
    assertTrue(BoardCalculationsUtil.checkIfSidesConnectedByColor(Flag.RED, board));
    assertFalse(BoardCalculationsUtil.checkIfSidesConnectedByColor(Flag.BLUE, board));
  }

  @Test
  public void maxLengthChainByFlag() {
    assertEquals(3, BoardCalculationsUtil.maxLengthChainByFlag(Flag.RED, board));
    assertNotEquals(3, BoardCalculationsUtil.maxLengthChainByFlag(Flag.BLUE, board));
  }
}
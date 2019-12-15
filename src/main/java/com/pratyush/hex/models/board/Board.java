package com.pratyush.hex.models.board;

import com.pratyush.hex.constants.UserMessages;
import com.pratyush.hex.models.ImmutableMove;
import com.pratyush.hex.models.Spot;
import java.util.Arrays;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Board {
  @Getter
  private Spot[][] spots;

  public Board(int dimension){
    spots = new Spot[dimension][dimension];
    for(int i=0;i<dimension;i++){
      for(int j=0;j<dimension;j++){
        spots[i][j] = new Spot(null);
      }
    }

  }

  private Board(Spot[][] spots){
    this.spots = spots;
  }

  public boolean isValid(ImmutableMove immutableMove) {
    if(immutableMove == null)
      return false;
    if(spots[immutableMove.getX()][immutableMove.getY()].isEmpty())
      return true;
    else{
      log.error(UserMessages.X_Y_OCCUPIED);
    }
    return false;
  }

  public void paint() {
    int row,col;
    for(row=0;row<spots.length;row++) {
      for (col = 0; col < spots[row].length; col++) {
        System.out.print(
            ("|" + spots[row][col].getRepresentation()));
      }
      System.out.print("|");
      System.out.println();
    }
  }

  public void markOccupied(ImmutableMove immutableMove) {
    spots[immutableMove.getX()][immutableMove.getY()] = new Spot(immutableMove.getFlag());
  }

  public Board deepCopy(){
    return new Board(Arrays.stream(spots).map(Spot[]::clone).toArray(Spot[][]::new));
  }


  public boolean isValidPosition(Integer coord) {
    if(coord == null)
      return false;
    if(coord<0 || coord>=spots.length) {
      log.error("Coord must be in between 0 and {}",spots.length);
      return false;
    }
    return true;
  }

}

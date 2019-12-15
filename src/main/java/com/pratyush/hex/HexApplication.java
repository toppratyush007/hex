package com.pratyush.hex;

import static com.pratyush.hex.constants.UserMessages.INVALID_DIMENSION;

import com.pratyush.hex.constants.Flag;
import com.pratyush.hex.exception.InvalidMoveException;
import com.pratyush.hex.helpers.MiniMaxSolvingStrategy;
import com.pratyush.hex.models.Game;
import com.pratyush.hex.models.player.CompPlayer;
import com.pratyush.hex.models.player.HumanPlayer;
import com.pratyush.hex.models.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class HexApplication {

  public static void main(String[] args) {
    initaliseApplication(args);
    Integer dimension = null;
    dimension = getDimensionInput(dimension);
    Game game = initialiseGame(dimension);
    playTillGameEnds(game);
  }

  private static void initaliseApplication(String[] args) {
    new SpringApplicationBuilder(HexApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

  private static Integer getDimensionInput(Integer dimension) {
    do {
      log.info("Provide a valid dimension size");
      try {
        dimension = new Scanner(System.in).nextInt();
      } catch (Exception e) {
        log.error("Invalid value for dimension",e);
      }
    }while (checkIfDimensionInvalid(dimension));
    return dimension;
  }

  private static boolean checkIfDimensionInvalid(Integer dimension) {
    if(dimension==null || dimension<=0) {
      log.error(INVALID_DIMENSION);
      return true;
    }
    return false;
  }

  private static Game initialiseGame(Integer dimension) {
    Player player1 = new HumanPlayer(Flag.RED);
    Player player2 = new CompPlayer(Flag.BLUE, new MiniMaxSolvingStrategy());
    return new Game(
        new ArrayList<Player>(Arrays.asList(player1, player2)),
        player1,
        dimension
    );
  }

  private static void playTillGameEnds(Game game) {
    while(!game.hasEnded()){
      try {
        game.play();
      } catch (InvalidMoveException e) {
        log.error("Invalid move played. Please provide a valid input. "+e.getMessage());
      }
    }
  }



}

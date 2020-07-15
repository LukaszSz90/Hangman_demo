import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.security.x509.OtherName;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    Hangman game;

    @BeforeEach
    public void enterClass() {
        game = new Hangman();
    }

    @Test
    public void hiddenPassword() {
        //given - game
        //when
        game.setPassword("What is password");
        String output = game.getOutput();
        //then
        assertEquals(".... .. ........", output);
    }

    @Test
    public void guessLetter_W() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("w");
        String output = game.getOutput();
        //then
        assertEquals("W... .. ....w...", output);
    }

    @Test
    public void guessLetter_P() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("p");
        String output = game.getOutput();
        //then
        assertEquals(".... .. p.......", output);
    }

    @Test
    public void guessWholePassword() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("what is password");
        String output = game.getOutput();
        //then
        assertEquals("What is password", output);
    }

    @Test
    public void guessLetterByLetter() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("w");
        game.guess("h");
        game.guess("a");
        game.guess("t");
        game.guess("i");
        game.guess("s");
        game.guess("p");
        game.guess("o");
        game.guess("r");
        game.guess("d");
        //then
        String output = game.getOutput();
        assertEquals("What is password", output);
    }

    @Test
    public void userHasFullHp() {
        //given - Hangman game = ...
        //when
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void wrongGuess() {
        //given - Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("c");
        int hp = game.getUserHp();
        //then
        assertEquals(6,hp);
    }
    @Test
    public void wrongGuessPassword() {
        //given - Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("London");
        int hp = game.getUserHp();
        //then
        assertEquals(6,hp);
    }

    @Test
    public void correctGuess() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("p");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void outOfHp() {
        //given - Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("1");
        game.guess("2");
        game.guess("3");
        game.guess("4");
        game.guess("5");
        game.guess("6");
        game.guess("7");
        int hp = game.getUserHp();
        //then
        assertEquals(0,hp);
    }

    @Test
    public void outOfHpAfterGame() {
        //given - Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("1");
        game.guess("2");
        game.guess("3");
        game.guess("4");
        game.guess("5");
        game.guess("6");
        game.guess("7");
        game.guess("8");
        game.guess("9");
        int hp = game.getUserHp();
        //then
        assertEquals(0,hp);
    }


    @Test
    public void emptySpace() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess(" ");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }
    @Test
    public void manyEmptySpace() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("     ");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void emptyGuess() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void letterBetweenSpace() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("     a    ");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void passwordBetweenSpace() {
        //given Hangman game = ...
        game.setPassword("What is password");
        //when
        game.guess("    What is password   ");
        int hp = game.getUserHp();
        //then
        assertEquals(7,hp);
    }

    @Test
    public void isGameWin() {
        //given
        game.setPassword("What is password");
        game.guess("What is password");
        //when
        boolean didWin = game.isWin();
        //then
        assertTrue(didWin);
    }


    @Test
    public void isGameLose() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        boolean didLose = game.isLose();
        //then
        assertTrue(didLose);
    }

    @Test
    public void isGameOver_Win() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("What is password");
        boolean isGameOverWin = game.isGameOver();
        //then
        assertTrue(isGameOverWin);
    }

    @Test
    public void isGameOver_Lose() {
        //given
        game.setPassword("What is password");
        //when
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        game.guess("x");
        boolean isGameOverLose = game.isGameOver();
        //then
        assertTrue(isGameOverLose);
    }

    @Test
    public void setNewPassword() {
        //given
        game.setPassword("Old password");
        game.guess("a");
        //then
        game.setPassword("New password");
        assertTrue(game.getTries().isEmpty());
    }
    @Test
    public void resetHpWithNewPassword() {
        //given
        game.setPassword("Old password");
        game.guess("x");
        //then
        game.setPassword("New password");
        int hp = game.getUserHp();
        assertEquals(7,hp);
    }

}
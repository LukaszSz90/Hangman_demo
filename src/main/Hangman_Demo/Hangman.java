import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Hangman {
    private static final int MAX_HP = 7;
    private String password;
    Set<String> guesesLetters = new HashSet<>();
    private int userHp = MAX_HP;


    public void setPassword(String password) {
        this.password = password;
        guesesLetters.clear();
        this.userHp = MAX_HP;
    }

    public String getOutput() {
        String output = "";

        for (int i = 0; i < password.length(); i++) {
            String character = password.substring(i,i+1);

            if (" ".equals(character)) {
                output += " ";
            }
            else if (guesesLetters.contains(character.toLowerCase())){
                output += character;
            }
            else {
                output += ".";
            }
        }
        return output;
    }

    public void guess(String userGuess) {
        String normalizedGuess = userGuess.trim().toLowerCase();

        if (this.userHp > 0) {
            if (normalizedGuess.length() == 0) {
                return;
            }
            else if (normalizedGuess.length() == 1) {
                guesesLetters.add(normalizedGuess);
                if (!password.toLowerCase().contains(normalizedGuess)) {
                    userHp--;
                }
            } else if (normalizedGuess.equalsIgnoreCase(this.password)) {
                for (int i = 0; i < normalizedGuess.length(); i++) {
                    String letter = normalizedGuess.substring(i, i + 1);
                    guess(letter);
                }
            }
            else {
                userHp--;
            }
        }

    }

    public Set<String> getTries() {
        Set<String> output = new TreeSet<>(guesesLetters);
        return output;
    }

    public int getUserHp() {
        return userHp;
    }

    public boolean isWin() {
        return getOutput().equalsIgnoreCase(this.password);
    }

    public boolean isLose() {
        return this.userHp == 0;
    }

    public boolean isGameOver() {
        return isWin() || isLose();
    }
}

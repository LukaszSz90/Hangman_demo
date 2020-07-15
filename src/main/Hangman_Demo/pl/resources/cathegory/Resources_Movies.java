package pl.resources.cathegory;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Resources_Movies {
    public static final List<String> MOVIELIST = Arrays.asList(
            "Jurassic Park",
            "The Green Mile",
            "The Shawshank Redemption",
            "Forrest Gump",
            "The Matrix",
            "The Silence of the Lambs",
            "Gladiator",
            "Avatar",
            "Shrek",
            "Pulp Fiction",
            "Titanic",
            "The Sixth Sense",
            "The Godfather",
            "Inception",
            "Pirates of the Caribbean",
            "The Hangover",
            "The Pianist",
            "The Lord of the Rings",
            "The Butterfly Effect",
            "The Da Vinci Code",
            "Django",
            "The Dark Knight",
            "The Lion King",
            "The Wolf of Wall Street",
            "I am Legend",
            "Sherlock Holmes",
            "Edward Scissorhands"
    );

    public static String getRandomPassword() {
       int index = MOVIELIST.size();
       int randomPasswordIndex = new Random().nextInt(index);
        return MOVIELIST.get(randomPasswordIndex);
    }
}

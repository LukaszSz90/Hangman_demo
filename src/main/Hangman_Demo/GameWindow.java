import pl.resources.cathegory.Resources_Movies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

class GameWindow {
    private JFrame frame;
    private JLabel passwordLabel;
    private JLabel hpLabel;
    private JButton button;
    private JTextField input;
    private JTextArea triesLetters;
    private JLabel imageLabel;
    private Hangman game = new Hangman();

    public GameWindow(){
        frame = new JFrame("Hangman game");
        frame.setSize(400,580);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);


        imageLabel = new JLabel("new ImageIcon(hangPicture)");
        imageLabel.setIcon(new ImageIcon("hang_main.png"));
        imageLabel.setBounds(70,0,250,250);
        frame.add(imageLabel);

        passwordLabel = new JLabel(".....");
        passwordLabel.setBounds(100,230, 300, 100);
        passwordLabel.setFont(setFont(30));
        frame.add(passwordLabel);

        input = new JTextField("");
        input.setBounds(150,330,100,30);
        input.setFont(setFont(18));
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent event) {
                System.out.println(event);
                if (event.getKeyCode() == 10) {
                    handleOkButton();
                }
            }
        });
        frame.add(input);

        button = new JButton("OK");
        button.setBounds(100, 370,200,50);
        button.addActionListener(actionEvent -> handleOkButton());
        frame.add(button);

        triesLetters = new JTextArea("a,b,c,d");
        triesLetters.setBounds(50,430,300,40);
        triesLetters.setFont(setFont(18));
        frame.add(triesLetters);

        hpLabel = new JLabel("HP = 7/7");
        hpLabel.setBounds(150,480,300,50);
        hpLabel.setFont(setFont(20));
        frame.add(hpLabel);

        game.setPassword(Resources_Movies.getRandomPassword());
        refreshUI();
        frame.setVisible(true);
    }

    private void handleOkButton() {
        if (game.isGameOver()) {
            resetGame();
        }
        else {
            String guess = input.getText();
            game.guess(guess);
            input.setText("");
        }
        refreshUI();
    }

    private void refreshUI() {
        refreshPassword();
        refreshHp();
        refreshTries();
        refreshButton();
        refreshImage();
    }

    private void refreshImage() {
        int hpSwitch = game.getUserHp();
        switch (hpSwitch) {
            case 7: {
                imageLabel.setIcon(new ImageIcon("hang_0.png"));
            } break;
            case 6: {
                imageLabel.setIcon(new ImageIcon("hang_1.png"));
            } break;
            case 5: {
                imageLabel.setIcon(new ImageIcon("hang_2.png"));
            }break;
            case 4: {
                imageLabel.setIcon(new ImageIcon("hang_3.png"));
            } break;
            case 3: {
                imageLabel.setIcon(new ImageIcon("hang_4.png"));
            } break;
            case 2: {
                imageLabel.setIcon(new ImageIcon("hang_5.png"));
            } break;
            case 1: {
                imageLabel.setIcon(new ImageIcon("hang_6.png"));
            } break;
            default: {
                imageLabel.setIcon(new ImageIcon("hang_7.png"));
            }
        }
    }

    private void refreshButton() {
        if (game.isGameOver())
            button.setText("Restart game");
        else
            button.setText("OK");
    }

    private void refreshTries() {
        Set<String> triesList = new TreeSet<>(game.getTries());
        StringBuilder builder = new StringBuilder();
        for (String element:triesList) {
            builder.append(element).append(" ");
        }
        String output = builder.toString();
        triesLetters.setText(output);
    }

    private void refreshHp() {
        if (game.isWin()) {
            hpLabel.setText("You win");
        }
        else if (game.isLose()) {
            hpLabel.setText("You lose");
        }
        else {
            int currentHp = game.getUserHp();
            String userHP = String.format("Chance: %d", currentHp);
            hpLabel.setText(userHP);
        }
    }

    private void refreshPassword() {
        String output = game.getOutput();
        passwordLabel.setText(output);
    }


    private void resetGame() {
        game.setPassword(Resources_Movies.getRandomPassword());
    }

    private Font setFont(int size) {
        return new Font("Serif", Font.PLAIN, size);
    }

}

package TIC_TAC_TOE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TIC_TAC_TOE_game extends JFrame implements ActionListener{
     private JButton[][] buttons;
    private boolean isPlayerX;
    private JLabel turnLabel;
    private int scoreX;
    private int scoreO;
    private JLabel scoreLabel;

    public TIC_TAC_TOE_game() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);

        isPlayerX = true;
        turnLabel = new JLabel("Player X's turn");
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(turnLabel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        scoreX = 0;
        scoreO = 0;
        scoreLabel = new JLabel("Score: X - " + scoreX + "  O - " + scoreO);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(scoreLabel);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        bottomPanel.add(restartButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().isEmpty()) {
            if (isPlayerX) {
                button.setText("X");
                turnLabel.setText("Player O's turn");
            } else {
                button.setText("O");
                turnLabel.setText("Player X's turn");
            }

            isPlayerX = !isPlayerX;
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() && buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][0].getText().equals(buttons[i][2].getText())) {
                displayWinner(buttons[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().isEmpty() && buttons[0][j].getText().equals(buttons[1][j].getText())
                    && buttons[0][j].getText().equals(buttons[2][j].getText())) {
                displayWinner(buttons[0][j].getText());
                return;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().isEmpty() && buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[0][0].getText().equals(buttons[2][2].getText())) {
            displayWinner(buttons[0][0].getText());
            return;
        }

        if (!buttons[0][2].getText().isEmpty() && buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[0][2].getText().equals(buttons[2][0].getText())) {
            displayWinner(buttons[0][2].getText());
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }

        if (isDraw) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }
    private void displayWinner(String player) {
        String message = "Player " + player + " wins!";
        if (player.equals("X")) {
            scoreX++;
        } else {
            scoreO++;
        }
        scoreLabel.setText("Score: X - " + scoreX + "  O - " + scoreO);
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        // Highlight the winning combination
        highlightWinningCombination(player);

        resetGame();
    }

    private void highlightWinningCombination(String player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) && buttons[i][1].getText().equals(player) && buttons[i][2].getText().equals(player)) {
                buttons[i][0].setBackground(Color.GREEN);
                buttons[i][1].setBackground(Color.GREEN);
                buttons[i][2].setBackground(Color.GREEN);
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(player) && buttons[1][j].getText().equals(player) && buttons[2][j].getText().equals(player)) {
                buttons[0][j].setBackground(Color.GREEN);
                buttons[1][j].setBackground(Color.GREEN);
                buttons[2][j].setBackground(Color.GREEN);
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player)) {
            buttons[0][0].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][2].setBackground(Color.GREEN);
            return;
        }

        if (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player)) {
            buttons[0][2].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][0].setBackground(Color.GREEN);
            return;
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(null); // Reset button background color
            }
        }

        isPlayerX = true;
        turnLabel.setText("Player X's turn");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TIC_TAC_TOE_game());
    }
}
}

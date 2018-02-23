package ticTacToe;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	private enum GameStatus {
		ZeroWon, CrossWon, Tie, Incomplete
	}

	private boolean crossturn = true;
	public static final int BOARD_SIZE = 3;
	public static final String CROSS = "X";
	public static final String ZERO = "O";

	private JButton[][] button = new JButton[BOARD_SIZE][BOARD_SIZE];

	public TTT() {
		super.setTitle(" Tic Tac Toe");

		GridLayout layout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(layout);
		Font font = new Font("Comic Scans", 1, 150);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton();

				button.setFont(font);
				button.addActionListener(this);
				this.button[row][col] = button;

				super.add(button);
			}
		}

		super.setResizable(false);

		super.setSize(1000, 1000);
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clickedButton = (JButton) e.getSource();
		// clickedButton.setBackground(Color.cyan);
		makemove(clickedButton);
		GameStatus gs = getGameStatus();
		if (gs == GameStatus.Incomplete) {
			return;
		} else {
			declarewinner(gs);
		}
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to replay?");

		if (choice == JOptionPane.YES_OPTION) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				for (int col = 0; col < BOARD_SIZE; col++) {
					JButton button = this.button[row][col];
					button.setText("");
				}
			}

			this.crossturn = true;

		} else

		{
			super.dispose();
		}
	}

	private void makemove(JButton clickedButton) {
		if (clickedButton.getText().length() > 0) {
			JOptionPane.showMessageDialog(this, "move not allowed!");
			return;
		}
		if (crossturn) {
			clickedButton.setText(CROSS);
		} else {

			clickedButton.setText(ZERO);
		}
		crossturn = !crossturn;
	}

	private GameStatus getGameStatus() {
		int row = 0, col = 0;
		String text1 = "", text2 = "";

		// winner from rows
		for (row = 0; row < BOARD_SIZE; row++) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = button[row][col].getText();
				text2 = button[row][col + 1].getText();
				if (text1.length() == 0 || !text1.equals(text2)) {
					break;

				}
				col++;

			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals(CROSS)) {
					return GameStatus.CrossWon;
				} else {
					return GameStatus.ZeroWon;
				}
			}
		}

		// winner from columns
		for (col = 0; col < BOARD_SIZE; col++) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = button[row][col].getText();
				text2 = button[row + 1][col].getText();
				if (text1.length() == 0 || !text1.equals(text2)) {
					break;

				}
				row++;

			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals(CROSS)) {
					return GameStatus.CrossWon;
				} else {
					return GameStatus.ZeroWon;
				}
			}
		}
		// winner from diagonals 1
		row = 0;
		col = 0;

		while (row < BOARD_SIZE - 1) {
			text1 = button[row][col].getText();
			text2 = button[row + 1][col + 1].getText();
			if (text1.length() == 0 || !text1.equals(text2)) {
				break;

			}
			row++;
			col++;

		}
		if (col == BOARD_SIZE - 1) {
			if (text1.equals(CROSS)) {
				return GameStatus.CrossWon;
			} else {
				return GameStatus.ZeroWon;
			}
		}

		// winner from diagonals 2
		row = 0;
		col = BOARD_SIZE - 1;

		while (row < BOARD_SIZE - 1) {
			text1 = button[row][col].getText();
			text2 = button[row + 1][col - 1].getText();
			if (text1.length() == 0 || !text1.equals(text2)) {
				break;

			}
			row++;
			col--;

		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals(CROSS)) {
				return GameStatus.CrossWon;
			} else {
				return GameStatus.ZeroWon;
			}
		}

		// for incomplete
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col++) {
				JButton button = this.button[row][col];
				if (button.getText().length() == 0) {
					return GameStatus.Incomplete;
				}
			}
		}
		return GameStatus.Tie;

	}

	private void declarewinner(GameStatus gs) {
		if (gs == GameStatus.CrossWon) {
			JOptionPane.showMessageDialog(this, "X WON!");
		} else if (gs == GameStatus.ZeroWon) {
			JOptionPane.showMessageDialog(this, "O WON!");
		} else {
			JOptionPane.showMessageDialog(this, "TIE!");
		}
	}
}
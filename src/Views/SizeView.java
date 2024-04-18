package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SizeView extends JFrame {



    public  SizeView(){
        JLabel rowsLabel = new JLabel("Rows:");
        JTextField rowsField = new JTextField(10);
        JLabel columnsLabel = new JLabel("Columns:");
        JTextField columnsField = new JTextField(10);


        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            int rows = Integer.parseInt(rowsField.getText());
            int columns = Integer.parseInt(columnsField.getText());

            if (rows < 10 || rows > 100 || columns < 10 || columns > 100) {
                JOptionPane.showMessageDialog(rowsField, "Rows or Columns must be between 10 and 100", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                setVisible(false);
                new GameView(rows, columns);
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {

                    returnToMainMenu();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(rowsLabel);
        inputPanel.add(rowsField);
        inputPanel.add(columnsLabel);
        inputPanel.add(columnsField);
        inputPanel.add(startButton);


        add(inputPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }
    private void returnToMainMenu() {
        dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}

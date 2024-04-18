package Others;

import Objects.Enemy;
import Objects.GameEntity;
import Objects.Player;
import Objects.Wall;
import Updates.Upgrade;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GameBoardCellRenderer extends DefaultTableCellRenderer {
    private GameBoardModel gameBoard;

    public GameBoardCellRenderer(GameBoardModel gameBoard) {
        this.gameBoard = gameBoard;
        setOpaque(true);
        setPreferredSize(new Dimension(15, 15));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);


        GameEntity entity = gameBoard.getEntityAt(row, col);

        if (entity != null) {
            if (entity instanceof Wall) {
                component.setBackground(Color.BLUE);
                component.setIcon(null);
            }
            else if (entity instanceof Upgrade) {
                component.setBackground(Color.BLACK);
                component.setIcon(new ImageIcon(entity.getImage()));
            }
            else if (entity instanceof Player || entity instanceof Enemy){
                component.setBackground(Color.BLACK);
                component.setIcon(new ImageIcon(entity.getImage()));
            }
            else {

                component.setBackground(Color.BLACK);
                component.setIcon(new ImageIcon("./src/Images/Others/point.png"));
                component.setText("");
            }
        } else {

            component.setBackground(Color.BLACK);
            component.setIcon(null);
        }
        component.setText("");
        return component;
    }
}

package Others;

import Objects.GameEntity;
import Objects.Wall;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class GameBoardModel extends AbstractTableModel {
    private Object[][] board;
    private List<GameEntity> entities;

    public GameBoardModel(int rows, int columns, List<GameEntity> entities) {
        board = new Object[rows][columns];

        this.entities = entities;
    }

    public void addEntity(GameEntity entity) {
        board[entity.getRow()][entity.getColumn()] = entity;
        fireTableDataChanged();
    }

    public GameEntity getEntityAt(int row, int col) {
        for (GameEntity entity : entities) {
            if (entity.getRow() == row && entity.getColumn() == col) {
                return entity;
            }
        }
        return null;
    }

    public void removeEntity(GameEntity entity) {
        board[entity.getRow()][entity.getColumn()] = null;
        fireTableDataChanged();
    }

    public boolean isWallAt(int row, int col) {

        for (GameEntity entity : entities) {

            if (entity instanceof Wall && entity.getRow() == row && entity.getColumn() == col) {

                return true;
            }
        }

        return false;
    }

    @Override
    public int getRowCount() {
        return board.length;
    }

    @Override
    public int getColumnCount() {
        return board[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        for (GameEntity entity : entities) {

            if (entity.getRow() == rowIndex && entity.getColumn() == columnIndex) {
                return entity;
            }
        }

        return board[rowIndex][columnIndex];
    }


}

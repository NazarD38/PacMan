package Objects;

import java.awt.*;

public class GameEntity {
    protected int row;
    protected int column;
    protected int dx;
    protected int dy;
    private Image image;

    String imagePath;
    boolean hasPoint;

    public GameEntity(int row, int column) {
        this.row = row;
        this.column = column;
        this.hasPoint = false;
    }

    public boolean hasPoint() {
        return hasPoint;
    }

    public void setHasPoint(boolean hasPoint) {
        this.hasPoint = hasPoint;
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean imageIs(String otherImagePath) {
        String one = otherImagePath.split("/")[otherImagePath.split("/").length - 1];
        String two = imagePath.split("/")[imagePath.split("/").length - 1];
        return one.equals(two);
    }

}

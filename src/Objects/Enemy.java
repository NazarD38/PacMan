package Objects;

import Others.ImageLoader;

public class Enemy extends GameEntity {

    private boolean frozen;
    private static int counter = 0;
    private static final String[] imagePaths = {
            "./src/Images/Ghosts/ghost-red.png",
            "./src/Images/Ghosts/ghost-cyan.png",
            "./src/Images/Ghosts/ghost-pink.png",
            "./src/Images/Ghosts/ghost-orange.png"
    };

    public Enemy(int row, int column) {
        super(row, column);
        this.frozen = false;
        setImage(ImageLoader.loadImage(imagePaths[counter % imagePaths.length]));
        counter++;
        this.setDirection(-1, 0);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void freeze() {
        frozen = true;
    }

    public void unfreeze() {
        frozen = false;
    }

}
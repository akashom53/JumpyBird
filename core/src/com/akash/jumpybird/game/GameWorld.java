package com.akash.jumpybird.game;

import com.akash.jumpybird.game.menus.MainMenu;
import com.akash.jumpybird.game.objects.Bird;
import com.akash.jumpybird.helpers.AssetLoader;
import com.akash.jumpybird.helpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

class GameWorld implements InputHandler.Listener, ScrollHandler.Listener {
    public static final String KEY_HIGH_SCORE = "HIGH_SCORE";
    private static final String TAG = "GameWorld";
    private Bird bird;
    private ScrollHandler scrollHandler;
    private Rectangle ground;
    private int score = 0;
    private GameState currentState;
    private int midPointY;
    private MainMenu mainMenu;
    private float scale;

    public enum GameState {

        READY, RUNNING, GAMEOVER, HIGHSCORE

    }

    public GameWorld(int screenWidth, int screenHeight, int midPointY) {
        scale = Gdx.graphics.getWidth()/136.0f;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scrollHandler = new ScrollHandler(midPointY + 66, this);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        currentState = GameState.READY;
        mainMenu = new MainMenu(screenWidth, screenHeight);

    }

    void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }
    }

    private void updateReady(float delta) {
        mainMenu.update(delta);
    }

    private void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }
        scrollHandler.update(delta);
        bird.update(delta);

        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()){
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int increment) {
        score += increment;
    }

    @Override
    public void onTap(int screenX, int screenY) {
        int scaledX = (int) (screenX / scale);
        int scaledY = (int) (screenY / scale);
        if (isReady() && mainMenu.checkPlayButtonPressed(scaledX, scaledY)){
            start();
        }
        bird.jump();
        if (isGameOver() || isHighScore()){
            restart();
        }
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}

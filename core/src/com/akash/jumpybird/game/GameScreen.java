package com.akash.jumpybird.game;

import com.akash.jumpybird.helpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private static final String TAG = "GameScreen";
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);
        world = new GameWorld((int)gameWidth, (int)gameHeight, midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void show() {
//        Gdx.app.log(TAG, "show()");
    }

    @Override
    public void resize(int width, int height) {
//        Gdx.app.log(TAG, String.format("resize(%d, %d)", width, height));
    }

    @Override
    public void pause() {
//        Gdx.app.log(TAG, "pause()");
    }

    @Override
    public void resume() {
//        Gdx.app.log(TAG, "resume()");
    }

    @Override
    public void hide() {
//        Gdx.app.log(TAG, "hide()");
    }

    @Override
    public void dispose() {
//        Gdx.app.log(TAG, "dispose()");
    }
}

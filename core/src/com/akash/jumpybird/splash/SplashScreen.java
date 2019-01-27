package com.akash.jumpybird.splash;

import com.akash.jumpybird.helpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

    private SpriteBatch spriteBatch;
    private int width, height, x, y;

    public SplashScreen() {
        spriteBatch = new SpriteBatch();
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        width = screenWidth - 40;
        height = (int) ((AssetLoader.akkipediaLogoTexture.getHeight() / (float)AssetLoader.akkipediaLogoTexture.getWidth()) * width);
        x = (int) (screenWidth / 2f - width / 2f);
        y = (int) (screenHeight/2f - height/2f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.draw(
                AssetLoader.akkipediaLogoTexture,
                x,
                y,
                width,
                height
        );
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        AssetLoader.disposeSplashAssets();
    }
}

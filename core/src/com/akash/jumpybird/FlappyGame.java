package com.akash.jumpybird;

import com.akash.jumpybird.game.GameScreen;
import com.akash.jumpybird.helpers.AssetLoader;
import com.akash.jumpybird.splash.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;

public class FlappyGame extends Game {
    private static final String TAG = "FlappyGame";
    private Screens currentScreen;
    private long splashShownTime = -1;
    private final long splashDelay = 3000;

    private enum Screens{
        GAMESCREEN, SPLASHSCREEN
    }

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");
        AssetLoader.loadGameAssets();
        AssetLoader.loadSplashAssets();
//        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        super.render();
        if (AssetLoader.update() && shouldHideSplash()){
            setCurrentScreen(Screens.GAMESCREEN);
        } else {
            float progress = AssetLoader.getProgress();
            setCurrentScreen(Screens.SPLASHSCREEN);
        }
    }

    private boolean shouldHideSplash(){
        if (splashShownTime < 0)
            return false;
        return ((TimeUtils.millis() - splashShownTime) >= splashDelay);
    }

    private void setCurrentScreen(Screens screen){
        if (currentScreen == screen)
            return;

        currentScreen = screen;
        if (screen == Screens.GAMESCREEN){
            setScreen(new GameScreen());
        } else if (screen == Screens.SPLASHSCREEN) {
            splashShownTime = TimeUtils.millis();
            setScreen(new SplashScreen());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}

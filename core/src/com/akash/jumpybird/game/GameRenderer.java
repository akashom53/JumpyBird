package com.akash.jumpybird.game;

import com.akash.jumpybird.game.objects.Bird;
import com.akash.jumpybird.game.objects.Grass;
import com.akash.jumpybird.game.objects.Pipe;
import com.akash.jumpybird.helpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class GameRenderer {
    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private int midPointY;
    private int gameHeight;
    private Bird bird;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    private TextureRegion bg, grass;
    private Animation<TextureRegion> birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

    GameRenderer(GameWorld world, int height, int midPointY) {
        this.world = world;
        this.gameHeight = height;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        bird = world.getBird();
        scroller = world.getScrollHandler();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        // Draw the grass
        spriteBatch.draw(
                grass,
                frontGrass.getX(),
                frontGrass.getY(),
                frontGrass.getWidth(),
                frontGrass.getHeight()
        );
        spriteBatch.draw(
                grass,
                backGrass.getX(),
                backGrass.getY(),
                backGrass.getWidth(),
                backGrass.getHeight()
        );
    }

    private void drawSkulls() {
        spriteBatch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        spriteBatch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        spriteBatch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        spriteBatch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        spriteBatch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        spriteBatch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.disableBlending();
        spriteBatch.draw(bg, 0, midPointY + 23, 136, 43);

        drawGrass();

        drawPipes();
        spriteBatch.enableBlending();

        drawSkulls();

        if (bird.shouldntFlap()) {
            spriteBatch.draw(
                    birdMid,
                    bird.getX(),
                    bird.getY(),
                    bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f,
                    bird.getWidth(),
                    bird.getHeight(),
                    1,
                    1,
                    bird.getRotation()
            );

        } else {
            spriteBatch.draw(
                    birdAnimation.getKeyFrame(runTime),
                    bird.getX(),
                    bird.getY(),
                    bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f,
                    bird.getWidth(),
                    bird.getHeight(),
                    1,
                    1,
                    bird.getRotation()
            );
        }

        if (world.isReady()) {
            world.getMainMenu().render(spriteBatch);
        } else {

            if (world.isGameOver() || world.isHighScore()) {
                if (world.isGameOver()) {
                    AssetLoader.shadow.draw(spriteBatch, "Game Over", 25, 56);
                    AssetLoader.font.draw(spriteBatch, "Game Over", 24, 55);

                    AssetLoader.shadow.draw(spriteBatch, "High Score:", 23, 106);
                    AssetLoader.font.draw(spriteBatch, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    // Draw shadow first
                    AssetLoader.shadow.draw(spriteBatch, highScore, (136 / 2)
                            - (3 * highScore.length()), 128);
                    // Draw text
                    AssetLoader.font.draw(spriteBatch, highScore, (136 / 2)
                            - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(spriteBatch, "High Score!", 19, 56);
                    AssetLoader.font.draw(spriteBatch, "High Score!", 18, 55);
                }

                AssetLoader.shadow.draw(spriteBatch, "Try again?", 23, 76);
                AssetLoader.font.draw(spriteBatch, "Try again?", 24, 75);

                // Convert integer into String
                String score = world.getScore() + "";

                // Draw shadow first
                AssetLoader.shadow.draw(spriteBatch, score,
                        (136 / 2) - (3 * score.length()), 12);
                // Draw text
                AssetLoader.font.draw(spriteBatch, score,
                        (136 / 2) - (3 * score.length() - 1), 11);


            }

            // Convert integer into String
            String score = world.getScore() + "";

            // Draw shadow first
            AssetLoader.shadow.draw(spriteBatch, "" + world.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            // Draw text
            AssetLoader.font.draw(spriteBatch, "" + world.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);
        }

        spriteBatch.end();
    }

}

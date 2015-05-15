/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import integrationGTGE.PublishingSpriteFactory;
import integrationGTGE.PublishingSprite;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.GameField;
import model.ball.Ball;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.Paddle;
import view.BallView;
import view.BreakableBrickView;
import integrationGTGE.GameFieldView;
import view.PaddleView;
import view.UnbreakableBrickView;

/**
 * Класс фабрики для создания игровых объектов
 * 
 * @author Елена
 */
public class IngameObjectFactory {

    /** Представление игрового поля */
    private GameFieldView _gameFieldView;
    /** Модель игрового поля */
    private GameField _gameField;
    /** Фабрика для создания спрайтов */
    private PublishingSpriteFactory _publishingSpriteFactory;

    /**
     * Созать фабрику
     * 
     * @param gameFieldView Представление игрового поля
     * @param gameField Модель игрового поля
     */
    public IngameObjectFactory(GameFieldView gameFieldView, GameField gameField) {
        
        _gameFieldView = gameFieldView;
        _gameField = gameField;
        _publishingSpriteFactory = new PublishingSpriteFactory(_gameFieldView);
        initResourses();
    }

    /**
     * Создать мяч
     * 
     * @return модель мячя
     */
    public Ball createBall() {
        
        // Создание модели мяча и добавление в модель игрового поля
        PublishingSprite publishingSprite = _publishingSpriteFactory.newBasicBallPublishigSprite();
        Ball ball = new Ball(publishingSprite);
        _gameField.addListenerToObject(ball);
        _gameField.addObject(ball);

        // Создание представления мяча и добавление в представление игрового поля
        BallView ballView = new BallView(ball, publishingSprite);
        _gameFieldView.addObject(ballView);
        _gameFieldView.addListenerToObject(ball);
        return ball;
    }

    /**
     * Создать ракетку
     * 
     * @return модель ракетки
     */
    public Paddle createPaddle() {
        
        // Создание модели ракетки и добавление в модель игрового поля
        PublishingSprite publishingSprite = _publishingSpriteFactory.newBasicPaddlePublishingSprite();
        Paddle paddle = new Paddle(publishingSprite);
        _gameField.addObject(paddle);
        _gameField.addListenerToObject(paddle);
        
        // Создание представления ракетки и добавление в представление игрового поля
        PaddleView paddleView = new PaddleView(paddle, publishingSprite);
        _gameFieldView.addObject(paddleView);
        _gameFieldView.addListenerToObject(paddle);
        return paddle;
    }

    /**
     * Создать неразрушаемый кирпич
     * 
     * @return модель неразрушаемого кирпича
     */
    public UnbreakableBrick createUnbreakableBrick() {
        
        // Создание модели неразрушаемого кирпича и добавление в модель игрового поля
        PublishingSprite publishingSprite = _publishingSpriteFactory.newUnbreakableBrickPublishingSprite();
        UnbreakableBrick unbreakableBrick = new UnbreakableBrick(publishingSprite);
        _gameField.addObject(unbreakableBrick);
        _gameField.addListenerToObject(unbreakableBrick);
        
        // Создание представления неразрушаемого кирпича и добавление в представление игрового поля
        UnbreakableBrickView unbreakableBrickView = new UnbreakableBrickView(unbreakableBrick, publishingSprite);
        _gameFieldView.addObject(unbreakableBrickView);
        _gameFieldView.addListenerToObject(unbreakableBrick);
        return unbreakableBrick;
    }

    /**
     * Создать разрушаемый кирпич
     * 
     * @return модель разрушаемого кирпича
     */
    public BreakableBrick createBreakableBrick() {
        
        // Создание модели разрушаемого кирпича и добавление в модель игрового поля
        PublishingSprite publishingSprite = _publishingSpriteFactory.newBreakableBrickPublishingSprite();
        BreakableBrick breakableBrick = new BreakableBrick(publishingSprite);
        _gameField.addObject(breakableBrick);
        _gameField.addListenerToObject(breakableBrick);
        
        // Создание представления разрушаемого кирпича и добавление в представление игрового поля
        BreakableBrickView breakableBrickView = new BreakableBrickView(breakableBrick, publishingSprite);
        _gameFieldView.addObject(breakableBrickView);
        _gameFieldView.addListenerToObject(breakableBrick);
        return breakableBrick;
    }

    /**
     * Задать изображения для спрайтов
     * 
     */
    private void initResourses() {
        
        try {
            BufferedImage basicBallImage = ImageIO.read(new File("default/gfx/balls/basic.png"));
            BufferedImage breakableBrickImage = ImageIO.read(new File("default/gfx/bricks/breakable.png"));
            BufferedImage unbreakableBrickImage = ImageIO.read(new File("default/gfx/bricks/unbreakable.png"));
            BufferedImage basicPaddleImage = ImageIO.read(new File("default/gfx/paddles/basic.png"));
            _publishingSpriteFactory.setBasicBallImage(basicBallImage);
            _publishingSpriteFactory.setBasicPaddleImage(basicPaddleImage);
            _publishingSpriteFactory.setBreakableBrickImage(breakableBrickImage);
            _publishingSpriteFactory.setUnbreakableBrickImage(unbreakableBrickImage);
        } catch (IOException ex) {
            Logger.getLogger(IngameObjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

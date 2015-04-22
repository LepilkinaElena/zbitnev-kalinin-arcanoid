/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.GameField;
import model.ball.Ball;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.Paddle;
import view.BallView;
import view.BreakableBrickView;
import view.GameFieldView;
import view.PaddleView;
import view.UnbreakableBrickView;

/**
 *
 * @author Елена
 */
public class IngameObjectFactory {
    private GameFieldView _fieldView;
    private GameField _gameField;
    private PublishingSpriteFactory _publishingSpriteFactory;

    public IngameObjectFactory(GameFieldView fieldView, GameField field) {
        _fieldView = fieldView;
        _gameField = field;
        _publishingSpriteFactory = new PublishingSpriteFactory(_fieldView);
    }
    
    public void createBall() {
        PublishingSprite sprite = _publishingSpriteFactory.newBasicBallPublishigSprite();
        Ball ball = new Ball(sprite);
        _gameField.addListenerToObject(ball);
        _gameField.addObject(ball);
        
        BallView ballView = new BallView(ball, sprite);
        _fieldView.addObject(ballView);
        _fieldView.addListenerToObject(ball);
    }
    
    public void createPaddle() {
        PublishingSprite sprite = _publishingSpriteFactory.newBasicPaddlePublishingSprite();
        Paddle paddle = new Paddle(sprite);
        _gameField.addObject(paddle);
        _gameField.addListenerToObject(paddle);
        PaddleView paddleView = new PaddleView(paddle, sprite);
        _fieldView.addObject(paddleView);
        _fieldView.addListenerToObject(paddle);
    }
    
    public void createUnbreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newUnbreakableBrickPublishingSprite();
        UnbreakableBrick unbreakableBrick = new UnbreakableBrick(sprite);
        _gameField.addObject(unbreakableBrick);
        _gameField.addListenerToObject(unbreakableBrick);
        UnbreakableBrickView unbreakableBrickView = new UnbreakableBrickView(unbreakableBrick, sprite);
        _fieldView.addObject(unbreakableBrickView);
        _fieldView.addListenerToObject(unbreakableBrick);
    }
    
    public void createBreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newBreakableBrickPublishingSprite();
        BreakableBrick breakableBrick = new BreakableBrick(sprite);
        _gameField.addObject(breakableBrick);
        _gameField.addListenerToObject(breakableBrick);
        BreakableBrickView breakableBrickView = new BreakableBrickView(breakableBrick, sprite);
        _fieldView.addObject(breakableBrickView);
        _fieldView.addListenerToObject(breakableBrick);
    } 
    
    
}

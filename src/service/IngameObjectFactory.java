/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.GameField;
import model.IngameObject;
import model.ball.Ball;
import view.GameFieldView;

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
        _gameField.addObject(ball);
        
        BallView ballView = new BallView(sprite);
        _fieldView.add(ballView);
    }
    
    public void createPaddle() {
        PublishingSprite sprite = _publishingSpriteFactory.newBasicPaddlePublishingSprite();
        Paddle paddle = new Paddle(sprite);
        _gameField.addObject(paddle);
        
        PaddleView paddleView = new PaddleView(sprite);
        _fieldView.add(paddleView);
    }
    
    public void createUnbreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newUnbreakableBrickPublishingSprite();
        UnbreakableBrick unbreakableBrick = new UnbreakableBrick(sprite);
        _gameField.addObject(unbreakableBrick);
        
        UnbreakableBrickView unbreakableBrickView = new UnbreakableBrickView(sprite);
        _fieldView.add(unbreakableBrickView);
    }
    
    public void createBreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newBreakableBrickPublishingSprite();
        BreakableBrick breakableBrick = new BreakableBrick(sprite);
        _gameField.addObject(breakableBrick);
        
        BreakableBrickView breakableBrickView = new BreakableBrickView(sprite);
        _fieldView.add(breakableBrickView);
    } 
    
    
}

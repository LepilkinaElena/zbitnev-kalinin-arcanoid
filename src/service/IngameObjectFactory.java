/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
        initResourses();
    }
    
    public Ball createBall() {
        PublishingSprite sprite = _publishingSpriteFactory.newBasicBallPublishigSprite();
        Ball ball = new Ball(sprite);
        _gameField.addListenerToObject(ball);
        _gameField.addObject(ball);
        
        BallView ballView = new BallView(ball, sprite);
        _fieldView.addObject(ballView);
        _fieldView.addListenerToObject(ball);
        return ball;
    }
    
    public Paddle createPaddle() {
        PublishingSprite sprite = _publishingSpriteFactory.newBasicPaddlePublishingSprite();
        Paddle paddle = new Paddle(sprite);
        _gameField.addObject(paddle);
        _gameField.addListenerToObject(paddle);
        PaddleView paddleView = new PaddleView(paddle, sprite);
        _fieldView.addObject(paddleView);
        _fieldView.addListenerToObject(paddle);
        return paddle;
    }
    
    public UnbreakableBrick createUnbreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newUnbreakableBrickPublishingSprite();
        UnbreakableBrick unbreakableBrick = new UnbreakableBrick(sprite);
        _gameField.addObject(unbreakableBrick);
        _gameField.addListenerToObject(unbreakableBrick);
        UnbreakableBrickView unbreakableBrickView = new UnbreakableBrickView(unbreakableBrick, sprite);
        _fieldView.addObject(unbreakableBrickView);
        _fieldView.addListenerToObject(unbreakableBrick);
        return unbreakableBrick;
    }
    
    public BreakableBrick createBreakableBrick() {
        PublishingSprite sprite = _publishingSpriteFactory.newBreakableBrickPublishingSprite();
        BreakableBrick breakableBrick = new BreakableBrick(sprite);
        _gameField.addObject(breakableBrick);
        _gameField.addListenerToObject(breakableBrick);
        BreakableBrickView breakableBrickView = new BreakableBrickView(breakableBrick, sprite);
        _fieldView.addObject(breakableBrickView);
        _fieldView.addListenerToObject(breakableBrick);
        return breakableBrick;
    } 
    
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

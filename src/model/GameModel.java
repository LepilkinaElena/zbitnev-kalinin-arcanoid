package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import model.ball.Ball;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.collisionProcessing.IngameObject;
import model.interaction.BallFailEvent;
import model.interaction.BallFailListener;
import model.paddle.Paddle;
import service.IngameObjectFactory;

/**
 * Модель игры.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameModel {

    protected GameField _gameField = null;
    protected Player _player = new Player();
    private EndGame _listener = new EndGame();
    private IngameObjectFactory _ingameObjectfactory;

    /**
     * Назначить игровое поле
     *
     * @param field
     */
    public void setField(GameField field) {

        if (field == null) {
            throw new NullPointerException();
        }
        _gameField = field;
    }

    /**
     * Получить игровое поле
     *
     * @return Текущее поле
     */
    public GameField getField() {

        return _gameField;
    }

    /**
     * Установить модели нового игрока
     *
     * @param player
     */
    public void setPlayer(Player player) {
        _player = player;
    }

    /**
     * Полуить игроков модели
     *
     * @return
     */
    public Player getPlayer() {

        return (Player) _player.clone();
    }

    /**
     * Обновляет модель. В реализации данного класса ничего не делает.
     *
     * @param arg Аргумент.
     */
    public void update(Object arg) {

    }

    public void initLevel(IngameObjectFactory ingameObjectFactory) {
        _ingameObjectfactory = ingameObjectFactory;
        Ball newball = ingameObjectFactory.createBall();
        newball.setPosition(new Point2D.Float(40, 160));
        newball.setSpeed(new Speed2D(0.3, -0.3));
        newball.initSpecialBehaviours();
        newball.addBallFailListener(_listener);
        Ball newball1 = ingameObjectFactory.createBall();
        newball1.setPosition(new Point2D.Float(300, 100));
        newball1.setSpeed(new Speed2D(-0.15, 0.15));
        newball1.initSpecialBehaviours();
        newball1.addBallFailListener(_listener);
        Ball newball2 = ingameObjectFactory.createBall();
        newball2.setPosition(new Point2D.Float(100, 300));
        newball2.setSpeed(new Speed2D(-0.15, 0.15));
        newball2.initSpecialBehaviours();
        newball2.addBallFailListener(_listener);
        Ball newball3 = ingameObjectFactory.createBall();
        newball3.setPosition(new Point2D.Float(300, 300));
        newball3.setSpeed(new Speed2D(-0.15, -0.15));
        newball3.initSpecialBehaviours();
        newball3.addBallFailListener(_listener);
        BreakableBrick newbrick = ingameObjectFactory.createBreakableBrick();
        newbrick.setPosition(new Point2D.Float(180, 120));
        BreakableBrick newbrick2 = ingameObjectFactory.createBreakableBrick();
        newbrick2.setPosition(new Point2D.Float(228, 120));
        UnbreakableBrick newbrick3 = ingameObjectFactory.createUnbreakableBrick();
        newbrick3.setPosition(new Point2D.Float(276, 120));
        newbrick.initSpecialBehaviours();
        newbrick2.initSpecialBehaviours();
        Paddle paddle = ingameObjectFactory.createPaddle();
        paddle.initSpecialBehaviours();
        paddle.setPosition(new Point2D.Float(230, 584));
        _player = new Player(paddle);
        paddle.addBall(newball);
    }

    public void startGame() {
        _player.startAttempt();
    }

    public void proccessPlayerAction(Speed2D speed2D) {
        _player.processAction(speed2D);
    }

    public void proccessPlayerAction(Direction direction) {
        _player.processAction(direction);
    }
    
    public void proccessPlayerAction() {
        _player.processAction();
    }

    private class EndGame implements BallFailListener {
        @Override
        public void endGame(BallFailEvent ballFailEvent) {
            ArrayList<IngameObject> objects = _gameField.getElements("model.ball.Ball");
            if (objects.isEmpty()) {
                _gameField.clear();
                initLevel(_ingameObjectfactory);
            }
        }
        
    }
}

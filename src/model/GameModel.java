package model;

import java.awt.geom.Point2D;
import model.ball.Ball;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.Paddle;
import service.IngameObjectFactory;

/**
 * Модель игры.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameModel {

    protected GameField _field = null;
    protected Player _player = new Player();
    
	

	/**
	 * Назначить игровое поле
	 * @param field
	 */
	public void setField(GameField field) {
		
		if (field == null) {
		    throw new NullPointerException();
		}
		_field = field;
	}
	
	/**
	 * Получить игровое поле
	 * @return Текущее поле
	 */
	public GameField getField() {
		
		return _field;
	}
	
	/**
	 * Установить модели нового игрока
	 * @param player
	 */
	public void setPlayer(Player player) {
            _player = player;
	}
	
	/**
	 * Полуить игроков модели
	 * @return
	 */
	public Player getPlayer() {
		
		return (Player) _player.clone();
	}
	
	/**
	 * Обновляет модель. В реализации данного класса ничего не делает.
	 * @param arg Аргумент.
	 */
	public void update(Object arg) {
	    
	}
        
    public void initLevel(IngameObjectFactory factory) {
        Ball newball = factory.createBall();
        newball.setPosition(new Point2D.Float(40, 160));
        newball.setSpeed(new Speed2D(0.03, -0.01));
        newball.initSpecialBehaviours();
        BreakableBrick newbrick = factory.createBreakableBrick();
        newbrick.setPosition(new Point2D.Float(180, 120));
        BreakableBrick newbrick2 = factory.createBreakableBrick();
        newbrick2.setPosition(new Point2D.Float(276, 120));
        UnbreakableBrick newbrick3 = factory.createUnbreakableBrick();
        newbrick3.setPosition(new Point2D.Float(276, 120));
        newbrick.initSpecialBehaviours();
        newbrick2.initSpecialBehaviours();
        Paddle paddle = factory.createPaddle();
        paddle.initSpecialBehaviours();
        paddle.setPosition(new Point2D.Float(0, 584));
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
}

package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.paddle.Paddle;

/**
 * Модель игрока, управляющего ракеткой.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Player {

    protected Paddle _paddle;
    
	/**
	 * Инициализировать игрока
	 * @param paddle Подконтрольная игроку ракетка
	 */
	public Player(Paddle paddle) {
            _paddle = paddle;
	}
	
	public Player() {
		
	}
	
        public Player clone() {
            Player clonePlaer = new Player((Paddle) _paddle.clone());
            return clonePlaer;
        }
        
	/**
	 * Установить ракетку для контроля игрока
	 * @param paddle Ракетка для добавления
	 */
	public void setPaddle(Paddle paddle) {
	    _paddle = paddle;
	}
	
	/**
	 * Переместить все свои ракетки в указанную позицию по горизонтали
	 * @param pos Позиция
	 */
	public void setPaddlePositionX(int x) {
		
	        int actualx;
	        if (x > _paddle.getField().getSize().width - _paddle.getSize().width) {
	            actualx = _paddle.getField().getSize().width - _paddle.getSize().width;
	        } else if (x < 0) {
	            actualx = 0;
	        } else {
	            actualx = x;
	        }
	        _paddle.setPosition(new Point2D.Float(actualx, _paddle.getPosition().y));
	}
	
	/**
	 * Переместить все свои ракетки в указанном направлении.
	 * Величину сдвига жёстко задана внутри класса.
	 * @param dir Направление перемещения
	 */
	/*public void movePaddles(Direction dir) {
		
	    for (Paddle p : _paddles) {
	        long delta = Math.round(p.getSize().width / 3.0 * 2.0);
	        delta = dir.equals(Direction.west()) ? -delta : delta;
            if (p.getPosition().x + p.getSize().width + delta > p.getField().getSize().width) {
                p.setPosition(new Point2D.Float(p.getField().getSize().width - p.getSize().width, p.getPosition().y));
            } else if (p.getPosition().x + delta < 0) {
                p.setPosition(new Point2D.Float(0, p.getPosition().y));
            } else {
                p.move(new Point2D.Float(delta, 0));
            }
        }
	}*/
	
	/**
	 * Заставляет подконтрольные ракетки задать скорость мячам.
	 */
	public void pushBalls() {
	     _paddle.pushBalls();
	}
}

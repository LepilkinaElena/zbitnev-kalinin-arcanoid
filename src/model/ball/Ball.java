package model.ball;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourBallRebound;
import model.collision.BehaviourReflect;
import model.interaction.BallFailEvent;
import model.interaction.BallFailListener;
import model.paddle.Paddle;
import service.PublishingSprite;

/**
 * Модель шарика
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Ball extends IngameObject {
    
    /** Слушатели падения мячика за нижнюю границу*/
    private ArrayList<BallFailListener> _ballFailListeners = new ArrayList();

    /**
     * Создать мяч
     * 
     * @param sprite спрайт для мяча
     */
    public Ball(PublishingSprite sprite) {
        
        super(sprite);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }
    
    /**
     * Создать мяч (конструктор для мяча, являющегося абстракцией системы)
     * 
     * @param axis ось
     */
    public Ball(Speed2D.Axis axis) {
        
        super(null);
        setAxis(axis);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }

    /**
     * Создать мяч
     * 
     * @param publishingSprite спрайт мяча
     * @param speed2D скорость
     */
    public Ball(PublishingSprite publishingSprite, Speed2D speed2D) {
        
        super(publishingSprite, speed2D);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }

    @Override
    public void setPosition(Point2D.Float pos) {
        
        super.setPosition(pos);
    }

    /**
     * Клонировать мяч
     * 
     * @return клон мяча
     */
    public Ball clone() {

        Ball clone = (Ball) super.clone();
        return clone;
    }

    /**
     * Инициализировать специальные поведения, характерные для мяча по текущим правилам
     * 
     */
    public void initSpecialBehaviours() {
        
        addSpecificCollisionBehaviour(Ball.class, BehaviourBallRebound.getInstance());
        addSpecificCollisionBehaviour(Paddle.class, BehaviourPaddleRebound.getInstance());
    }
    
    @Override
    public void destroy() {
        
        super.destroy();
        fireBallFail();
    }
    
    //-------------------------- Работа со слушателями -----------------------
    
    /**
     * Добавить слушателя падения
     * 
     * @param ballFailListener слушатель
     */
    public void addBallFailListener (BallFailListener ballFailListener) {
        
        _ballFailListeners.add(ballFailListener);
    }    
    
    /**
     * Известить всех слушателей о падении мяча за нижнюю границу
     * 
     */
    private void fireBallFail() {
        
        for (BallFailListener listener: _ballFailListeners) {
            listener.endGame(new BallFailEvent(this));
        }
    }
}

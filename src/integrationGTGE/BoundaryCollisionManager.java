/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationGTGE;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;

/**
 * Менеджер столкновений с границами (при изменении библиотеки заменить!)
 * 
 * @author Елена
 */
public class BoundaryCollisionManager extends CollisionBounds {

    /** Карта со столкновениями */
    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    /** Поле, с границами которого отлавливается столкновение */
    private GameField _gameField;
    /** Позиция до столкновения */
    private Point2D.Float _oldPosition;
    /** Список слушателей события столкновения с границами */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();

    /**
     * Создать менеджер столкновений с границами
     * 
     * @param x координата х поля границ
     * @param y координата у поля границ
     * @param width ширина поля границ
     * @param height высота поля границ
     * @param gameField поле
     */
    public BoundaryCollisionManager(int x, int y, int width, int height, GameField gameField) {
        
        super(x, y, width, height);
        _gameField = gameField;
    }

    @Override
    public void collided(Sprite sprite) {
        
        // Обработать случившуюся коллизию
        _storage.clear();
        _oldPosition = new Point2D.Float((float)sprite.getOldX(), (float) sprite.getOldY());
        _storage.put(_gameField.getObject(sprite.getID()), null);
        fireIngameObjectCollided();
    }

    //------------------------ Работа со слушателями ---------------------
    
    /**
     * Добавление слушателей события о том, что столкнулись игровые объекты
     *
     * @param element слушатель
     */
    public void addCollisionListener(CollisionListener element) {
        
        _collisionListener.add(element);
    }

    /**
     * Испустить событие о том, что столкнулись игровые объекты
     */
    private void fireIngameObjectCollided() {
        
        for (CollisionListener listener : _collisionListener) {
            listener.collisionOccured(new CollisionEvent(this, getSide(), _oldPosition, _storage));
        }
    }
    
    /**
     * Получить сторону столкновения
     * 
     * @return сторона столкновения
     */
    private int getSide() {
        
        int side;
        if(isCollisionSide(TOP_COLLISION)) {
            side = TOP_COLLISION;
        } else if(isCollisionSide(BOTTOM_COLLISION)) {
            side = BOTTOM_COLLISION;
        } else if(isCollisionSide(RIGHT_COLLISION)) {
            side = RIGHT_COLLISION;
        } else {
            side = LEFT_COLLISION;
        }
        return side;
    }
    
}

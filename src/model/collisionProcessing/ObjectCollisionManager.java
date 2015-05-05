/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collisionProcessing;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Boundary;
import model.GameField;
import model.Speed2D;
import model.Speed2D.Axis;
import model.ball.Ball;
import model.brick.Brick;
import model.collision.BoundaryCollisionManager;
import model.collision.PublishingCollisionManager;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;

/**
 *
 * @author Елена
 */
public class ObjectCollisionManager {

    private PublishingCollisionManager _objectManager;
    private BoundaryCollisionManager _boundaryManager;

    public ObjectCollisionManager(GameField field) {
        CollisionOccuredListener listener = new CollisionOccuredListener();
        _objectManager = new PublishingCollisionManager(field);
        _boundaryManager = new BoundaryCollisionManager(0, 0, field.getSize().width, field.getSize().height, field);
        _objectManager.addCollisionListener(listener);
        _boundaryManager.addCollisionListener(listener);
    }

    public CollisionManager getObjectManager() {
        return _objectManager;
    }

    public CollisionManager getBoundaryManager() {
        return _boundaryManager;
    }
    
    /**
     * Получить ось для отскока от стороны
     * 
     * @param side сторона
     * @return ось
     */
    private Speed2D.Axis getAxis(int side) {
        Speed2D.Axis axis;
        if (side == CollisionBounds.LEFT_COLLISION || side == CollisionBounds.RIGHT_COLLISION) {
            axis = Speed2D.Axis.Y;
        } else {
            axis = Speed2D.Axis.X;
        }
        return axis;
    }

    private class CollisionOccuredListener implements CollisionListener {

        @Override
        public void collisionOccured(CollisionEvent event) {
            ArrayList<IngameObject> system;
            HashMap<IngameObject, ArrayList<IngameObject>> storage = event.getStorage();

            Set<IngameObject> keys = storage.keySet();

             system = getSystem(storage);
            // Если есть система
            if (!system.isEmpty()) {
                // Обработать столкновения внутри системы
                boolean wasProccessed = handleSystem(system);
                // Удалить спрайты, входящие в систему
                ArrayList<Object> keyList = new ArrayList<>();
                keyList.addAll(keys);
                for (int i = 0; i < keyList.size() && wasProccessed; i++) {
                    if (system.contains((IngameObject) keyList.get(i))) {
                        storage.remove((IngameObject) keyList.get(i));
                        keyList.remove(i);
                        i--;
                    }
                }
            }

            for (IngameObject key : keys) {
                if (key != null) {
                    if (storage.get(key) == null) {
                        //столкновение с границей
                        if (key instanceof Ball && getAxis(event.side()) == Axis.X && event.side() == CollisionBounds.BOTTOM_COLLISION) {
                           key.destroy();
                        } 
                        else {
                            if (getAxis(event.side()) == Speed2D.Axis.X) {
                                key.setPosition(new Point2D.Float((float)key.getPosition().getX(), (float)(event.xBound().getY())));
                            } else if (getAxis(event.side()) == Speed2D.Axis.Y) {
                                key.setPosition(new Point2D.Float((float)event.xBound().getX(), (float)(key.getPosition().getY())));
                            } else {
                                key.setPosition(new Point2D.Float((float)event.xBound().getX(), (float)(event.xBound().getY())));
                            }
                            key.processCollision(new Boundary(getAxis(event.side())));
                        }
                    } else {
                        ArrayList<IngameObject> objectsClone = new ArrayList<>();
                        for (IngameObject object : storage.get(key)) {
                            if (object != null) {
                                objectsClone.add((IngameObject) object.clone());
                            }
                        }
                        /*for (IngameObject object : objectsClone) {
                            key.processCollision(object);
                        }*/
                        storage.put(key, objectsClone);
                    }
                }
            }
            
            for (IngameObject key : keys) {
                if (key != null) {
                    if (storage.get(key) != null) {

                        for (IngameObject object : storage.get(key)) {
                            key.processCollision(object);
                        }
                    }
                }
            }
            //storage.clear();
            system.clear();
            //event.clearStorage();
        }

    }

    /**
     * Обработать столкновение с системой
     *
     * @param system система
     */
    private boolean handleSystem(ArrayList<IngameObject> system) {
        ArrayList<IngameObject> cloneElements = new ArrayList<>();
        // Карта элемент-> клоны, столкнувшихся элементов, которые должнв рассматриваться как один элемент
        HashMap<IngameObject, ArrayList<IngameObject>> elementsAsOne = new HashMap<>();
        // Получение элементов и клонов
        for (IngameObject element : system) {
            cloneElements.add((IngameObject) element);
        }
        // Создание карты
        for (IngameObject element : system) {
            ArrayList<IngameObject> elementArray = new ArrayList<>();
            for (IngameObject other : cloneElements) {
                if (system.indexOf(element) != cloneElements.indexOf(other)) {
                    elementArray.add(other);
                }
            }
            elementsAsOne.put(element, elementArray);
        }
        // Обработка каждого элемента системы
        for (Map.Entry<IngameObject, ArrayList<IngameObject>> entrySet : elementsAsOne.entrySet()) {
            try {
                IngameObject key = entrySet.getKey();
                ArrayList<IngameObject> values = entrySet.getValue();
                IngameObject firstElement = values.get(0);
                if (firstElement.equals(key)) {
                    firstElement = values.get(1);
                }
                boolean isSameClass = true;
                for (IngameObject value: values) {
                    if (!value.equals(key)) {
                        if (!(firstElement instanceof Brick && value instanceof Brick)) {
                            isSameClass = isSameClass && firstElement.getClass() == value.getClass();
                        }
                    
                    }
                }
                // Создание элемента на основе нескольких
                if (isSameClass) {
                    Class classElement = values.get(0).getClass();
                    Constructor[] construct = classElement.getDeclaredConstructors();
                    Speed2D resultSpeed = new Speed2D();
                    IngameObject firstObject = values.get(0);
                    Speed2D.Axis axis = Speed2D.Axis.X;
                    for (IngameObject value : values) {
                        if (value.getPosition().getY() == firstObject.getPosition().getY()) {
                            axis = Speed2D.Axis.X;
                        } else {
                            axis = Speed2D.Axis.Y;
                        }
                        resultSpeed = resultSpeed.sum(value.getSpeed());
                        
                    }
                    Constructor chosenConstructor = null;
                    for (Constructor constr:construct) {
                        if (constr.getGenericParameterTypes().length == 1 && constr.getGenericParameterTypes()[0] == Speed2D.Axis.class) {
                            chosenConstructor = constr; 
                        }
                    }
                    if (chosenConstructor != null) {
                        Object otherElement = chosenConstructor.newInstance(axis);

                        ((IngameObject) otherElement).setSpeed(resultSpeed);
                        key.processCollision((IngameObject) otherElement);
                        for (IngameObject value : values) {
                            value.processCollision(key.clone());
                        }
                        return true;
                    }
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ObjectCollisionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ObjectCollisionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ObjectCollisionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ObjectCollisionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Получить систему, образованную несколькими соударившимися элементами
     *
     * @param storage хранилище соударений
     * @return список, содержащий элементы, входящие в систему
     */
    private ArrayList<IngameObject> getSystem(Map storage) {
        ArrayList<IngameObject> result = new ArrayList<>();
        // Создание системы связанных соударившихся элементов
        for (Object keyObject : storage.keySet()) {
            if (keyObject != null) {
                result.add((IngameObject) keyObject);
                formListForElement((IngameObject) keyObject, storage, result);
                // Есть система, а не простое соударение
                if (result.size() >= 3) {
                    return result;
                } else {
                    result = new ArrayList<>();
                }
            }
        }

        return result;
    }

    /**
     * Сформировать список элементов, с которыми стокнулся по цепочке
     *
     * @param sprite спрайт, для которого определяем
     * @param storage хранилище со столкновениями
     * @param list список, куда заносится цепочка столкновений
     */
    private void formListForElement(IngameObject object, Map storage, ArrayList<IngameObject> list) {
        Set keySet = storage.keySet();
        if (keySet.contains(object)) {
            if (storage.get(object) != null) {
                ArrayList<IngameObject> valueSprites = (ArrayList<IngameObject>) storage.get(object);
                for (IngameObject value : valueSprites) {
                    if (!list.contains(value) && value != null) {
                        list.add(value);
                        formListForElement(value, storage, list);
                    }
                }
            }
        }
    }
}

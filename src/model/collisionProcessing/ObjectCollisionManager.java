/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collisionProcessing;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionRect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameField;
import model.Speed2D;
import model.collision.BoundaryCollisionManager;
import model.collision.CollisionBehaviour;
import model.collision.PublishingCollisionManager;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;


/**
 *
 * @author Елена
 */
public class ObjectCollisionManager {
    private PublishingCollisionManager _objectManager;
    private BoundaryCollisionManager _boundaManager;
    
    public ObjectCollisionManager(GameField field) {
        CollisionOccuredListener listener = new CollisionOccuredListener();
        _objectManager = new PublishingCollisionManager(field);
        _boundaManager = new BoundaryCollisionManager(0, 0, field.getSize().width, field.getSize().height, field);
        _objectManager.addCollisionListener(listener);
        _boundaManager.addCollisionListener(listener);
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
                        handleSystem(system);
                        // Удалить спрайты, входящие в систему
                        for ( Object keyObject : storage.keySet()) {
                            if (system.contains((IngameObject)keyObject)) {
                                storage.remove((IngameObject)keyObject);
                            }
                        }
                    }
            
            for (IngameObject key : keys) {
                
                if (storage.get(key).isEmpty()) {
                    //столкновение с границей
                } else {
                    ArrayList<IngameObject> objectsClone = new ArrayList<>();
                    IngameObject cloneKey = (IngameObject) key.clone();
                    for (IngameObject object : storage.get(key)) {
                        objectsClone.add((IngameObject) object.clone());
                        object.processCollision(cloneKey);
                    }
                    for (IngameObject object : objectsClone) {
                        key.processCollision(object);                        
                    }
                }
            }
        }
        
    }
    
    /**
     * Обработать столкновение с системой
     * 
     * @param system система
     */
    private void handleSystem(ArrayList<IngameObject> system) {
        ArrayList<IngameObject> cloneElements = new ArrayList<>();
        // Карта элемент-> клоны, столкнувшихся элементов, которые должнв рассматриваться как один элемент
        HashMap<IngameObject, ArrayList<IngameObject>> elementsAsOne = new HashMap<>();
        // Получение элементов и клонов
        for (IngameObject element :system) {
            cloneElements.add((IngameObject) element.clone());
        }
        // Создание карты
        for (IngameObject element:system) {
            ArrayList<IngameObject> elementArray = new ArrayList<>();
            for (IngameObject other:cloneElements) {
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
                // Создание элемента на основе нескольких
                Class classElement = values.get(0).getClass();
                Constructor[] construct =  classElement.getDeclaredConstructors();
                Object otherElement = construct[0].newInstance();
                Speed2D resultSpeed = new Speed2D();
                for (IngameObject value: values) {
                    resultSpeed = resultSpeed.sum(value.getSpeed());
                }
                ((IngameObject)otherElement).setSpeed(resultSpeed);
                key.processCollision((IngameObject)otherElement);
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
        for ( Object keyObject: storage.keySet()) {
            result.add((IngameObject)keyObject);
            formListForElement((IngameObject)keyObject, storage, result);
            // Есть система, а не простое соударение
            if (result.size() >= 3) {
                return result;
            } else {
                result = new ArrayList<>();
            }
        }
        
        return result;
    }
    
    /**
     * Сформировать список элементов, с которыми стокнулся по цепочке
     * @param sprite спрайт, для которого определяем
     * @param storage хранилище со столкновениями
     * @param list список, куда заносится цепочка столкновений
     */
    private void formListForElement(IngameObject object, Map storage, ArrayList<IngameObject> list) {
        Set keySet = storage.keySet();
        if (keySet.contains(object)) {
            IngameObject[] valueSprites = (IngameObject[]) storage.get(object);
            for (IngameObject value:valueSprites) {
                if (!list.contains(value)) {
                    list.add(value);
                    formListForElement(value, storage, list);
                }
            }
        }
    }
}
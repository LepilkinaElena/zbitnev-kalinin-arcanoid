/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

import java.util.EventObject;

/**
 * Событие удаления объекта
 * 
 * @author Елена
 */
public class GenericEvent extends EventObject {

    /** Идентификатор объекта */
    private int elementId;

    /**
     * Создать событие
     * 
     * @param source объект источник
     * @param id идентификатор удаляемго объекта
     */
    public GenericEvent(Object source, int id) {
        
        super(source);
        elementId = id;
    }

    /**
     * Получить идентификатор удаляемго объекта
     * @return идентификатор удаляемго объекта
     */
    public int getElementId() {
        
        return elementId;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

import java.util.EventObject;

/**
 *
 * @author Елена
 */
public class GenericEvent extends EventObject {

    private int elementId;

    public GenericEvent(Object source, int id) {
        super(source);
        elementId = id;
    }

    public int getElementId() {
        return elementId;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

import java.util.EventObject;

/**
 * Класс падения мяча
 * 
 * @author Мария
 */
public class BallFailEvent extends EventObject {

    public BallFailEvent(Object source) {
        super(source);
    }
    
}

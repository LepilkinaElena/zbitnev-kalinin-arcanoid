/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

/**
 * Интерфейс слушателя падения мяча
 * 
 * @author Мария
 */
public interface BallFailListener {

    /**
     * Закончить игру
     * 
     * @param ballFailEvent событие падение мяча
     */
    public void endGame(BallFailEvent ballFailEvent);
    
}

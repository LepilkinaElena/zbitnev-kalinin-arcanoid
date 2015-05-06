/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.collisionProcessing.IngameObject;

/**
 * Класс для псевдо-границ (сделан, чтобы можно было вызывать invoke для поведений
 * одинаково)
 *
 * @author Елена
 */
public class Boundary extends IngameObject {

    public Boundary(Speed2D.Axis axis) {
        super();
        setAxis(axis);
    }
}

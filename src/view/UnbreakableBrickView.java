/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import integrationGTGE.IngameObjectView;
import model.collisionProcessing.IngameObject;
import integrationGTGE.PublishingSprite;

/**
 * Класс представления неразрушаемого кирпича
 * 
 * @author Елена
 */
public class UnbreakableBrickView extends IngameObjectView {

    public UnbreakableBrickView(IngameObject ingameObject, PublishingSprite publishingSprite) {
        
        super(ingameObject, publishingSprite);
    }

}

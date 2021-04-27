/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;

import Battleship.logic.BdController;

public class GameLaunch {
        /**
         * @param args the command line arguments
        */
    public static void main(String[] args) {
        // TODO code application logic here
        BdController bd = new BdController ();
        GameModel model = new GameModel(10, 10, 4, bd);
        GameView view = new GameView(model);
        view.setVisible(true);		
    }
}

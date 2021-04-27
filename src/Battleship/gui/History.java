/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;

/**
 *
 * @author Администратор
 */
public class History {
    private String win;
    private int ship;
    private int point;
    
    public History(String win, int ship, int point){
        this.win = win;
        this.ship=ship;
        this.point=point;
    }
    
    public String getWin(){
    return this.win;
    }
    
    public int getShip(){
        return this.ship;
    }
    
    public int getPoint(){
        return this.point;
    }
    
}

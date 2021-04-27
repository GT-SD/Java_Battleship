/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;

import Battleship.logic.Field;
import Battleship.logic.Cell;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

abstract public class GameField extends JPanel {
    private Field field;
	
    public Field getField() {
        return field;
    }

    public GameField(Field field) {
        this.field = field;
    }

    private int getCellWidth() {
        return getWidth() / getField().getWidth();
    }

    private int getCellHeight() {
        return getHeight() / getField().getHeight();
    }
	
    abstract protected Color getColorByStateElement(int state);
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // рисуем решётку
        for(int i = 0; i < getField().getWidth() + 1; i++) {
            g.drawLine(i * getCellWidth(), 0, i * getCellWidth(), getCellHeight() * getField().getHeight());
        }
        for(int i = 0; i < getField().getHeight() + 1; i++) {
            g.drawLine(0, i * getCellHeight(), getCellWidth() * getField().getWidth(), i * getCellHeight());
        }
        // рисуем элементы
        for(int j = 0; j < getField().getHeight(); j++) {
            for(int i = 0; i < getField().getWidth(); i++) {
                int state = field.getCell(i, j).getStatus();
                g.setColor(getColorByStateElement(state));
                if (state == Cell.CELL_MISS) {
                    g.fillRect(i*getCellWidth() + (getCellWidth() / 2) - 1, j*getCellHeight() + (getCellHeight() / 2) - 1, 4, 4);
                } else {
                    g.fillRect(i*getCellWidth()+1, j*getCellHeight()+1, getCellWidth() - 1, getCellHeight() - 1);
                }
            }
        }
    }
}

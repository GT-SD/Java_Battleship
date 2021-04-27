/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class GameHistoryTable implements TableModel{
    private ArrayList<History> hist;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    
    public GameHistoryTable (ArrayList<History> hist){
        this.hist = hist;
    }
    
    @Override
    public int getRowCount() {
        return hist.size();
    }
    
    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Победитель";
        case 1:
            return "Кол-во кораблей";
        case 2:
            return "Очки";
        }
    return "";
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        History his = hist.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return his.getWin();
            case 1:
                return his.getShip();
            case 2:
                return his.getPoint();
            default:
                return "";
        }
    }
    
    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}

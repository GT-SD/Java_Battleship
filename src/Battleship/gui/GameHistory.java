/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class GameHistory extends JFrame{
	
    JTable jTable1;
    JScrollPane scrollPane;
    
    
    
    public GameHistory(ArrayList<History> hist) {
        this.setTitle("История игр");
        TableModel model = new GameHistoryTable(hist);
        jTable1 = new JTable(model);
        scrollPane = new JScrollPane(jTable1);
        this.getContentPane().add(scrollPane);
        jTable1.setVisible(true);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
        
    }        
}

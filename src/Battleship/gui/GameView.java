package Battleship.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class GameView extends JFrame {
    private GameHistory frameHistory;
    
    private GameModel model;

    private JMenuItem newgame;
    private JMenuItem exit;
    private JMenuItem rulGame;
    private JMenuItem gameHistory;
    
    private JLabel playerTopLabel;
    private JLabel enemyTopLabel;
    
    private GameFieldPlayer fieldPlayer;
    private GameFieldEnemy fieldEnemy;
    
    
	
    public GameView(GameModel model) {
        this.model = model;
        frame();
        this.model.signOn(fieldPlayer);
        this.model.signOn(fieldEnemy);
        setListeners();
    }

    //назначение кнопок
    private void setListeners() {
        rulGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                "     Цель игры: уничтожить корабли противника раньше, чем он уничтожит ваши.\n" +
                "Для выстрела щелкните на нужной клетке правого поля. Если вы попали в корабль,\n"+ 
                "то эта клетка закрасится красным - ранили корабль, иначе появится точка - мимо.\n"+ 
                "Раненный корабль необходимо потопить, стреляя в соседние прилегающие клетки,\n"+ 
                "пока он не будет найден полность, тогда эти клетки станут серыми. Компьютер\n"+ 
                "сделает свой ход автоматически.", "Правила игры", JOptionPane.PLAIN_MESSAGE);
            }
        });
        newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.newGame();
            }
        });
        gameHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.bd.getHist().clear();
                model.bd.selectHistory();
                frameHistory = new GameHistory(model.bd.getHist());
                frameHistory.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });       
        fieldEnemy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameField field =  fieldEnemy;
                int x = e.getX() / (field.getWidth() / field.getField().getWidth());
                int y = e.getY() / (field.getHeight() / field.getField().getHeight());
                if ( field.getField().insideBorder(x, y) ) {
                    model.makeShotByPlayer(x, y);
                }
                model.getResult();             
            }
        });
    }

    private void frame() {
        model.bd.createDatabase();
        this.setTitle("Морской бой");
        this.setResizable(false);
        this.setBounds(0, 0, 366, 230);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        fieldPlayer = new GameFieldPlayer(model.playerFieldPlayer);
        fieldPlayer.setBounds(17, 35, 151, 151);
        fieldPlayer.setBackground(new Color(255, 255, 255));
        this.getContentPane().add(fieldPlayer);

        fieldEnemy = new GameFieldEnemy(model.playerFieldEnemy);
        fieldEnemy.setBounds(200, 35, 151, 151);
        fieldEnemy.setBackground(new Color(255, 255, 255));
        this.getContentPane().add(fieldEnemy);

        playerTopLabel = new JLabel("А  Б   В   Г  Д  Е  Ж  З  И  К");
        playerTopLabel.setBounds(21, 17, 151, 20);
        this.getContentPane().add(playerTopLabel);
        
        enemyTopLabel = new JLabel("А  Б   В   Г  Д  Е  Ж  З  И  К");
        enemyTopLabel.setBounds(204, 17, 151, 20);
        this.getContentPane().add(enemyTopLabel);
        
        JLabel player1 = new JLabel("1");
        player1.setBounds(9, 37, 10, 10);
        this.getContentPane().add(player1);
        JLabel player2 = new JLabel("2");
        player2.setBounds(9, 53, 10, 10);
        this.getContentPane().add(player2);
        JLabel player3 = new JLabel("3");
        player3.setBounds(9, 68, 10, 10);
        this.getContentPane().add(player3);
        JLabel player4 = new JLabel("4");
        player4.setBounds(9, 83, 10, 10);
        this.getContentPane().add(player4);
        JLabel player5 = new JLabel("5");
        player5.setBounds(9, 98, 10, 10);
        this.getContentPane().add(player5);
        JLabel player6 = new JLabel("6");
        player6.setBounds(9, 113, 10, 10);
        this.getContentPane().add(player6);
        JLabel player7 = new JLabel("7");
        player7.setBounds(9, 128, 10, 10);
        this.getContentPane().add(player7);
        JLabel player8 = new JLabel("8");
        player8.setBounds(9, 143, 10, 10);
        this.getContentPane().add(player8);
        JLabel player9 = new JLabel("9");
        player9.setBounds(9, 158, 10, 10);
        this.getContentPane().add(player9);
        JLabel player10 = new JLabel("10");
        player10.setBounds(2, 171, 15, 15);
        this.getContentPane().add(player10);
        
        JLabel enemy1 = new JLabel("1");
        enemy1.setBounds(190, 37, 10, 10);
        this.getContentPane().add(enemy1);
        JLabel enemy2 = new JLabel("2");
        enemy2.setBounds(190, 53, 10, 10);
        this.getContentPane().add(enemy2);
        JLabel enemy3 = new JLabel("3");
        enemy3.setBounds(190, 68, 10, 10);
        this.getContentPane().add(enemy3);
        JLabel enemy4 = new JLabel("4");
        enemy4.setBounds(190, 83, 10, 10);
        this.getContentPane().add(enemy4);
        JLabel enemy5 = new JLabel("5");
        enemy5.setBounds(190, 98, 10, 10);
        this.getContentPane().add(enemy5);
        JLabel enemy6 = new JLabel("6");
        enemy6.setBounds(190, 113, 10, 10);
        this.getContentPane().add(enemy6);
        JLabel enemy7 = new JLabel("7");
        enemy7.setBounds(190, 128, 10, 10);
        this.getContentPane().add(enemy7);
        JLabel enemy8 = new JLabel("8");
        enemy8.setBounds(190, 143, 10, 10);
        this.getContentPane().add(enemy8);
        JLabel enemy9 = new JLabel("9");
        enemy9.setBounds(190, 158, 10, 10);
        this.getContentPane().add(enemy9);
        JLabel enemy10 = new JLabel("10");
        enemy10.setBounds(183, 171, 15, 15);
        this.getContentPane().add(enemy10);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 543, 21);
        this.getContentPane().add(menuBar);

        JMenu menuGame = new JMenu("Игра");
        menuBar.add(menuGame);

        newgame = new JMenuItem("Новая игра");
        menuGame.add(newgame);
        
        gameHistory = new JMenuItem("История игр");
        menuGame.add(gameHistory);

        exit = new JMenuItem("Выход");
        menuGame.add(exit);

        JMenu menuHelp = new JMenu("Справка");
        menuBar.add(menuHelp);

        rulGame = new JMenuItem("Правила игры");
        menuHelp.add(rulGame);
    }
}

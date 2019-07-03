import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI{
    Game game = new Game();
    JLabel jLabels[][] = new JLabel[4][4];
    JFrame jFrame = new JFrame();//开始游戏界面框架
    JFrame jFrame1 = new JFrame();//游戏主界面
    JPanel mainPanel = new JPanel();
    JPanel panel = new JPanel();
    Font font = new Font("楷体",Font.PLAIN,33);

    //开始界面
    UI(){
        JPanel panel_start = new JPanel();
        panel_start.setLayout(null);

        JLabel title = new JLabel("2048游戏",SwingConstants.CENTER);
        title.setFont(font);
        title.setBounds(100,150,200,50);
        panel_start.add(title);

        JButton start_game = new JButton("开始游戏");
        start_game.setBounds(100,300,200,50);
        start_game.setFont(font);
        panel_start.add(start_game);


        jFrame.setTitle("2048");
        jFrame.setSize(400,600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(panel_start);
        jFrame.setVisible(true);

        start_game.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.dispose();
                jFrame1.setVisible(true);
            }
        });
    }
    //主游戏界面
    void game_UI(Game game){
        this.game = game;

        mainPanel.setLayout(new GridLayout(4,4,10,10));

        jLabels_add();//往主游戏界面里添加jLabel

        jFrame1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                jFrame1_keyPressed(e);
            }
        });

        jFrame1.setTitle("2048");
        jFrame1.setSize(500,500);
        jFrame1.setLocationRelativeTo(null);
        jFrame1.setResizable(false);
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame1.getContentPane().add(mainPanel);
    }
    //游戏结束时弹出GameOver的提示对话框
    void dialog_GameOver(){
        JDialog dialog = new JDialog(jFrame,"提示",true);
        dialog.setSize(250,150);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(jFrame);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel messageLabel = new JLabel("游戏结束",JLabel.CENTER);
        messageLabel.setFont(font);
        messageLabel.setBounds(50,0,150,40);
        JButton button_ok = new JButton("确定");
        button_ok.setFont(font);
        button_ok.setBounds(75,60,100,30);

        button_ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                jFrame1.dispose();
            }
        });

        panel.add(messageLabel);
        panel.add(button_ok);
        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }
    //初始化jLabels，向mainPanel
    public void jLabels_add(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(this.game.numbers[i][j] == 0){
                    jLabels[i][j] = new JLabel("");
                }else {
                    jLabels[i][j]= new JLabel(Integer.toString(this.game.numbers[i][j]),JLabel.CENTER);
                    jLabels[i][j].setFont(font);
                }
                jLabels[i][j].setOpaque(true);//设置背景不透明
                setColor(i,j,jLabels[i][j].getText());
                mainPanel.add(jLabels[i][j]);
            }
        }
    }
    //主游戏界面方向监听
    public void jFrame1_keyPressed(KeyEvent e){
        game.before_numbers = game.numbers;
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                game.Left();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                game.Right();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                game.Up();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                game.Down();
                break;
        }
        mainPanel.removeAll();
        jLabels_add();
        mainPanel.revalidate();
        game.Print_arr();
        if(game.isGameOver()) dialog_GameOver();
    }
    //根据jLabel里面的字符设置jLabel的背景颜色
    public void setColor(int i,int j,String str) {
        switch (str) {
            case "2":
                jLabels[i][j].setBackground(new Color(0xeee4da));
                break;
            case "4":
                jLabels[i][j].setBackground(new Color(0xede0c8));
                break;
            case "8":
                jLabels[i][j].setBackground(new Color(0xf2b179));
                break;
            case "16":
                jLabels[i][j].setBackground(new Color(0xf59563));
                break;
            case "32":
                jLabels[i][j].setBackground(new Color(0xf67c5f));
                break;
            case "64":
                jLabels[i][j].setBackground(new Color(0xf65e3b));
                break;
            case "128":
                jLabels[i][j].setBackground(new Color(0xedcf72));
                break;
            case "256":
                jLabels[i][j].setBackground(new Color(0xedcc61));
                break;
            case "512":
                jLabels[i][j].setBackground(new Color(0xedc850));
                break;
            case "1024":
                jLabels[i][j].setBackground(new Color(0xedc53f));
                break;
            case "2048":
                jLabels[i][j].setBackground(new Color(0xedc22e));
                break;
            default:
                jLabels[i][j].setBackground(new Color(0xffffffff));
                break;
        }
    }
}

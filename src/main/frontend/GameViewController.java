package main.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameViewController extends JFrame {
    private JPanel boardPanel; // 棋盘面板
    private JButton[][] tileButtons; // 棋盘上的按钮（代表岛屿板块）

    public GameViewController() {
        // 设置窗口标题
        setTitle("Forbidden Island");

        // 设置窗口大小
        setSize(800, 600);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置布局为 BorderLayout
        setLayout(new BorderLayout());

        // 创建标题
        JLabel titleLabel = new JLabel("Forbidden Island", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80)); // 设置字体颜色
        add(titleLabel, BorderLayout.NORTH);

        // 创建棋盘面板
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 6, 5, 5)); // 6x6 网格，间距为 5
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置边距
        add(boardPanel, BorderLayout.CENTER);

        // 初始化棋盘
        initializeBoard();

        // 创建操作按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 行 1 列，间距为 10
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置边距

        // 第一行按钮
        JPanel firstRow = new JPanel();
        firstRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        firstRow.add(createButton("Move To"));
        firstRow.add(createButton("Shore Up"));
        firstRow.add(createButton("Pass To"));
        firstRow.add(createButton("Capture"));
        firstRow.add(createButton("Lift Off"));
        buttonPanel.add(firstRow);

        // 第二行按钮
        JPanel secondRow = new JPanel();
        secondRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        secondRow.add(createButton("Special Actions"));
        secondRow.add(createButton("Next"));
        secondRow.add(createButton("Discard"));
        secondRow.add(createButton("Clear"));
        buttonPanel.add(secondRow);

        // 将按钮面板添加到窗口底部
        add(buttonPanel, BorderLayout.SOUTH);

        // 显示窗口
        setVisible(true);
    }

    // 初始化棋盘
    private void initializeBoard() {
        tileButtons = new JButton[6][6]; // 6x6 的棋盘
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                JButton tileButton = new JButton();
                tileButton.setBackground(Color.CYAN); // 初始颜色为青色（代表未被淹没）
                tileButton.setOpaque(true);
                tileButton.setBorderPainted(false);
                tileButton.setPreferredSize(new Dimension(80, 80)); // 设置按钮大小
                tileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 点击棋盘按钮时的逻辑
                        JButton clickedButton = (JButton) e.getSource();
                        clickedButton.setBackground(Color.BLUE); // 点击后变为蓝色（代表被淹没）
                    }
                });
                tileButtons[row][col] = tileButton;
                boardPanel.add(tileButton);
            }
        }
    }

    // 创建按钮的辅助方法
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(100, 30)); // 设置按钮大小
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(text + " button clicked");
            }
        });
        return button;
    }

    public static void main(String[] args) {
        // 在事件调度线程中运行 UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameViewController();
            }
        });
    }
}
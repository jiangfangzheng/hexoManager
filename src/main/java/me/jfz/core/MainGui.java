package me.jfz.core;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static me.jfz.core.GuiUtil.setFrameCenter;
import static me.jfz.core.GuiUtil.setSystemStyle;
import static me.jfz.util.DateUtil.getNowTimeMS;
import static me.jfz.util.FileUtil.save;

/**
 * @author Sandeepin
 * 2018/6/2 0002
 */


public class MainGui {

    private static String nowFilePath;
    private static String nowFileString;

    public static void gui(String[] fileNameList, Map<String, HexoMdFile> hexoMdFileMap) {
        setSystemStyle();
        JFrame jf = new JFrame("异想家Sandeepin的Hexo文章管理器 v1.0");
        jf.setSize(1200, 900);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFrameCenter(jf, true);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel panel01 = new JPanel(new FlowLayout());
        JPanel panel02 = new JPanel(new FlowLayout());
        panel.add(panel01, BorderLayout.NORTH);
        panel.add(panel02, BorderLayout.SOUTH);

        JButton btn01 = new JButton("选择_posts目录");
        JLabel label01 = new JLabel("_posts目录：");
        JButton btn02 = new JButton("保存当前文章");
        panel01.add(btn01);
        panel01.add(label01);
        panel01.add(btn02);


        JLabel label02 = new JLabel("状态：");
        panel02.add(label02);


        // 创建一个 JList 实例
        final JList<String> list = new JList<String>();

        // 设置一下首选大小
//        list.setPreferredSize(new Dimension(550, 800));

        // 允许可间断的多选
//        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 设置选项数据（内部将自动封装成 ListModel ）
//        list.setListData(new String[]{"香蕉", "雪梨", "苹果", "荔枝"});
        list.setListData(fileNameList);


        // 设置默认选中项
        list.setSelectedIndex(0);
        // 滚动条
        JScrollPane scrlpane = new JScrollPane(list);
        scrlpane.setPreferredSize(new Dimension(550, 850));

        // 添加到内容面板容器
//        panel.add(list);
        panel.add(scrlpane, BorderLayout.WEST);

        // 创建一个 5 行 10 列的文本区域
        JTextArea textArea = new JTextArea();
        // 设置自动换行
        textArea.setLineWrap(true);
        // textArea事件
        // 滚动条
        JScrollPane scrlpane2 = new JScrollPane(textArea);
//        scrlpane.setPreferredSize(new Dimension(550, 850));
        // 添加到内容面板
        panel.add(scrlpane2, BorderLayout.CENTER);

        jf.setContentPane(panel);
        jf.setVisible(true);


        // 添加选项选中状态被改变的监听器
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取所有被选中的选项索引
                int[] indices = list.getSelectedIndices();
                // 获取选项数据的 ListModel
                ListModel<String> listModel = list.getModel();
                // 输出选中的选项
                for (int index : indices) {
                    System.out.println("选中: " + index + " = " + listModel.getElementAt(index));
                    HexoMdFile hexoMdFile = hexoMdFileMap.get(listModel.getElementAt(index));
//                    System.out.println(hexoMdFile.getContent());
                    textArea.setText(hexoMdFile.getContent());
                    nowFilePath = hexoMdFile.getFilePath();
                    nowFileString = hexoMdFile.getContent();
                }
                System.out.println();
            }
        });

        btn02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                System.out.println("保存当前文章按钮 被点击");
                System.out.println(nowFilePath);
//                System.out.println(nowFileString);
                if (textArea.getText().equals(nowFileString)) {
                    System.out.println("文件未改动！");
                    label02.setText("状态：文件未改动！");
                } else {
                    nowFileString = textArea.getText();
                    boolean b = save(nowFilePath, nowFileString);
                    if (b) {
                        label02.setText("状态：文件保存成功！ 时间：" + getNowTimeMS());
                    } else {
                        label02.setText("状态：文件保存失败！" + getNowTimeMS());
                    }
                }
            }
        });
    }

}
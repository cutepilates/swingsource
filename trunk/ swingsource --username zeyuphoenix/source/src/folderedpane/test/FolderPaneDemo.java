/*
 * FolderPaneDemo.java
 *
 * Created on June 8, 2007, 8:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package folderedpane.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import folderedpane.FolderPane;

/**
 * �۵�������ʾ����
 * ��������foldered_pane.jar��Ϳ���˫���ۿ�Ч��
 * ���ڵײ��и�Enable Animation��JCheckBox��ѡ���ܳ��ֶ��������͵���Ч��
 *
 * @author William Chen
 * @mail rehte@hotmail.com
 */
public class FolderPaneDemo extends JFrame{
    public FolderPaneDemo(){
        setTitle("java_source");
        setIconImage(
                Toolkit.getDefaultToolkit()
                .getImage(FolderPaneDemo.
                class.getResource("/folderedpane/test/title.png")));
        
        initComponent();
        
        setSize(238,510);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initComponent(){
        FolderPane fp = getFolderPane();
        //��ӵ�JScrollPane
        JScrollPane jsp=new JScrollPane(fp);
        //Ϊ�˺ÿ���Viewport�������ó�FolderPane�ı�������������Լ�����
        jsp.getViewport().setBackground(fp.getBackground());
        add(jsp, BorderLayout.CENTER);
        
        JCheckBox box = getAnimationCheckBox(fp);
        add(box, BorderLayout.SOUTH);
    }

    private FolderPane getFolderPane() {
        FolderPane fp=new FolderPane();
        fp.setAnimated(true);
        fp.addFolder("�ļ����ļ�������", getFileFolderPane());
        fp.addFolder("����λ��", getOtherPlacePane());
        fp.addFolder("��ϸ��Ϣ", getDetailsPane());
        return fp;
    }

    private ListPane getDetailsPane() {
        ListPane p=new ListPane();
        p.addItem("<html><b>java_source</b><br>�ļ���</html>",null);
        p.addItem("<html>�޸�����: 2001��11��8��,<br>22:39</html>",null);
        p.setSize(185,74);
        return p;
    }

    private ListPane getOtherPlacePane() {
        ListPane p=new ListPane();
        p.addItem("System (C:)","drive.png");
        p.addItem("�ҵ��ĵ�","mydoc.png");
        p.addItem("�����ĵ�","shareddoc.png");
        p.addItem("�ҵĵ���","mycom.png");
        p.addItem("�����ھ�","neighbor.png");
        p.setSize(185,117);
        return p;
    }

    private ListPane getFileFolderPane() {
        ListPane p=new ListPane();
        p.addItem("����һ�����ļ���","/folderedpane/test/newfolder.png");
        p.addItem("������ļ��з�����Web","/folderedpane/test/internet.png");
        p.addItem("������ļ���","/folderedpane/test/share.png");
        p.setSize(185,86);
        return p;
    }

    private JCheckBox getAnimationCheckBox(final FolderPane fp) {
        final JCheckBox box=new JCheckBox("Enable Animation");
        box.setSelected(true);
        box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fp.setAnimated(box.isSelected());
            }
        });
        return box;
    }
    public static void main(String[]args){
        try {
            System.setProperty("swing.useSystemFontSettings", "false");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {}
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                new FolderPaneDemo().setVisible(true);
            }
        });
    }
}

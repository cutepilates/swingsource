package cellspan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import jtable.tableheader.MyGroupTableHeaderUI;
import jtable.tableheader.MyHeaderButtonRenderer;
import jtable.tableheader.MyTableHeader;

/**
 * �������Ժϲ���Ԫ���table
 * 
 * @author zhangshuai
 */
public class SpanCellTablePanel extends JPanel {

    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * table�����Խ��е�Ԫ��ϲ�
     */
    private CustomTabel table = null;
    /**
     * ����table��ͷ���ֺͿ��.
     */
    private String[] columnTitle = new String[] { "���", "����", "�û�", "������һ",
            "��������" };
    /**
     * ����table�ϲ����е�����
     */
    private int[][] spanArray = null;

    /**
     * ��Ϊ�漰���¼����ݵ�Ԫ��ϲ������⣬���Ը���table�����ݶ������´���
     */
    public SpanCellTablePanel(Vector<Vector<?>> datas) {
        init(datas);
    }

    /**
     * ��ʼ��
     */
    private void init(Vector<Vector<?>> datas) {
        CustomTableModel model = null;
        datas = convertSpanData(datas);
        if (datas != null) {
            model = new CustomTableModel(datas, convertToVector(columnTitle));
        } else {
            model = new CustomTableModel(columnTitle, 0);
        }

        ICellAttribute cellAtt = model.getCellAttribute();
        table = new CustomTabel(model) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            protected JTableHeader createDefaultTableHeader() {
                return new MyTableHeader(columnModel);
            }
        };
        table.setOpaque(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // ���ñ�ͷ�в�����ק����ʽ
        table.getTableHeader().setResizingAllowed(false);
        TableCellRenderer renderer2 = new MyHeaderButtonRenderer();
        TableColumnModel model2 = table.getColumnModel();
        for (int i = 0; i < model2.getColumnCount(); i++) {
            model2.getColumn(i).setHeaderRenderer(renderer2);
        }
        table.getTableHeader().setUI(new MyGroupTableHeaderUI());
        JScrollPane scroll = new JScrollPane(table);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        if (datas != null) {
            // �ϲ���Ԫ��
            spanArray = combineSpanData(datas);
            if (spanArray != null) {
                int[] columns = new int[] { 0, 1, 2 };
                for (int i = 0; i < columns.length; i++) {

                    for (int t = 0; t < spanArray.length; t++) {
                        ((ICellSpan) cellAtt).combine(spanArray[t],
                                new int[] { columns[i] });
                    }
                }
                table.clearSelection();
                table.revalidate();
                table.repaint();
            }
        }

        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        // ����������̬������ʾ���
        int rowcount = table.getRowCount();
        this.setPreferredSize(new Dimension(800, 70 + rowcount * 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
    }

    /**
     * ȡ�úϲ���Ԫ��ı�
     */
    public JTable getTable() {
        return table;
    }

    /**
     * ȡ�úϲ����е�����
     */
    public int[][] getSpanArray() {
        return spanArray;
    }

    /**
     * �����к�ȡ���е�����id�����������ҵ��id��
     */
    public String getSelectedCbsAppId(int row) {
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            if (table.getModel().getColumnName(i) != null
                    && table.getModel().getColumnName(i).trim()
                            .equalsIgnoreCase("obj")) {
                // ȡ��obj�е�ֵ
                Object value = table.getValueAt(table.getSelectedRow(), i);
                return (value == null) ? null : value.toString();
            }
        }
        return null;
    }

    /**
     * �ڴ���ͷ�
     */
    public void release() {
        spanArray = null;
        table = null;
        this.removeAll();
    }

    /**
     * tableͷת��Ϊ��Ҫ��vector����
     */
    private Vector<Object> convertToVector(Object[] anArray) {
        if (anArray == null) {
            return null;
        }
        Vector<Object> v = new Vector<Object>(anArray.length);
        for (int i = 0; i < anArray.length; i++) {
            v.addElement(anArray[i]);
        }
        return v;
    }

    /**
     * Ϊ������table������������
     */
    private Vector<Vector<?>> convertSpanData(Vector<Vector<?>> datas) {
        if (datas == null || datas.isEmpty()) {
            return null;
        }
        Vector<Object> rows = new Vector<Object>();
        Vector<Vector<?>> spanList = new Vector<Vector<?>>();
        Object tempInfo = datas.get(0).get(0);
        int order = 1;
        for (int i = 0; i < datas.size(); i++) {
            if (tempInfo.equals(datas.get(i).get(0))) {
            } else {
                tempInfo = datas.get(i).get(0);
                order++;
            }
            rows = new Vector<Object>();
            rows.addAll(datas.get(i));
            rows.insertElementAt(order, 0);
            spanList.add(rows);
        }
        return spanList;
    }

    /**
     * ���ݸ�����table����ȡ����Ҫ�ϲ��ĵ�Ԫ��
     */
    private int[][] combineSpanData(Vector<Vector<?>> datas) {
        if (datas == null || datas.isEmpty()) {
            return null;
        }
        List<Integer> rows = new ArrayList<Integer>();
        List<List<Integer>> spanList = new ArrayList<List<Integer>>();
        Object tempInfo = datas.get(0).get(0);
        for (int i = 0; i < datas.size(); i++) {
            if (tempInfo.equals(datas.get(i).get(0))) {
                rows.add(i);
            } else {
                spanList.add(rows);
                tempInfo = datas.get(i).get(0);
                rows = new ArrayList<Integer>();
                rows.add(i);
            }
            if (i == datas.size() - 1) {
                spanList.add(rows);
            }
        }

        int[][] spanArray = new int[spanList.size()][];
        for (int i = 0; i < spanList.size(); i++) {
            int[] array = new int[spanList.get(i).size()];
            for (int j = 0; j < spanList.get(i).size(); j++) {
                array[j] = spanList.get(i).get(j);
            }
            spanArray[i] = array;
        }

        return spanArray;
    }
}

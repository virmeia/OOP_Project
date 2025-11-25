package com.udj.gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CustomStyledTable extends JTable {

    public CustomStyledTable() {
        super();
        initTable();
    }
    
    public CustomStyledTable(DefaultTableModel model) {
        super(model);
        initTable();
    }
    
    private void initTable() {
        if (getModel().getColumnCount() == 0) {
            String[] columns = {"STUDENT ID", "NAME", "PROGRAM", "YEAR LEVEL", "STATUS", "ACTIONS"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 5; 
                }
            };
            setModel(model);
        }
        
        styleTable();
    }
    
    private void styleTable() {
        setRowHeight(40);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setSelectionBackground(new Color(0x8750A6)); 
        setSelectionForeground(new Color(0xFFFFFF)); 
        setGridColor(new Color(0xC8A8D8)); 
        setShowGrid(true);
        setIntercellSpacing(new Dimension(1, 1));
        setBackground(new Color(0xFFFFFF)); 
        setFillsViewportHeight(true);

        JTableHeader header = getTableHeader();
        header.setBackground(new Color(0x6B2C91));
        header.setForeground(new Color(0xFFFFFF)); 
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        if (getColumnCount() >= 6) {
            getColumnModel().getColumn(0).setPreferredWidth(100);
            getColumnModel().getColumn(1).setPreferredWidth(150);
            getColumnModel().getColumn(2).setPreferredWidth(150);
            getColumnModel().getColumn(3).setPreferredWidth(80);
            getColumnModel().getColumn(4).setPreferredWidth(80);
            getColumnModel().getColumn(5).setPreferredWidth(120);
        }
    }
    
    public void addRow(Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(rowData);
    }
    
    public void removeSelectedRow() {
        int selectedRow = getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) getModel();
            model.removeRow(selectedRow);
        }
    }
    
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);
    }
    
}
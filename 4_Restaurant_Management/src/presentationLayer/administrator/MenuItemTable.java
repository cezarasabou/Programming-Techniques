package presentationLayer.administrator;

import businessLayer.CompositeProduct;
import businessLayer.MenuItem;


import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class MenuItemTable extends AbstractTableModel {
    private String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
    private ArrayList<MenuItem> menuItems;

       public MenuItemTable(ArrayList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }

    @Override
    public int getRowCount() {
        if(menuItems.isEmpty()){
            return 0;
        }
        else{
            return menuItems.size();
        }

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MenuItem menuItem = menuItems.get(rowIndex);
        switch (columnIndex){
            case 0: return menuItem.getMenuItemName();
            case 1: return Integer.toString(menuItem.getMenuItemWeight());
            case 2: return  Double.toString(menuItem.computePrice());
            default: return menuItem.getMenuItemName();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class getColumnClass(int column){
        return String.class;
    }
}

package presentationLayer.administrator;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BaseProductTable extends AbstractTableModel {

    private String[] columnNames = {"Product Name", "Product Weight", "Product Price"};
    private ArrayList<BaseProduct> baseProducts;

    public BaseProductTable(ArrayList<BaseProduct> baseProducts){
        if(baseProducts == null){
            this.baseProducts = null;
        }
        else{
            this.baseProducts = baseProducts;
        }

    }

    @Override
    public int getRowCount() {
        if(baseProducts.isEmpty() || baseProducts == null){
            return 0;
        }
        else{
            return baseProducts.size();
        }

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MenuItem menuItem = baseProducts.get(rowIndex);
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

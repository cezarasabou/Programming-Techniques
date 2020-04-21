package presentationLayer.waiter;

import businessLayer.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrdersTable extends AbstractTableModel {

    private String[] columnNames = {"OrderID", "TableNumber", "OrderDate"};

    private ArrayList<Order> allOrders;
    public OrdersTable(ArrayList<Order> allOrders){
        this.allOrders = allOrders;
    }
    @Override
    public int getRowCount() {
       return allOrders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return allOrders.get(rowIndex).getOrderId();
            case 1: return allOrders.get(rowIndex).getTableId();
            case 2: return  allOrders.get(rowIndex).getOrderDate();
            default : return allOrders.get(rowIndex).getOrderId();
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

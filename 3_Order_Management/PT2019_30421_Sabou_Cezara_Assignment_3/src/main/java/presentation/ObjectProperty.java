//package presentation;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//public class ObjectProperty extends AbstractTableModel {
//
//    private List<Object> objects = new ArrayList<Object>();
//
//    public void addObject(Object o){
//        addObject(o);
//    }
//     public void addObjectAt(Object o, int index){
//        objects.add(index,o);
//     }
//
//     public void removeObject(Object o){
//        int index = objects.indexOf(o);
//        objects.remove(index);
//        fireTableRowsDeleted(index,index);
//     }
//
//     public Object getObject(int rowIndex){
//        return objects.get(rowIndex);
//     }
//
//    public int getRowCount() {
//        return objects.size();
//    }
//
//    public int getColumnCount() {
//        return 4;
//    }
//    public String getColumnName(int columnIndex){
//        switch (columnIndex){
//
//        }
//    }
//
//}

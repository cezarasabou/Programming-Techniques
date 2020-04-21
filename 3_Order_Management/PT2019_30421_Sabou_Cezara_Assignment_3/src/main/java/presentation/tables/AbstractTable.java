package presentation.tables;

import javax.swing.table.AbstractTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * this class is the one used to obtain the table format, given the list of objects
 * it takes the fields through reflection and it generates a table with specific number pf rows and columns
 * @param <T>
 */
public class AbstractTable<T> extends AbstractTableModel {

    private List<String> columns;
    private List<T> objectList;
    private Class<T> type;

    public AbstractTable(List<T> objects, List<String> columns, Class<T> myClass){
        this.objectList = objects;
        this.columns = columns;
        this.type = myClass;
    }

    @Override
    public int getRowCount() {
        return objectList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    /**
     * method that returns the value of an object depending on the row and the column
     * @param rowIndex specifies the object
     * @param columnIndex specifies the field
     * @return value of that specific field
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        PropertyDescriptor propertyDescriptor = null;
        Field []fields = objectList.get(rowIndex).getClass().getDeclaredFields();
        Object wantedField = fields[columnIndex];
        try{
            propertyDescriptor = new PropertyDescriptor(fields[columnIndex].getName(),type);
            Method method = propertyDescriptor.getReadMethod();
            Object fieldValue = method.invoke(objectList.get(rowIndex)).toString();
            return fieldValue;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }
}

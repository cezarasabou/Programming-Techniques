package reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionProperties {
    /**
     * this method takes an Object and by getting its class it can obtain the fields
     * @param generalObject
     * @return a List of field names
     */
    public static List<String> getObjectFields(Object generalObject){
        List<String> fields = new ArrayList<String>();
        for(Field field : generalObject.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{

                fields.add(field.getName());
            }
            catch(IllegalArgumentException e){
                e.printStackTrace();
            }

        }
        return fields;
    }
}

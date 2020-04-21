package businesslogic.validators;

import model.ProductOrder;

import java.util.Date;

public class OrderDateValidator implements Validator<ProductOrder>{
    public boolean validate(ProductOrder o){
        String str[] = o.getOrderDate().split("/");
        int day = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);
        Date d = new java.util.Date();

        if(day<d.getDay() && month < d.getMonth() && year < d.getYear() ){
            throw new IllegalArgumentException(" order date not valid, should not be lower than ");
        }

        return true;
    }
}

package businesslogic.validators;

import model.Client;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientBirthdateValidator implements Validator<Client> {
    public boolean validate(Client c){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            Date parsedDate = sdf.parse(c.getClientBirthDate());

            if(parsedDate.compareTo(new Date()) > 0){
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

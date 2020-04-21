package businesslogic;

import businesslogic.validators.ClientBirthdateValidator;
import businesslogic.validators.Validator;
import dataaccess.ClientRepository;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class that implements the validators and links the dataaccess layer to the presentation
 */
public class ClientBLL {
    List<Validator<Client>> validators;
    private ClientRepository clientRepository;
    public ClientBLL(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientBirthdateValidator());

        clientRepository = new ClientRepository();
    }
    public Client findClientById(int id){
        Client c = (Client) clientRepository.findById(id);
        if(c == null){
            throw new NoSuchElementException();
        }
        return c;
    }
}

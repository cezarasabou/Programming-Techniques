package presentation.tables;

import model.Client;

import java.util.List;

public class ClientTable extends AbstractTable {

    public ClientTable(List<Client> clientList, List<String>  tableFieldNames) {
       super(clientList,tableFieldNames, Client.class);
    }

}

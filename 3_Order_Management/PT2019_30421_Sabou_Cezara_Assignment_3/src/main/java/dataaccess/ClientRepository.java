package dataaccess;

import model.Client;
import sun.java2d.loops.FillRect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends GeneralRepository{

    private Connection connection;
    private List<Client> clientList = new ArrayList<Client>();
    private GeneralRepository<Client> clients ;

    public ClientRepository() {
        super(Client.class);
    }

    public List<Client> getClients(){
      clientList = clients.findAll();
      return clientList;
    }

}

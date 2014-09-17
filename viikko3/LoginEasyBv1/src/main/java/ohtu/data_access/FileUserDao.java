/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author narchie
 */
public class FileUserDao implements UserDao {
    
    private File db;
    BufferedWriter writer;
    BufferedReader reader;
   
    public FileUserDao() throws FileNotFoundException, IOException {
        db = new File("salasanat.txt");
        writer = new BufferedWriter(new FileWriter(db));
        reader = new BufferedReader(db);
        add(new User("pekka", "akkep"));
    }        


    @Override
    public void add(User user) {
        try {
            writer.append(user.getUsername() + ":" + user.getPassword());
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User findByName(String name) {
        
        try {
            String line = reader.readLine();
            while (line != null) {
            if (line.contains("name")) {
                return new User(line.split(":")[0],line.split(":")[1]);
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

    @Override
    public List<User> listAll() {
        return (List)reader.lines();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.data_access;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FileUserDAO implements UserDao {
    
    private List<User> users;
    private String fileName;
    
    public FileUserDAO(String fileName){

        users = new ArrayList<User>();
        try {
            this.fileName = fileName;
            Scanner scr = new Scanner(new File(fileName));
            while(scr.hasNext()){
                
                String str = scr.nextLine();

                String[] split = str.split("\\|");

                if(split.length == 2){
                    User u = new User(split[0],split[1].trim());
                    this.users.add(u);
                }
                
            }
        } catch (FileNotFoundException ex) {

        }
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        try {
            this.dumpUsersToFile();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }
    
    private void dumpUsersToFile() throws UnsupportedEncodingException, FileNotFoundException, IOException{
        Writer w = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(this.fileName)));
        for(User u : users){
            w.write(u.getUsername()+"|"+u.getPassword()+"\n");
        }
        w.close();
    }
    
    
}

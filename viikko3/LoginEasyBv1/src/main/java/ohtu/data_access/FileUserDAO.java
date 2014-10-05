
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;

/**
 *
 * @author Toni Oksanen
 */

public class FileUserDAO implements UserDao {
  
  private final File file;
  private List<User> cache = null;
  
  public FileUserDAO( String path ) {
    this.file = new File( path );
  }
  
  @Override
  public List<User> listAll() {
    
    if ( this.cache != null ) {
      return cache;
    }
    
    Scanner scanner = null;
    List<User> users = new ArrayList();
    
    try {
      
      scanner = new Scanner( this.file );
      
      while ( scanner.hasNextLine() ) {
        
        String[] credentials = scanner.nextLine().split( ":" );
        
        User user = new User( credentials[ 0 ], credentials[ 1 ] );
        users.add( user );
      
      }
    
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "Tapahtui virhe: " + e.getMessage() );
    }
    finally {
      if ( scanner != null ) {
        scanner.close(); 
      }
    }
    
    this.cache = users;
    
    return users;
    
  }

  @Override
  public User findByName( String name ) {
    for ( User user : this.listAll() ) {
      if ( user.getUsername().equals( name ) ) {
        return user;
      }
    }
    return null;
  }

  @Override
  public void add( User user ) {
    try {
      this.cache.add( user );
      FileWriter fw = new FileWriter( this.file, true );
      fw.write( user.getUsername() + ":" + user.getPassword() + "\n" );
      fw.close();
    }
    catch ( IOException e ) {
      System.out.println( "Tapahtui virhe: " + e.getMessage() );
    }
  }
  
}

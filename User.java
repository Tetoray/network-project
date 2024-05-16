
package network_project;

import java.util.ArrayList;

/**
 * tarik.alrayan
 * 2121221366
 */

/*
User class used to handel user informations 
 */

public class User {
    
    String name;
    String password;
    ArrayList<String> keys;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.keys = new ArrayList<>();
    }

   

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
    
    
    
 public boolean passwordCheck(String password, String cPassword) {
    if (password.length() != cPassword.length()) {
        return false; // If lengths don't match, passwords are definitely different
    }

    for (int i = 0; i < password.length(); i++) {
        if (password.charAt(i) != cPassword.charAt(i)) {
            return false; // If any character mismatches, return false
        }
    }

    // If we reach here, all characters matched
    return true;
}

    
    
    
    
    
    
    
    
    
    
    
}


package network_project;

/**
 * tarik.alrayan
 * 2121221366
 */

import java.sql.SQLException;
import java.util.Random;

/*
Project class used to handel projects informations 
 */
public class Project {

    private String name;
    private User admin;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Project(String name, User admin) {
        this.name = name;
        this.admin = admin;

    }

    //generate random project key 
    private String randomKey() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            keyBuilder.append(random.nextInt(10));
        }

        return keyBuilder.toString();
    }

    // this methode get random project key and send it to database to check if it unique then use it 
    public String generateKey() throws SQLException {
        String key;
        do {
            key = randomKey();
        } while (!Database.isUnique("project", "keyword", key));

        return key;
    }
}

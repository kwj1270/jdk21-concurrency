package com.lab.sc.scope.user;

/*
 * Ponder this question
 *  - Do we need to make this class Thread safe ? 
 */
public class User {
    
    private String id;
    
    public User(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", super.toString(), this.id);
    }
}

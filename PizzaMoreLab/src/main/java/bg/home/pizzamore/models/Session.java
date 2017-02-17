/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
/**
 *
 * @author kalin
 */
@Entity
@Table(name = "pizza_sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "pizzaSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SessionData> sessionData;

    public Session() {
        this.setSessionData(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<SessionData> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Set<SessionData> sessionData) {
        this.sessionData = sessionData;
    }

    public void addSessionData(String key, String value) {
        SessionData sessionData = new SessionData(key, value, this);
        this.getSessionData().add(sessionData);
    }
}


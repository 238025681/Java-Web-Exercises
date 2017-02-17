/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kalin
 */
@Entity
@Table(name = "pizza_session_data")
public class SessionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "key_data")
    private String key;

    @Column(name = "value_data")
    private String value;

    @ManyToOne
    @JoinColumn(name = "pizza_session_id", nullable = false)
    private Session pizzaSession;

    public SessionData() {
    }

    public SessionData(String key, String value, Session session) {
        this.key = key;
        this.value = value;
        this.pizzaSession = session;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Session getPizzaSession() {
        return pizzaSession;
    }

    public void setPizzaSession(Session session) {
        this.pizzaSession = session;
    }
}

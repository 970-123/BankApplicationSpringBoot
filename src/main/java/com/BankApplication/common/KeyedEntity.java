package com.BankApplication.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@EqualsAndHashCode
@ToString
public class KeyedEntity<T extends Serializable> {
    // JO bhi data network call pe jana hota hai usko serialize karte hai matlab bit stream mei convert karte hai
    // Agar aisa nahi karenge to vo cache nahi hoga, cache kyu? Har baar thodi DB read write karti rahegi...
    // Assume ki 10K users per minute ka traffic aara hai.. Itne read or wrte operations mei yteri money khatam ho jayegi
    // isko kse use krte h, Tune redis setup nahi kiya hai... Redis use karegi agar caching ke liye to agar tune serializable is extend ni kiya to vo cache hi nhi hoga

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;

    public KeyedEntity() {
    }

    public KeyedEntity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}

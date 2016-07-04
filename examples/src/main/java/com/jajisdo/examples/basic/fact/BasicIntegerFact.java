package com.jajisdo.examples.basic.fact;

import com.jajisdo.presistence_service.persistable.PersistableFact;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "basic_integer_fact")
public class BasicIntegerFact extends PersistableFact {

    private int integerValue;

    protected BasicIntegerFact() {
    }

    public BasicIntegerFact(int content) {
        this.integerValue = content;
    }

    @Basic
    @Column(name = "integer_value")
    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicIntegerFact)) return false;

        BasicIntegerFact that = (BasicIntegerFact) o;

        return integerValue == that.integerValue;

    }

    @Override
    public int hashCode() {
        return integerValue;
    }

    @Override
    public String toString() {
        return "BasicIntegerFact{" +
                "integerValue=" + integerValue +
                '}';
    }
}

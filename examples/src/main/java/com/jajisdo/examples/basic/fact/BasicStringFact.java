package com.jajisdo.examples.basic.fact;

import com.jajisdo.presistence_service.persistable.PersistableFact;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "basic_string_fact")
public class BasicStringFact extends PersistableFact {

    private String content;

    protected BasicStringFact() {
    }

    public BasicStringFact(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicStringFact that = (BasicStringFact) o;

        return content != null ? content.equals(that.content) : that.content == null;

    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BasicStringFact{" +
                "content='" + content + '\'' +
                '}';
    }

}

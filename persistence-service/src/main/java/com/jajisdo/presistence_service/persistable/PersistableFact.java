package com.jajisdo.presistence_service.persistable;

import de.dailab.jiactng.agentcore.knowledge.IFact;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by domann on 01.07.2016.
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public class PersistableFact implements Serializable, IFact, Persistable<Long> {

    private final Long id;

    public PersistableFact() {
        this.id = 0L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return id == 0L;
    }

}

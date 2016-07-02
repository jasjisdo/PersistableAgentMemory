package com.jajisdo.presistence_service.persistable;

import de.dailab.jiactng.agentcore.knowledge.IFact;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class PersistableFact implements IFact, Persistable<Long> {

    private final Long id;

    public PersistableFact() {
        this.id = 0L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public boolean isNew() {
        return id == 0L;
    }

}

package com.jajisdo.examples;

import com.jajisdo.examples.basic.fact.BasicIntegerFact;
import com.jajisdo.examples.basic.fact.BasicStringFact;
import com.jajisdo.presistence_service.fact.manager.PersistableFactManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by domann on 04.07.16.
 */
public class PersistableFactManagerTest {

    private PersistableFactManager manager;
    private BasicIntegerFact basicIntegerFact1;
    private BasicStringFact basicStringFact1;
    private BasicStringFact basicStringFact2;
    private BasicStringFact basicStringFact3;
    private BasicStringFact basicStringFact4;

    @Before
    public void setUp() throws Exception {
        manager = new PersistableFactManager("test-inmem-unit");

        basicIntegerFact1 = new BasicIntegerFact(1);

        basicStringFact1 = new BasicStringFact("Hello");
        basicStringFact2 = new BasicStringFact("Bjavt");
        basicStringFact3 = new BasicStringFact("NuqneH!");
        basicStringFact4 = new BasicStringFact("Dif-tor heh smusma!");
    }

    @Test
    public void name0() throws Exception {
        assertEquals("test-inmem-unit", manager.getPersistenceUnitName());
    }

    @Test
    public void name1() throws Exception {
        String jdbcInfo = manager.getJdbcInfo();
        assertNotNull(jdbcInfo);
    }

    @Test
    public void name2() throws Exception {
        assertTrue(basicStringFact1.isNew());
    }

    @Test
    public void name3() throws Exception {
        manager.add(basicStringFact1);
        assertFalse(basicStringFact1.isNew());
    }

    @Test
    public void name4() throws Exception {
        manager.add(basicStringFact1);
        assertEquals(1, manager.count(BasicStringFact.class));
    }

    @Test
    public void name5() throws Exception {
        manager.add(basicStringFact1);
        manager.add(basicStringFact2);
        manager.add(basicStringFact3);
        manager.add(basicStringFact4);
        assertEquals(4, manager.count(BasicStringFact.class));
    }

    @Test
    public void name6() throws Exception {
        manager.add(basicStringFact1);
        manager.add(basicStringFact2);
        manager.add(basicStringFact3);
        manager.add(basicStringFact4);
        manager.add(basicIntegerFact1);
        assertEquals(4, manager.count(BasicStringFact.class));
    }

    @Test
    public void name7() throws Exception {
        manager.add(basicStringFact1);
        manager.add(basicStringFact2);
        manager.add(basicStringFact3);
        manager.add(basicStringFact4);
        manager.add(basicIntegerFact1);
        assertEquals(1, manager.count(BasicIntegerFact.class));
    }

    @Test
    public void name8() throws Exception {
        manager.add(basicIntegerFact1);
        assertEquals(basicIntegerFact1, manager.getFactById(BasicIntegerFact.class, 1));
    }

    @Test
    public void name9() throws Exception {
        manager.add(basicStringFact1);
        manager.add(basicStringFact2);
        manager.add(basicStringFact3);
        manager.add(basicStringFact4);
        assertEquals(basicStringFact4, manager.getFactById(BasicStringFact.class, 4));
    }
}




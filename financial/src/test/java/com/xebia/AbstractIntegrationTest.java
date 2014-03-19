package com.xebia;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.xebia.module.PersistenceModule;
import org.junit.Before;

public abstract class AbstractIntegrationTest {

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new PersistenceModule());

        injector.injectMembers(this);

        initDatabase();
    }

    private void initDatabase() {

    }

}

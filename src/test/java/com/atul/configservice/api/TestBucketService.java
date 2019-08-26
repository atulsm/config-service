package com.atul.configservice.api;

import com.atul.configservice.ConfigServiceApplication;
import com.atul.configservice.ConfigServiceConfiguration;
import com.atul.configservice.core.Bucket;
import com.atul.configservice.db.BucketDao;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DAOTestRule;
import io.dropwizard.testing.junit.DropwizardAppRule;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;

@Ignore
//TODO Fix the issue with testing
public class TestBucketService extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final Configuration config = new Configuration();
        config.setProperty(AvailableSettings.URL, "jdbc:mysql://localhost:3306/configservice");
        config.setProperty(AvailableSettings.USER, "root");
        config.setProperty(AvailableSettings.PASS, "novell@123");
        config.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
        config.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        // Use the same configuration as in the Hibernate bundle to reduce differences between
        // testing and production environments.
        config.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "managed");
        config.setProperty(AvailableSettings.USE_GET_GENERATED_KEYS, "true");
        config.setProperty(AvailableSettings.GENERATE_STATISTICS, "true");
        config.setProperty(AvailableSettings.USE_REFLECTION_OPTIMIZER, "true");
        config.setProperty(AvailableSettings.ORDER_UPDATES, "true");
        config.setProperty(AvailableSettings.ORDER_INSERTS, "true");
        config.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true");
        config.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL57Dialect");

        SessionFactory sessionFactory = config.buildSessionFactory();
        final BucketDao dao = new BucketDao(sessionFactory);
        BucketService.INSTANCE.init(dao);

        TestBucketService service = new UnitOfWorkAwareProxyFactory("test",sessionFactory).create(TestBucketService.class);
        service.bucketGetLoad();
    }

    public void testBucketCreate(){
        BucketService.INSTANCE.createBucket("es-ip", "192.168.1.1");
        Bucket bucket = BucketService.INSTANCE.getBucket("es-ip");
        assertEquals("192.168.1.1",bucket.getValue());
    }

    public void testBucketGetLoad(){
        System.out.println("Dummy");
    }

    @UnitOfWork
    public void bucketGetLoad() throws Exception {
        int attempts = 10000;
        long start = System.currentTimeMillis();

        for(int i=0;i<attempts;i++) {
            Bucket bucket = BucketService.INSTANCE.getBucket("es-ip");
        }

        long end = System.currentTimeMillis();

        System.out.println(String.format("Took %d ms to process %d calls at QPS: %d",end-start,attempts, (attempts*1000)/(end-start)));
    }
}

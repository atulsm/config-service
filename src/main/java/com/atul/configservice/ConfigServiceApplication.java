package com.atul.configservice;

import com.atul.configservice.api.BucketService;
import com.atul.configservice.core.Bucket;
import com.atul.configservice.api.BucketServiceHealthCheck;
import com.atul.configservice.db.BucketDao;
import com.atul.configservice.resources.BucketResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConfigServiceApplication extends Application<ConfigServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ConfigServiceApplication().run(args);
    }

    private final HibernateBundle<ConfigServiceConfiguration> hibernate = new HibernateBundle<ConfigServiceConfiguration>(Bucket.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ConfigServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "ConfigService";
    }

    @Override
    public void initialize(final Bootstrap<ConfigServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final ConfigServiceConfiguration configuration,
                    final Environment environment) {
        environment.healthChecks().register("BucketCount", new BucketServiceHealthCheck());

        final BucketDao dao = new BucketDao(hibernate.getSessionFactory());
        BucketService.INSTANCE.init(dao);
        environment.jersey().register(new BucketResource());

    }

}

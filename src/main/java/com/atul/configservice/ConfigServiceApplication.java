package com.atul.configservice;

import com.atul.configservice.resources.BucketResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConfigServiceApplication extends Application<ConfigServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ConfigServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ConfigService";
    }

    @Override
    public void initialize(final Bootstrap<ConfigServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ConfigServiceConfiguration configuration,
                    final Environment environment) {
        System.out.println(configuration.getDefaultName());

        environment.jersey().register(BucketResource.class);
    }

}

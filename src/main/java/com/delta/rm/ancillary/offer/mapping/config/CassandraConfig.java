package com.delta.rm.ancillary.offer.mapping.config;

import static com.datastax.driver.mapping.NamingConventions.LOWER_CAMEL_CASE;
import static com.datastax.driver.mapping.NamingConventions.LOWER_SNAKE_CASE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.DefaultNamingStrategy;
import com.datastax.driver.mapping.DefaultPropertyMapper;
import com.datastax.driver.mapping.MappingConfiguration;
import com.datastax.driver.mapping.MappingManager;

/**
 * Class CassandraConfig
 * setting all Cassandra configuration
 */
@Configuration
public class CassandraConfig {

    @Value("${cassandra.contactpoints}")
    private String contactPoints;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.keyspace}")
    private String keySpace;

    @Value("${cassandra.clustername}")
    private String clusterName;

    @Bean(name = "cluster")
    public Cluster cluster() {

        return Cluster.builder()
                      .addContactPoint(contactPoints)
                      .withPort(port)
                      .withClusterName(clusterName)
                      .withoutMetrics()
                      .build();
    }

    @Bean(name = "session")
    @DependsOn("cluster")
    public Session session(Cluster cluster) {

        return cluster.connect(keySpace);
    }

    @Bean(name = "mappingManager")
    @DependsOn("session")
    public MappingManager mappingManager(Session session) {

        DefaultNamingStrategy namingStrategy = new DefaultNamingStrategy(LOWER_CAMEL_CASE, LOWER_SNAKE_CASE);

        final DefaultPropertyMapper propertyMapper = new DefaultPropertyMapper().setNamingStrategy(namingStrategy);

        final MappingConfiguration configuration = MappingConfiguration.builder()
                                                                       .withPropertyMapper(propertyMapper)
                                                                       .build();
        return new MappingManager(session, configuration);
    }
}

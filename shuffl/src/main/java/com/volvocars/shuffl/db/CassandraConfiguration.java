/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl.db;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ConditionalOnProperty(name = "db.provider", havingValue = "cassandra")
@EnableCassandraRepositories(basePackageClasses = {LocationRepository.class})
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    @Value("${db.cassandra.contactPoints:localhost}")
    String contactPoints;

    @Value("${db.cassandra.keyspace:hackaton}")
    String keyspace;

    @Value("${db.cassandra.username}")
    String username;

    @Value("${db.cassandra.password}")
    String password;

    @Value("${db.cassandra.port:9042}")
    int port;

    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    public String getKeyspaceName() {
        return keyspace;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {"com.volvocars.shuffl.db"};
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = super.cluster();
        cluster.setJmxReportingEnabled(false);
        cluster.setUsername(username);
        cluster.setPassword(password);
        cluster.setQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM));
        return cluster;
    }

    @Bean
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = super.session();
        session.setKeyspaceName(getKeyspaceName());
        return session;
    }

    @Override
    protected List<String> getStartupScripts() {

        final String createTable = "CREATE TABLE IF NOT EXISTS " + keyspace + ".location (" + "id text, coordinates list<text>, "
                + "isOccupied boolean, " + "PRIMARY KEY(id))";

        List<String> strList = new LinkedList<>();
        strList.add(createTable);
        return strList;
    }
}

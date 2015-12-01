package com.dastko.devbookmarks.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.elasticsearch.common.settings.Settings;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


/**
 * Created by dastko on 11/16/15.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "org/springframework/data/elasticsearch/repositories")
public class ElasticsearchConfiguration
{
    @Bean(name="elasticsearchTemplate")
    public static ElasticsearchOperations elasticsearchTemplate()
    {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
        TransportClient transportClient = new TransportClient(settings);
        Client client = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        return new ElasticsearchTemplate(client);
    }
}

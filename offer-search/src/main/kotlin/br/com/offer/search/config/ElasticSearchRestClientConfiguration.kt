package br.com.offer.search.config

import br.com.offer.search.config.properties.AWSElasticSearchProperties
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories(basePackages = ["br.com.offer.search.repositories"])
class ElasticSearchRestClientConfiguration(
    private val esProperties: AWSElasticSearchProperties
) : AbstractElasticsearchConfiguration() {

    override fun elasticsearchClient(): RestHighLevelClient {
        val config = ClientConfiguration.builder()
            .connectedTo(esProperties.endpoint)

        if(!esProperties.dev) {
            config.usingSsl()
            config.withBasicAuth(esProperties.username, esProperties.password)
        }

        return RestClients.create(config.build()).rest()
    }
}
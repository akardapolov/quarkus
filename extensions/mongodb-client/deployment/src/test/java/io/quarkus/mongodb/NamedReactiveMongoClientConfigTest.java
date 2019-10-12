package io.quarkus.mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.mongodb.runtime.MongoClientConnection;
import io.quarkus.test.QuarkusUnitTest;

public class NamedReactiveMongoClientConfigTest extends MongoWithReplicasTestBase {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .withConfigurationResource("application-named-mongoclient.properties");

    @Inject
    @MongoClientConnection("cluster1")
    ReactiveMongoClient client;

    @Inject
    @MongoClientConnection("cluster2")
    ReactiveMongoClient client2;

    @AfterEach
    void cleanup() {
        if (client != null) {
            client.close();
        }
        if (client2 != null) {
            client2.close();
        }
    }

    @Test
    public void testNamedDataSourceInjection() {
        assertThat(client.listDatabases().findFirst().run().toCompletableFuture().join()).isNotEmpty();
        assertThat(client2.listDatabases().findFirst().run().toCompletableFuture().join()).isNotEmpty();
    }
}

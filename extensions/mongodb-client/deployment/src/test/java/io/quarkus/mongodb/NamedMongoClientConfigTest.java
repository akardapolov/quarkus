package io.quarkus.mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.mongodb.client.MongoClient;

import io.quarkus.mongodb.runtime.MongoClientConnection;
import io.quarkus.test.QuarkusUnitTest;

public class NamedMongoClientConfigTest extends MongoWithReplicasTestBase {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .withConfigurationResource("application-named-mongoclient.properties");

    @Inject
    @MongoClientConnection("cluster1")
    MongoClient client;

    @Inject
    @MongoClientConnection("cluster2")
    MongoClient client2;

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
        assertThat(client.listDatabases().first()).isNotEmpty();
        assertThat(client2.listDatabases().first()).isNotEmpty();
    }
}

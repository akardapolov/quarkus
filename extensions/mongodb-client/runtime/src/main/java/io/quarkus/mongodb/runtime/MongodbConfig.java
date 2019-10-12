package io.quarkus.mongodb.runtime;

import java.util.Map;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "mongodb", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class MongodbConfig {
    /**
     * The default mongo client connection.
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public MongoClientConfig defaultMongoClientConfig;

    /**
     * Configures additional mongo client connections.
     * <p>
     * each cluster have a unique identifier witch must be identified to select the right connection.
     * example:
     * <p>
     * 
     * <pre>
     * quarkus.mongodb.cluster1.connection-string = mongodb://mongo1:27017
     * quarkus.mongodb.cluster2.connection-string = mongodb://mongo2:27017,mongodb://mongo3:27017
     * </pre>
     * <p>
     * And then use annotations above the instances of MongoClient to indicate which instance we are going to use
     * <p>
     * 
     * <pre>
     * {@code
     * &#64;Connection("cluster1")
     * &#64;Inject
     * ReactiveMongoClient mongoClientCluster1
     * }
     * </pre>
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public Map<String, MongoClientConfig> mongoClientConfigs;
}
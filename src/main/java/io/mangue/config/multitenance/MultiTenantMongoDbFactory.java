package io.mangue.config.multitenance;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import io.mangue.config.Logger;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

    private String defaultDatabaseName;

    public MultiTenantMongoDbFactory(MongoClient mongo, String database) {
        super(mongo, database);
        database = defaultDatabaseName;
        Logger.debug("Instantiating " + MultiTenantMongoDbFactory.class.getName() + " with default database name: " + defaultDatabaseName);
    }

    @Override
    public DB getDb() {
        final String subdomain = TenantContextHolder.getTenantSubdomain();
        final String uniqueSimpleHash = TenantContextHolder.getTenantUniqueSimpleHash();
        final String dbToUse;
        if(subdomain == null || uniqueSimpleHash == null)
            dbToUse = defaultDatabaseName;
        else
            dbToUse = subdomain + "-" + uniqueSimpleHash;

        Logger.debug("Acquiring database: " + dbToUse);
        return super.getDb(dbToUse);
    }
}
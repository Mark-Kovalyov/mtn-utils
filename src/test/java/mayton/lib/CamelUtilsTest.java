package mayton.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CamelUtilsTest {

    @Test
    void test() {
        assertEquals("GPT_ORG_ID", CamelUtils.commandLineToDash("--gpt-org-id"));
        assertEquals("gptOrgId",   CamelUtils.dashToCamel("GPT_ORG_ID"));
        assertEquals("GPT_ORG_ID", CamelUtils.camelToDash("gptOrgId"));

        assertEquals("MAYTON_NETWORK_DNS_ROCKS_DB_DNS_CLIENT_DB_PATH", CamelUtils.camelToDash("mayton.network.dns.RocksDbDnsClient.dbPath"));
        assertEquals("IPV4_LOCATION_SERVICE_RADIX_PATH",               CamelUtils.camelToDash("ipv4LocationServiceRadixPath"));
        assertEquals("MAYTON_NETWORK_BLACKLIST_EMULE_GUARDING_SERVICE_RADIX_PATH",
                CamelUtils.camelToDash("mayton.network.blacklist.EmuleGuardingServiceRadix.path"));


    }
}

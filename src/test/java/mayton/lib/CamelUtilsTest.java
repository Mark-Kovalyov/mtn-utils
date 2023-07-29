package mayton.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CamelUtilsTest {

    @Test
    void test() {
        assertEquals("GPT_ORG_ID", CamelUtils.commandLineToDash("--gpt-org-id"));
        assertEquals("gptOrgId",   CamelUtils.dashToCamel("GPT_ORG_ID"));
        assertEquals("GPT_ORG_ID", CamelUtils.camelToDash("gptOrgId"));
        // TODO: FIx
        // assertEquals("GPT_ORG_ID", CamelUtils.camelToDash("gptOrgID"));
    }
}

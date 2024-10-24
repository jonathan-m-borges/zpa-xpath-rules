package com.jonathanmborges.plsql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XPathCustomRulesPluginTest {

    @Test
    public void testRepository() {
        XPathCustomRulesDefinition plugin = new XPathCustomRulesDefinition();
        assertEquals("Jonathan M Borges", plugin.repositoryName());
        assertEquals("zpa-xpath-rules", plugin.repositoryKey());
        assertEquals(1, plugin.checkClasses().length);
    }

}

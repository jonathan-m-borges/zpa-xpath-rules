package com.jonathanmborges.plsql;

import org.junit.jupiter.api.Test;
import org.sonar.plsqlopen.checks.verifier.PlSqlCheckVerifier;

public class XPathCheckTest {

    @Test
    public void testSelect() {
        PlSqlCheckVerifier.verify("src/test/resources/select.sql", new XPathCheck());
    }

    @Test
    public void testCreateTable() {
        PlSqlCheckVerifier.verify("src/test/resources/createTable.sql", new XPathCheck());
    }
    
}

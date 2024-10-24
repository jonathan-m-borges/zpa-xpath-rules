package com.jonathanmborges.plsql;

import org.sonar.plugins.plsqlopen.api.CustomPlSqlRulesDefinition;

public class XPathCustomRulesDefinition extends CustomPlSqlRulesDefinition {

    @Override
    public String repositoryName() {
        return "Jonathan M Borges";
    }

    @Override
    public String repositoryKey() {
        return "zpa-xpath-rules";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Class[] checkClasses() {
        return new Class[] {
                XPathCheck.class
        };
    }

}

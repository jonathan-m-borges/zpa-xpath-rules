package com.jonathanmborges.plsql;

import java.io.File;
import java.util.List;

import org.sonar.check.RuleProperty;
import org.sonar.plugins.plsqlopen.api.annotations.ActivatedByDefault;
import org.sonar.plugins.plsqlopen.api.annotations.Priority;
import org.sonar.plugins.plsqlopen.api.annotations.Rule;
import org.sonar.plugins.plsqlopen.api.checks.PlSqlCheck;
import org.sonar.plugins.plsqlopen.api.sslr.AstNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipebz.flr.xpath.api.AstNodeXPathQuery;

@Rule(name = "XPath Rules", description = "XPath Custom Rules", key = "XPathRules", priority = Priority.MAJOR)
@ActivatedByDefault
public class XPathCheck extends PlSqlCheck {

    @RuleProperty(key = "configXPaths")
    private String configFileXPaths = "zpa-config-xpath.json";
    private List<ConfigRule> rules = null;

    @Override
    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            rules = objectMapper.readValue(new File(configFileXPaths), new TypeReference<List<ConfigRule>>() {
            });
        } catch (Exception e) {
            addFileIssue("Invalid config file to xpath: " + configFileXPaths);
        }
    }

    @Override
    public void visitFile(AstNode node) {
        if (rules == null || rules.isEmpty())
            return;

        rules.forEach(rule -> {
            List<Object> query = AstNodeXPathQuery.create(rule.getXpath()).selectNodes(node);

            if (query != null && query.size() > 0) {
                query.forEach(obj -> {
                    if (obj instanceof AstNode) {
                        addIssue((AstNode) obj, rule.getMessage());
                    } else if (obj instanceof Boolean && (Boolean) obj) {
                        addFileIssue(rule.getMessage());
                    }
                });
            }
        });
    }

}

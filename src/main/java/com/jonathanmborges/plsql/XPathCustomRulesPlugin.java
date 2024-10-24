package com.jonathanmborges.plsql;

import org.sonar.api.Plugin;

public class XPathCustomRulesPlugin implements Plugin {

    @Override
    public void define(Plugin.Context context) {
        context.addExtension(XPathCustomRulesDefinition.class);
    }

}

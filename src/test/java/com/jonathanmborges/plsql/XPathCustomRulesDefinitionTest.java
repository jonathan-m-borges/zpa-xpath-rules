package com.jonathanmborges.plsql;

import org.junit.jupiter.api.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XPathCustomRulesDefinitionTest {

    @Test
    public void test() {
        Plugin.Context context = new Plugin.Context(SonarRuntimeImpl.forSonarQube(Version.create(6, 0), SonarQubeSide.SERVER, SonarEdition.COMMUNITY));
        XPathCustomRulesPlugin plugin = new XPathCustomRulesPlugin();
        plugin.define(context);
        assertEquals(1, context.getExtensions().size());
    }

}

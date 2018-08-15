package org.testng;

import java.util.List;
import java.util.Map;

public interface ITestNGMethodWithMetaGroups extends ITestNGMethod {
    /**
     * @return The meta groups this method belongs to.
     */
    Map<String, List<String>> getMetaGroups();
}

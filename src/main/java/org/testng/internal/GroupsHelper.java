package org.testng.internal;

import org.testng.ITestNGMethod;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.internal.annotations.IAnnotationFinder;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class GroupsHelper {

    private GroupsHelper() {
        //Utility class. Defeat instantiation.
    }

    /**
     * Helps create a map of groups (key/value = groupName/groupName) which takes into consideration the
     * included/excluded group in conjunction with a possible group definition that a user may have created
     * via their suite xml file.
     * @param metaGroups Represents a Key/Value pair of dynamically defined groups by the user.
     *                   For e.g.,
     *                   <pre>
     *                                     &lt;groups&gt; <br>
     *                                     &lt;define name="dynamicGroup"&gt; <br>
     *                                     &lt;include name="regressionMethod"/&gt; <br>
     *                                     &lt;/define&gt; <br>
     *                                     &lt;/groups&gt; <br>
     *                                     </pre>
     * @param groups     A {@link List} of groups that are included/excluded in a given &lt;test&gt;
     * @return a map that represents the computed group names.
     */
    public static Map<String, String> createGroups(Map<String, List<String>> metaGroups, List<String> groups) {
        Map<String, String> result = Maps.newHashMap();

        // Groups that were passed on the command line
        for (String group : groups) {
            result.put(group, group);
        }

        if (metaGroups.isEmpty()) {
            return result;
        }

        List<String> unfinishedGroups = Lists.newLinkedList();
        collectGroups(groups, unfinishedGroups, metaGroups, result);

        while (!unfinishedGroups.isEmpty()) {
            List<String> uGroups = Lists.newLinkedList(unfinishedGroups);
            unfinishedGroups = Lists.newLinkedList();
            collectGroups(uGroups, unfinishedGroups, metaGroups, result);
        }

        return result;
    }

    public static Graph<Map.Entry<String, List<String>>> topologicalSort(XmlTest xmlTest,
        Comparator<Map.Entry<String, List<String>>> comparator) {

        final Map<String, List<String>> allMetaGroups = Maps.newHashMap();
        allMetaGroups.putAll(xmlTest.getMetaGroups());
        XmlSuite suite = xmlTest.getSuite();

        while(suite != null) {
            allMetaGroups.putAll(suite.getMetaGroups());
            suite = suite.getParentSuite();
        }



    }

    private static void collectGroups(List<String> groups,
                                      List<String> unfinishedGroups,
                                      Map<String, List<String>> metaGroups,
                                      Map<String, String> result) {
        for (String gn : groups) {
            List<String> subGroups = metaGroups.get(gn);
            if (subGroups == null) {
                continue;
            }
            for (String sg : subGroups) {
                if (null == result.get(sg)) {
                    result.put(sg, sg);
                    unfinishedGroups.add(sg);
                }
            }
        }
    }

}

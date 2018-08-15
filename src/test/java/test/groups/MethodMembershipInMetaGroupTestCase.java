package test.groups;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlDefine;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;

import org.testng.xml.XmlTest;
import test.SimpleBaseTest;
import test.TestNgRunStateListener;
import test.TestNgRunStateTracker;
import test.groups.sample.TestClassAFiveMethodsWithIntraClassMetaGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static test.TestNgRunStateTracker.getTestMethodListenerStartEventLogsForMethod;
import static test.TestNgRunStateTracker.reset;


public class MethodMembershipInMetaGroupTestCase extends SimpleBaseTest {
    private static final String SUITE = "SingleTestSuite";
    private static final String TEST = "SingleTestClassTest";

    @Test
    public void verifyMethodMembershipInMetaGroupsForGroupsTagOnTestTag() {
        reset();

        XmlSuite suite = createXmlSuite(SUITE);

        XmlTest test = createXmlTest(suite, TEST, TestClassAFiveMethodsWithIntraClassMetaGroup.class);

        XmlDefine metaGroup = createXmlDefine("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup",
                "TestClassAFiveMethodsWithIntraClassMetaGroupGroupA",
                "TestClassAFiveMethodsWithIntraClassMetaGroupGroupB");
        XmlDefine allGroups = createXmlDefine("all", ".*Group.*");

        XmlRun xmlRun = createXmlRun(allGroups);

        XmlGroups xmlGroups = new XmlGroups();

        xmlGroups.addDefine(metaGroup);
        xmlGroups.addDefine(allGroups);

        xmlGroups.setRun(xmlRun);

        test.setGroups(xmlGroups);

        addParams(suite, SUITE, TEST, "0");

        TestNG tng = create(suite);

        tng.addListener((ITestNGListener) new TestNgRunStateListener());
        tng.run();

        Map<Object, TestNgRunStateTracker.EventLog> logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodA");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupA"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodB");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupA"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodC");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupDefaultGroup"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodD");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupDefaultGroup"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodE");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupB"));
        }
    }

    @Test
    public void verifyMethodMembershipInMetaGroupsForGroupsTagOnSuiteTag() {
        reset();

        XmlSuite suite = createXmlSuite(SUITE);

        XmlTest test = createXmlTest(suite, TEST, TestClassAFiveMethodsWithIntraClassMetaGroup.class);

        XmlDefine metaGroup = createXmlDefine("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup",
                "TestClassAFiveMethodsWithIntraClassMetaGroupGroupA",
                "TestClassAFiveMethodsWithIntraClassMetaGroupGroupB");
        XmlDefine allGroups = createXmlDefine("all", ".*Group.*");

        XmlRun xmlRun = createXmlRun(allGroups);

        XmlGroups xmlGroups = new XmlGroups();

        xmlGroups.addDefine(metaGroup);
        xmlGroups.addDefine(allGroups);

        xmlGroups.setRun(xmlRun);

        suite.setGroups(xmlGroups);

        addParams(suite, SUITE, TEST, "0");

        TestNG tng = create(suite);

        tng.addListener((ITestNGListener) new TestNgRunStateListener());
        System.out.println(suite.toXml());
        tng.run();

        Map<Object,TestNgRunStateTracker.EventLog> logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodA");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupA"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodB");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupA"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodC");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupDefaultGroup"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodD");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupDefaultGroup"));
        }

        logs = getTestMethodListenerStartEventLogsForMethod(SUITE, TEST,
                TestClassAFiveMethodsWithIntraClassMetaGroup.class.getCanonicalName(),
                "testMethodE");

        assertTrue(logs.size() > 0);

        for(Object obj : logs.keySet()) {
            String[] groups = (String[])logs.get(obj).getData(TestNgRunStateTracker.EventInfo.GROUPS_BELONGING_TO);
            assertTrue(Arrays.asList(groups).contains("all"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupMetaGroup"));
            assertTrue(Arrays.asList(groups).contains("TestClassAFiveMethodsWithIntraClassMetaGroupGroupB"));
        }
    }

    public static void addParams(XmlSuite suite, String suiteName, String testName, String sleepFor) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("suiteName", suiteName);
        parameters.put("testName", testName);
        parameters.put("sleepFor", sleepFor);

        for (XmlTest test : suite.getTests()) {
            if (test.getName().equals(testName)) {
                test.setParameters(parameters);
            }
        }
    }
}

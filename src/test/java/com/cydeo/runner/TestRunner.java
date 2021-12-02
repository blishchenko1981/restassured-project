package com.cydeo.runner;


import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Smoke Test")
//@SelectPackages("com.cydeo.tests.day9")  // to run entire  day8 package
//@SelectClasses(BaseAuthTest.class)  // I just want to run BaseAuth Test
//@SelectPackages({"com.cydeo.tests.day8", "com.cydeo.tests.day1"})

@SelectPackages("com.cydeo.tests")  // start looking for location
//@IncludeTags({"smoke1", "smoke2"})        // specify what to run

//@IncludeTags({"smokeAll", "day5test"})   // tag could be placed on class level
//@ExcludeTags("smoke2")     // exclude some tests with particular @TAG

@IncludeTags("db")
public class TestRunner {

}

package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAPI_BaseTest;
import org.junit.jupiter.api.Test;

public class LibraryAPIDBTest extends LibraryAPI_BaseTest {

@Test
    public void testDB(){

    DB_Util.runQuery("SELECT * FROM books");
    DB_Util.displayAllData();


}


}

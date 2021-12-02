package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import org.junit.jupiter.api.Test;

public class API_DB_Test {

    @Test
    public void testRunSimpleQuery(){

        DB_Util.createConnection();

        DB_Util.runQuery("select * from regions");
        DB_Util.displayAllData();
        DB_Util.destroy();

    }

}

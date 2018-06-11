package com.fehorizon.busi;

import com.fehorizon.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTransDetailSheetParseBusi extends TestBase{
    @Autowired
    private TransDetailSheetParseBusi transDetailSheetParseBusi;

    @Test
    public void testParseTransDetail(){
        transDetailSheetParseBusi.parseTransDetailSheet();
    }
}

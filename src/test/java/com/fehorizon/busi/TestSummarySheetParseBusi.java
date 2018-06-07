package com.fehorizon.busi;

import com.fehorizon.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestSummarySheetParseBusi extends TestBase {

    @Autowired
    private SummarySheetParseBusi summarySheetParseBusi;

    @Test
    public void testParseSummarySheet(){
        summarySheetParseBusi.parseSheet();
    }
}

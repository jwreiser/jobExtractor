package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.both.RemoteFilter;
import com.goodforallcode.jobExtractor.filters.deep.CloudFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RemoteFilterTests {
    RemoteFilter filter =new RemoteFilter();
    @Test
    void testIsTitleRemote_NotRemote() {
        assertFalse(filter.isTitleRemote("this position is not remote."));
    }

    @Test
    void testIsTitleRemote_BasedIn() {
        assertFalse(filter.isTitleRemote("this position is based in houston,texas"));
    }
}

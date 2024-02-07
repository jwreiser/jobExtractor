package com.goodforallcode.jobExtractor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTests {
    @Test
    void testCompressDescription(){
        String result = StringUtil.compressDescription("About the job LTI (Logic Technology, Inc.) the \\\"Pro People\\\" company ", "");
        assertEquals("lti logic people",result);

    }

    @Test
    void testCompressDescription_Dice(){
        String result = StringUtil.compressDescription("About the job Dice is the leading career destination for tech experts at every stage of their careers. Our client, McKinsol Consulting Inc, is seeking the following. Apply via Dice today! Role: LabView Developer Location: Anywhere in US / Carlsbad, California (Remote) Good Skills in SQL. LabView programming knowledge. S Supply chain domain experience Experience working in Support project Type of work Manual work with SQL databases to identify and correct data-entry or other errors SQL interface for supporting features LabVIEW programming language to support the client and server tools Business support for reporting/analysis/inventory – mostly investigating things don’t make sense in the data LabVIEW Developer || Remote",
                "LabVIEW Developer || Remote");
        assertEquals("leading client mckinsol consulting labview anywhere carlsbad california remote sql labview s supply chain domain support project manual sql databases correct sql interface supporting labview language support client server support reporting analysis inventory – mostly investigating things sense data",result);

    }


    @Test
    void trimUrl(){
        String result = StringUtil.trimUrl("http://linkedin.com/jobs/view/3786721580/?eBP=NON_CHARGEABLE_CHANNEL&refId=9XySThhrZF7k0rfPet3Ikg%3D%3D&trackingId=vxnsy8RjJUBtvUbO7FdAAg%3D%3D&trk=flagship3_search_srp_jobs");
        assertEquals("http://linkedin.com/jobs/view/3786721580/",result);

    }

}

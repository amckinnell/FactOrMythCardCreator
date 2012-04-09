package com.valuablecode.test;

import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getPath;
import static org.apache.commons.io.FilenameUtils.getPrefix;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FilenameUtilsLearningTest {

    final String testFilename = "/Users/alistair/ValuableCode/Clients/Shaw Media/Card Source/Offering.txt";

    @Test public void
    test() {
        assertThat(getPrefix(testFilename), equalTo("/"));
        assertThat(getPath(testFilename), equalTo("Users/alistair/ValuableCode/Clients/Shaw Media/Card Source/"));
        assertThat(getBaseName(testFilename), equalTo("Offering"));
        assertThat(getExtension(testFilename), equalTo("txt"));
    }

}

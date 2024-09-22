package com.adamhedges.utilities;

import com.adamhedges.utilities.filesystem.ResourceUtilities;
import org.junit.Assert;
import org.junit.Test;

public class TestResourceUtilities {

    @Test
    public void TestResourceUrlLoader() {
        String testResourcePath = ResourceUtilities.getResourceFilePath("someroot", "table-results.txt");
        Assert.assertEquals("/Users/adam/java/utilities/target/test-classes/table-results.txt", testResourcePath);

        String otherResourcePath = ResourceUtilities.getResourceFilePath("someroot", "some-other-file.txt");
        Assert.assertEquals("someroot/some-other-file.txt", otherResourcePath);
    }

}

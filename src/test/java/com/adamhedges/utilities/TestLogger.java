package com.adamhedges.utilities;

import org.junit.Assert;
import org.junit.Test;

import com.adamhedges.utilities.logger.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.List;

public class TestLogger {

	@Test
	public void testLoggerOutput() throws Exception {

		String filename = "";
        String entry1 = "Testing log entry only";
        String entry2 = "Testing log entry with console echo";

		try (Logger logger = new Logger("testlogs", "testing", ZoneId.of("America/Chicago"))) {
			logger.log(entry1);
			logger.log(entry2, true);
			filename = logger.logFileName;
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

        Path testLogsPath = Paths.get("testlogs", filename);
        String output = new String(Files.readAllBytes(testLogsPath));

        List<String> logLines = output.lines().toList();
        Assert.assertTrue(logLines.get(0).endsWith(entry1));
        Assert.assertTrue(logLines.get(1).endsWith(entry2));
        Assert.assertTrue(logLines.get(2).endsWith("Closing log file..."));

		Files.delete(testLogsPath);
		Files.delete(Paths.get("testlogs"));

	}

}

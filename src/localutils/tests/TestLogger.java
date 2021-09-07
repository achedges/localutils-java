package localutils.tests;

import localutils.logger.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestLogger {

	@Test
	public void testLoggerOutput() throws Exception {

		String filename = "";

		try (Logger logger = new Logger("testlogs", "testing")) {
			logger.log("Testing log entry only");
			logger.log("Testing log entry with console echo", true);
			filename = logger.logFileName;
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		String output = new String(Files.readAllBytes(Paths.get("testlogs", filename)));
		String expected = new String(Files.readAllBytes(Paths.get("log-results.txt")));

		Assert.assertEquals(expected, output);

		Files.delete(Paths.get("testlogs", filename));
		Files.delete(Paths.get("testlogs"));

	}

}

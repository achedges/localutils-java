package localutils.tests;

import localutils.filesystem.FileSystemUtilities;
import org.junit.Test;

public class TestDownloader {
	@Test
	public void testFileDownload() {
		String url = "https://www.quandl.com/api/v3/databases/ASINDU/download?api_key=fyGPkWbZ4ssvdsmUTH46&download_type=all-trading-days";
		FileSystemUtilities.downloadFile(url, "all-trading-days.zip");
		FileSystemUtilities.deleteFile("all-trading-days.zip");
	}
}

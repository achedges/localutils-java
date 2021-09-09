package localutils.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements AutoCloseable {

	public final String logFilePath;
	public final String logFilePrefix;
	public final String logFileName;

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss_n");

	private BufferedWriter log;

	public Logger() throws Exception {
		logFilePath = "logs";
		logFilePrefix = "log";
		logFileName = String.format("%s_%s.txt", logFilePrefix, LocalDateTime.now().format(dateTimeFormatter));
		initializeLogFile();
	}

	public Logger(String filePath, String filePrefix) throws Exception {
		logFilePath = filePath;
		logFilePrefix = filePrefix;
		logFileName = String.format("%s_%s.txt", logFilePrefix, LocalDateTime.now().format(dateTimeFormatter));
		initializeLogFile();
	}

	private void initializeLogFile() throws Exception {
		File logDirectory = new File(logFilePath);
		if (!logDirectory.exists())
			Files.createDirectory(Paths.get(logFilePath));

		log = new BufferedWriter(new FileWriter(Paths.get(logFilePath, logFileName).toString()));
	}

	@Override
	public void close() throws Exception {
		if (log != null) {
			log("Closing log file...");
			log.flush();
			log.close();
		}
	}

	public void log(String msg) throws Exception {
		log(msg, false);
	}

	public void log(String msg, boolean toConsole) throws Exception {
		log.write(String.format("%s%n", msg));
		if (toConsole)
			System.out.println(msg);
	}

}
package com.adamhedges.utilities.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements AutoCloseable {

	public final String logFilePath;
	public final String logFilePrefix;
	public final String logFileName;
    public final ZoneId timestampZoneId;

	private final DateTimeFormatter fileNameDtFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss_n");
    private final DateTimeFormatter logTimestampFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

	private BufferedWriter log;

	public Logger() throws Exception {
		logFilePath = "logs";
		logFilePrefix = "log";
		logFileName = String.format("%s_%s.txt", logFilePrefix, LocalDateTime.now().format(fileNameDtFormatter));
        timestampZoneId = null;
		initializeLogFile();
	}

	public Logger(String filePath, String filePrefix, ZoneId timestampZoneId) throws Exception {
		logFilePath = filePath;
		logFilePrefix = filePrefix;
		logFileName = String.format("%s_%s.txt", logFilePrefix, LocalDateTime.now().format(fileNameDtFormatter));
        this.timestampZoneId = timestampZoneId;
		initializeLogFile();
	}

	private void initializeLogFile() throws Exception {
		File logDirectory = new File(logFilePath);
		if (!logDirectory.exists())
			Files.createDirectory(Paths.get(logFilePath));

		log = new BufferedWriter(new FileWriter(Paths.get(logFilePath, logFileName).toString()), 256); // use a small buffer so log entries show up quicker
	}

	@Override
	public void close() throws Exception {
		if (log != null) {
			log("Closing log file...");
			log.flush();
			log.close();
			log = null;
		}
	}

	public void log(String msg) {
		log(msg, false);
	}

	public void log(String msg, boolean toConsole) {
		try {
            ZonedDateTime now = ZonedDateTime.now();
            if (timestampZoneId != null) {
                now = now.withZoneSameInstant(timestampZoneId);
            }

            String message = String.format("%-32s %s", logTimestampFormatter.format(now), msg);
			log.write(String.format("%s%n", message));

			if (toConsole) {
                System.out.println(message);
            }
		} catch (Exception ex) {
			System.out.println(msg);
			System.out.printf("%s exception: %s%n", getClass().getName(), ex.getMessage());
		}
	}

}

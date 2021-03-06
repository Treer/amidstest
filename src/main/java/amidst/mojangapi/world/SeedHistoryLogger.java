package amidst.mojangapi.world;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import amidst.documentation.NotNull;
import amidst.documentation.ThreadSafe;
import amidst.logging.AmidstLogger;
import amidst.mojangapi.minecraftinterface.RecognisedVersion;

@ThreadSafe
public class SeedHistoryLogger {
	public static SeedHistoryLogger from(String filename) {
		if (filename != null) {
			AmidstLogger.info("using seed history file: '" + filename + "'");
			return new SeedHistoryLogger(new File(filename), true, true);
		} else {
			return new SeedHistoryLogger(new File(HISTORY_TXT), false, true);
		}
	}

	public static SeedHistoryLogger createDisabled() {
		return new SeedHistoryLogger(new File(HISTORY_TXT), false, false);
	}

	private static final String HISTORY_TXT = "history.txt";

	private final File file;
	private final boolean createIfNecessary;
	private final boolean isEnabled;

	public SeedHistoryLogger(@NotNull File file, boolean createIfNecessary, boolean isEnabled) {
		Objects.requireNonNull(file);
		this.file = file;
		this.createIfNecessary = createIfNecessary;
		this.isEnabled = isEnabled;
	}

	public void log(RecognisedVersion recognisedVersion, WorldSeed worldSeed) {
		if (isEnabled) {
			doLog(recognisedVersion, worldSeed);
		}
	}

	private synchronized void doLog(RecognisedVersion recognisedVersion, WorldSeed worldSeed) {
		if (createIfNecessary && !file.exists()) {
			tryCreateFile();
		}
		if (file.isFile()) {
			writeLine(createLine(recognisedVersion, worldSeed));
		} else {
			AmidstLogger.info("Not writing to seed history file, because it does not exist: " + file);
		}
	}

	private String createLine(RecognisedVersion recognisedVersion, WorldSeed worldSeed) {
		String recognisedVersionName = recognisedVersion.getName();
		String timestamp = createTimestamp();
		String seedString = getSeedString(worldSeed);
		return recognisedVersionName + ", " + timestamp + ", " + seedString;
	}

	private String createTimestamp() {
		return new Timestamp(new Date().getTime()).toString();
	}

	private String getSeedString(WorldSeed worldSeed) {
		String text = worldSeed.getText();
		if (text != null) {
			return worldSeed.getLongAsString() + ", " + text;
		} else {
			return worldSeed.getLongAsString();
		}
	}

	private void tryCreateFile() {
		try {
			file.createNewFile();
		} catch (IOException e) {
			AmidstLogger.warn(e, "Unable to create seed history file: " + file);
		}
	}

	private void writeLine(String line) {
		try (PrintStream stream = new PrintStream(new FileOutputStream(file, true))) {
			stream.println(line);
		} catch (IOException e) {
			AmidstLogger.warn(e, "Unable to write to seed history file: " + file);
		}
	}
}

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileSysUtils {

	public static List<String> listFiles(String folder) {
		List<String> ret = new ArrayList<>();
		File[] files = (new File(folder)).listFiles();
		if (files == null)
			return ret;

		for (File file : files)
			if (file.isFile())
				ret.add(file.getName());

		return ret;
	}

	private static void _deleteFileOrFolder(String path) {
		File fileOrFolder = new File(path);
		if (!fileOrFolder.delete())
			System.out.println(String.format("Unable to delete %s %s", fileOrFolder.isFile() ? "file" : "folder", path));
	}

	public static void deleteFile(String path) {
		FileSysUtils._deleteFileOrFolder(path);
	}

	public static void deleteFolder(String path) {
		FileSysUtils._deleteFileOrFolder(path);
	}

	public static boolean fileExists(String path) {
		return (new File(path)).exists();
	}

	public static void cleanFolder(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files)
				if (!file.delete())
					System.out.println(String.format("Unable to delete file %s", file.getName()));
		}

		if (!folder.delete())
			System.out.println(String.format("Unable to delete folder %s", folder.getName()));
	}

	public static void downloadFile(String url, String downloadPath) {
		try {
			BufferedInputStream urlreq = new BufferedInputStream(new URL(url).openStream());
			FileOutputStream filestream = new FileOutputStream(downloadPath);

			byte[] buffer = new byte[1024];
			int bytesread;

			while ((bytesread = urlreq.read(buffer, 0, 1024)) != -1)
				filestream.write(buffer, 0, bytesread);

			filestream.close();
			urlreq.close();
		}
		catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
	}

	public static void extractFile(String archivePath, String extractPath, boolean autoClean) {
		try {
			byte[] buffer = new byte[1024];
			ZipInputStream zipfile = new ZipInputStream(new FileInputStream(archivePath));
			ZipEntry entry;

			while ((entry = zipfile.getNextEntry()) != null) {
				String filename = entry.getName();
				File entryfile = new File(filename);
				FileOutputStream outputStream = new FileOutputStream(extractPath + entryfile.getName());

				int len;
				while ((len = zipfile.read(buffer)) > 0)
					outputStream.write(buffer, 0, len);

				outputStream.close();
			}

			zipfile.closeEntry();
			zipfile.close();

			if (autoClean)
				FileSysUtils.deleteFile(archivePath);
		}
		catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
	}

}

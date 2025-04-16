package spring.library_gunel_aslanova.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExportScheduler {

	@Scheduled(cron = "0 */1 * * * *")
	public void exportDatabase() {
		String user = "root";
		String password = "1234";
		String database = "library-gunel-aslanova";
		String exportPath = "C:\\Users\\User\\Downloads\\backup";

		try {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			String fileName = Paths.get(exportPath, database + "_" + timestamp + ".sql").toString();

			Files.createDirectories(Paths.get(exportPath));
			String mysqldumpPath = "\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe\"";
			String command = String.format("%s -u%s -p%s %s -r \"%s\"", mysqldumpPath, user, password, database,
					fileName);

			ProcessBuilder builder = new ProcessBuilder(mysqldumpPath, "-u" + user, "-p" + password, database);
			builder.redirectOutput(Paths.get(fileName).toFile());
			Process process = builder.start();
			int result = process.waitFor();

			if (result == 0) {
				System.out.println("Backup uğurla yaradıldı: " + fileName);
			} else {
				System.err.println(" Backup zamanı xəta baş verdi.");
			}

		} catch (Exception e) {
			System.err.println("Backup zamanı istisna baş verdi: " + e.getMessage());
			e.printStackTrace();
		}
	}

}

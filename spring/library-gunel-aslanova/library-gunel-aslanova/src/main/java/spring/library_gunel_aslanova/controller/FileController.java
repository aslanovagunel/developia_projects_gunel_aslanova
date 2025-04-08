package spring.library_gunel_aslanova.controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;
import spring.library_gunel_aslanova.exception.MyException;


@RestController
@RequestMapping(path = "/files")
public class FileController {

	@Autowired
	// For video download
	private ResourceLoader resourceLoader;

	private Path rootLocation;

	@PostConstruct
	private void init() {
		rootLocation = Paths.get("C:/files");
	}

	// Save file to disk service method
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestParam(name = "file") MultipartFile file) throws Exception {

		InputStream stream = file.getInputStream();
		// alma.png
		String originalFilename = file.getOriginalFilename();

		if (file.getSize() > 30_000_000) {
			throw new MyException("max 30 mb olar", null, "too-big-file");
		}

		// Create random name
		UUID uuid = UUID.randomUUID();
		String randomName = uuid.toString(); // dsakhk-43dwqeqw-werwerwe-4234234-wwerwer
		// dsakhk-43dwqeqw-werwerwe-4234234-wwerwer.png

		// Get file original name without extension
		String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));

		// File random name for saving on disk
		String fileRandomName = originalFilename.replace(fileNameWithoutExtension, randomName);

		// Save file to disk
		Files.copy(stream, Paths.get("C:/files/" + fileRandomName), StandardCopyOption.REPLACE_EXISTING);

	}

	@GetMapping(path = "/video/{title}", produces = "video/mp4")

	public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader String range) {
		System.out.println("nese " + range);
		return getVideo(title);

	}

	public Mono<Resource> getVideo(String title) {

		return Mono.fromSupplier(() -> resourceLoader.getResource("file:C:/files/" + title));

	}

//
//	@GetMapping("/download/{filename:.+}")
//	@ResponseBody
//	// @PreAuthorize(value = "hasAuthority('ROLE_LOGIN')")
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//		FileEntity fileEntity = fileRepository.findByFileUuidName(filename);
//		if (fileEntity == null) {
//			throw new OurRuntimeException("Fayl tapılmadı!", "", "Not found", 404);
//
//		}
//		Resource file = loadAsResource(filename);
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
//				.body(file);
//	}

	@GetMapping("/download/raw/{filename:.+}")
	@ResponseBody

	public ResponseEntity<Resource> serveRawFile(@PathVariable String filename) {

		Resource file = loadAsResource(filename);
		if (file == null) {
			throw new MyException("Fayl tapılmadı!", null, "Not found");
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
				.body(file);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
//
//	// bura kimi seliqeye salinib
//
//	@GetMapping("/file-object/{fileId}")
//	@PreAuthorize(value = "hasAuthority('ROLE_LOGIN')")
//
//	public FileEntity findById(@PathVariable Integer fileId) {
//
//		FileEntity fileEntity = fileRepository.findById(fileId).get();
//
//		return fileEntity;
//
//	}
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SecurityService securityService;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	// Save profile photo to disk service method
//	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/upload-profile-photo")
//	@PreAuthorize(value = "hasAuthority('ROLE_LOGIN')")
//
//	public FileResponseModel uploadProfilePhoto(@RequestParam(name = "file") MultipartFile file) throws Exception {
//		// Get os name
//
//		if (file.getSize() > 1000_000) {
//			throw new OurRuntimeException("Faylın ölçüsü çoxdur. Maksimum 1 MB", "file size is too big", "bad req",
//					400);
//		}
//
//		String os = System.getProperty("os.name");
//		System.out.println(os);
//
//		// Get stream from file
//		InputStream stream = file.getInputStream();
//
//		// Get file original name
//		String originalFilename = file.getOriginalFilename();
//
//		// Create random name
//		UUID uuid = UUID.randomUUID();
//		String randomName = uuid.toString();
//
//		// Get file original name without extension
//		String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
//
//		// Get file extension
//		String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
//		fileExtension = fileExtension.toLowerCase();
//		if (fileExtension.equals(".jpg") || fileExtension.equals(".png")) {
//
//		} else {
//			throw new OurRuntimeException("Ancaq .jpg və .png formatında şəkilləri yükləmək mümkündür.",
//					"format is bad", "bad request", 400);
//		}
//
//		// File random name for saving on disk
//		String fileRandomName = originalFilename.replace(fileNameWithoutExtension, randomName);
//
//		// Save file to disk
//		Files.copy(stream, Paths.get(variables.getFileLocation() + "/" + fileRandomName),
//				StandardCopyOption.REPLACE_EXISTING);
//
//		// Create file entity object
//		FileEntity fileModel = new FileEntity();
//		fileModel.setFileName(originalFilename);
//
//		fileModel.setFileUuidName(fileRandomName);
//		fileModel.setFileSize(file.getSize());
//
//		fileModel.setFileExtension(fileExtension);
//		fileRepository.save(fileModel);
//
//		// Create response bean
//		FileResponseModel bean = new FileResponseModel();
//
//		bean.setFileUuidName(fileRandomName);
//		bean.setFileName(originalFilename);
//		bean.setFileEntity(fileModel);
//
//		// get file id
//		Integer imageId = fileModel.getId();
//
//		String username = securityService.getCurrentUsername();
//		UserEntity user = userRepository.findById(username).get();
//		String userType = user.getUserType();
//		userService.updateUserProfilePhoto(userType, imageId);
//
//		// Return response bean
//		return bean;
//	}
//
//	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/upload-course-logo")
//	@PreAuthorize(value = "hasAuthority('ROLE_SUPER_ADMIN')")
//
//	public FileResponseModel uploadLogo(@RequestParam(name = "file") MultipartFile file) throws Exception {
//		// Get os name
//
//		if (file.getSize() > 1000_000) {
//			throw new OurRuntimeException("Faylın ölçüsü çoxdur. Maksimum 1 MB", "file size is too big", "bad req",
//					400);
//		}
//
//		// Get stream from file
//		InputStream stream = file.getInputStream();
//
//		// Get file original name
//		String originalFilename = file.getOriginalFilename();
//
//		// Create random name
//		UUID uuid = UUID.randomUUID();
//		String randomName = uuid.toString();
//
//		// Get file original name without extension
//		String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
//
//		// Get file extension
//		String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
//		fileExtension = fileExtension.toLowerCase();
//		if (fileExtension.equals(".jpg") || fileExtension.equals(".png")) {
//
//		} else {
//			throw new OurRuntimeException("Ancaq .jpg və .png formatında şəkilləri yükləmək mümkündür.",
//					"format is bad", "bad request", 400);
//		}
//
//		// File random name for saving on disk
//		String fileRandomName = originalFilename.replace(fileNameWithoutExtension, randomName);
//
//		// Save file to disk
//		Files.copy(stream, Paths.get(variables.getFileLocation() + "/" + fileRandomName),
//				StandardCopyOption.REPLACE_EXISTING);
//
//		// Create file entity object
//
//		// Create response bean
//		FileResponseModel bean = new FileResponseModel();
//
//		bean.setFileUuidName(fileRandomName);
//		bean.setFileName(originalFilename);
//		Integer courseId = securityService.findThisUserCourseId();
//		CourseEntity course = courseRepository.findById(courseId).get();
//		course.setLogo(fileRandomName);
//		courseRepository.save(course);
//
//		// Return response bean
//		return bean;
//	}
}

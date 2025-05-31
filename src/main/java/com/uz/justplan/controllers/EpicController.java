package com.uz.justplan.controllers;

import com.uz.justplan.lookup.EpicDetailType;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicDetail;
import com.uz.justplan.plan.EpicDetailRepository;
import com.uz.justplan.plan.EpicRepository;
import com.uz.justplan.services.CompanyDashboardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController()
@RequestMapping("/epics")
public class EpicController {
    private static final Log log = LogFactory.getLog(EpicController.class);
    private final Path fileStorageLocation;
    private EpicDetailRepository epicDetailRepository;
    private EpicRepository epicRepository;
    private CompanyDashboardService companyDashboardService;

    @Autowired
    public EpicController(@Value("${file.upload-dir}") String uploadDir,
                          EpicDetailRepository epicDetailRepository,
                          EpicRepository epicRepository,
                          CompanyDashboardService companyDashboardService) {
        this.epicDetailRepository = epicDetailRepository;
        this.epicRepository = epicRepository;
        this.companyDashboardService = companyDashboardService;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @PostMapping("/create")
    public Epic addEpic(@RequestBody Epic epic) {
        epic.setCode(companyDashboardService.getEpicCodeForNewEpic(epic.getProductId()));
        return epicRepository.save(epic);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not store file: " + ex.getMessage());
        }
    }

    @PostMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileWithJson(
            @PathVariable("id") Long epicId,
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") Map<String, String> metadata) {
        // Save file
        try {
            log.info("upload 1");
            // Access metadata
            String name = file.getOriginalFilename();
            String description = metadata.get("details");
            log.info("upload 2");
            Long prodId = epicRepository.findById(epicId).get().getProductId();
            Path targetLocation = this.fileStorageLocation.resolve(prodId.toString()).resolve(epicId.toString());
            Files.createDirectories(targetLocation);
            targetLocation = targetLocation.resolve(name);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            EpicDetail epicDetail = new EpicDetail();
            epicDetail.setName(name);
            epicDetail.setDetails(description);
            log.info("targetLocation.normalize:" + targetLocation.normalize().toString());
            log.info("targetLocation.toAbsolutePath:" + targetLocation.toAbsolutePath().normalize());
            epicDetail.setLink(targetLocation.normalize().toString());
            epicDetail.setEpicId(epicId);
            epicDetail.setDetailType(EpicDetailType.ATTACHED_FILE);
            epicDetail.setActive(true);
            epicDetailRepository.save(epicDetail);
            //return epicDetail;
            return ResponseEntity.ok(epicDetail);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        try {
            EpicDetail epicDetail = epicDetailRepository.findById(id).orElseThrow();
            Path filePath = Paths.get(epicDetail.getLink()).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

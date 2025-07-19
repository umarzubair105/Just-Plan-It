package com.uz.justplan.controllers;

import com.uz.justplan.core.CompanyRepository;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.EntityDetailType;
import com.uz.justplan.lookup.EntityType;
import com.uz.justplan.plan.EntityDetail;
import com.uz.justplan.plan.EntityDetailRepository;
import com.uz.justplan.plan.ReleaseRepository;
import com.uz.justplan.resources.ResourceRepository;
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
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/misc")
public class MiscController {
    private static final Log log = LogFactory.getLog(MiscController.class);
    private final Path fileStorageLocation;
    private EntityDetailRepository entityDetailRepository;
    private ResourceRepository resourceRepository;
    private CompanyRepository companyRepository;
    private ReleaseRepository releaseRepository;
    private ProductRepository productRepository;

    @Autowired
    public MiscController(@Value("${file.upload-dir}") String uploadDir,
                          EntityDetailRepository entityDetailRepository, ResourceRepository resourceRepository,
                          CompanyRepository companyRepository, ReleaseRepository releaseRepository,
                          ProductRepository productRepository) {
        this.entityDetailRepository = entityDetailRepository;
        this.resourceRepository = resourceRepository;
        this.companyRepository = companyRepository;
        this.releaseRepository = releaseRepository;
        this.productRepository = productRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }


    @PostMapping(value = "/entity-details/{id}/{companyId}/{entityType}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileWithJson(
            @PathVariable("id") Long entityId,
            @PathVariable("companyId") Long companyId,
            @PathVariable("entityType") String entityType,
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") Map<String, String> metadata) {
        // Save file
        try {
            log.info("upload 1");
            // Access metadata
            String name = file.getOriginalFilename();
            String description = metadata.get("details");
            log.info("upload 2");
            //Long prodId = epicRepository.findById(epicId).get().getProductId();
            Path targetLocation = this.fileStorageLocation
                    .resolve(companyId.toString())
                    .resolve(entityType)
                    .resolve(entityId.toString());
            Files.createDirectories(targetLocation);
            targetLocation = targetLocation.resolve(name);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            EntityDetail entityDetail = new EntityDetail();
            entityDetail.setName(name);
            entityDetail.setDetails(description);
            log.info("targetLocation.normalize:" + targetLocation.normalize().toString());
            log.info("targetLocation.toAbsolutePath:" + targetLocation.toAbsolutePath().normalize());
            entityDetail.setLink(targetLocation.normalize().toString());
            entityDetail.setEntityId(entityId);
            entityDetail.setEntityType(EntityType.valueOf(entityType));
            entityDetail.setDetailType(EntityDetailType.ATTACHED_FILE);
            entityDetail.setActive(true);
            entityDetailRepository.save(entityDetail);
            return ResponseEntity.ok(entityDetail);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/entity-details/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        try {
            EntityDetail entityDetail = entityDetailRepository.findById(id).orElseThrow();
            Path filePath = Paths.get(entityDetail.getLink()).toAbsolutePath().normalize();
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

    @GetMapping("/entity-details")
    public List<EntityDetail> findEntityDetails(@RequestParam EntityType entityType, @RequestParam long entityId) {
        List<EntityDetail> list = entityDetailRepository.findByEntityTypeAndEntityIdAndActiveIsTrueOrderByCreatedDateAsc(entityType, entityId);
        list.stream().forEach(e -> {
            if (e.getCreatedById() != null) {
                e.setCreatedByName(resourceRepository.findById(e.getCreatedById()).get().getName());
            }
        });
        return list;
    }


}

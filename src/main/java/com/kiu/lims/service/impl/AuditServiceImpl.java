package com.kiu.lims.service.impl;

import com.kiu.lims.entity.AuditEntity;
import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.AuditRepository;
import com.kiu.lims.repository.InternalQualityAssuranceRepository;
import com.kiu.lims.service.AuditService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public ResponseModel getAllAudit() {
        ResponseModel responseModel = new ResponseModel();
        List<AuditEntity> auditEntityList = auditRepository.findAll();

        if (auditEntityList.isEmpty()) {
            responseModel.setCode(204); // No Content
            responseModel.setMessage("No reports found.");
        } else {
            responseModel.setCode(200);
            responseModel.setMessage("Audit reports retrieved successfully.");
            responseModel.setData(auditEntityList);
        }
        return responseModel;
    }

    @Override
    public ResponseModel createAudit(AuditEntity manual, MultipartFile file) {
        ResponseModel responseModel = new ResponseModel();

        try {
            if (file != null && !file.isEmpty()) {
                // Save the uploaded file to a specific directory (e.g., "uploads").
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                String uploadDir = "uploads";
                String filePath = Paths.get(uploadDir, fileName).toString();
                Files.createDirectories(Paths.get(uploadDir));
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

                // Set the file path in the manual entity.
                manual.setFilePath(filePath);
            }

            manual.setCreatedAt(Timestamp.from(Instant.now()));
            // Save the practical manual entity to the database.
            AuditEntity savedManual = auditRepository.save(manual);

            responseModel.setCode(201); // Created
            responseModel.setMessage("Report created successfully.");
            responseModel.setData(savedManual);
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while creating the reports.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel updateAudit(Long manualId, Map<String, Object> updatedFields) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<AuditEntity> optionalManual = auditRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                AuditEntity existingManual = optionalManual.get();

                // Update only the provided fields
                if (updatedFields.containsKey("title")) {
                    existingManual.setTitle((String) updatedFields.get("title"));
                }
                if (updatedFields.containsKey("description")) {
                    existingManual.setDescription((String) updatedFields.get("description"));
                }
                if (updatedFields.containsKey("updated_by")) {
                    // Assuming updated_by is an Integer in InternalQualityAssuranceEntity
                    existingManual.setUpdatedBy((Integer) updatedFields.get("updated_by"));
                }

                // Set the manualId and updatedAt from the existing manual
                existingManual.setManualId(manualId);
                existingManual.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                AuditEntity savedManual = auditRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Audit report updated successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Audit report not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the Audit report.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel deleteAudit(Long manualId, Map<String, Object> deletedFields) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<AuditEntity> optionalManual = auditRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                AuditEntity existingManual = optionalManual.get();

                // Set deleted_at and deleted_by
                existingManual.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                if (deletedFields.containsKey("deleted_by")) {
                    // Assuming deleted_by is an Integer in PracticalManualEntity
                    existingManual.setDeletedBy((Integer) deletedFields.get("deleted_by"));
                }

                AuditEntity savedManual = auditRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Audit report deleted successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Audit report not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting the Audit report.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }


//    @Override
//    public ResponseModel deletePracticalManual(Long manualId) {
//        ResponseModel responseModel = new ResponseModel();
//        try {
//            Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);
//            if (optionalManual.isPresent()) {
//                practicalManualRepository.deleteById(manualId);
//                responseModel.setCode(204); // No Content
//                responseModel.setMessage("Practical manual deleted successfully.");
//            } else {
//                responseModel.setCode(404); // Not Found
//                responseModel.setMessage("Practical manual not found.");
//            }
//        } catch (Exception e) {
//            responseModel.setCode(500); // Internal Server Error
//            responseModel.setMessage("An error occurred while deleting the practical manual.");
//            // You can log the exception or include additional error details in the message.
//        }
//        return responseModel;
//    }

    @Override
    public ResponseModel getAuditById(Long manualId) {
        ResponseModel responseModel = new ResponseModel();
        Optional<AuditEntity> optionalManual = auditRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage("Audit report retrieved successfully.");
            responseModel.setData(optionalManual.get());
        } else {
            responseModel.setCode(404); // Not Found
            responseModel.setMessage("Audit report not found.");
        }
        return responseModel;
    }

    @Override
    public Resource downloadManualPdf(Long manualId) {
        Optional<AuditEntity> optionalManual = auditRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            AuditEntity manual = optionalManual.get();
            String filePath = manual.getFilePath();
            try {
                Resource resource = new UrlResource(Paths.get(filePath).toUri());
                if (resource.exists()) {
                    return resource;
                } else {
                    throw new FileNotFoundException("PDF file not found");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error downloading PDF file", e);
            }
        } else {
            throw new EntityNotFoundException("Audit report not found");
        }
    }

}

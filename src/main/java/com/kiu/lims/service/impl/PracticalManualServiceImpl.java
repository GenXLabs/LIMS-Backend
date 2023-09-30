package com.kiu.lims.service.impl;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.PracticalManualRepository;
import com.kiu.lims.service.PracticalManualService;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class PracticalManualServiceImpl implements PracticalManualService {

    private final PracticalManualRepository practicalManualRepository;

    @Override
    public ResponseModel getAllPracticalManuals() {
        ResponseModel responseModel = new ResponseModel();
        List<PracticalManualEntity> practicalManualEntityList = practicalManualRepository.findAll();

        if (practicalManualEntityList.isEmpty()) {
            responseModel.setCode(204); // No Content
            responseModel.setMessage("No practical manuals found.");
        } else {
            responseModel.setCode(200);
            responseModel.setMessage("Practical manuals retrieved successfully.");
            responseModel.setData(practicalManualEntityList);
        }
        return responseModel;
    }

    @Override
    public ResponseModel createPracticalManual(PracticalManualEntity manual, MultipartFile file) {
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
            PracticalManualEntity savedManual = practicalManualRepository.save(manual);

            responseModel.setCode(201); // Created
            responseModel.setMessage("Practical manual created successfully.");
            responseModel.setData(savedManual);
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while creating the practical manual.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel updatePracticalManual(Long manualId, Map<String, Object> updatedFields) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                PracticalManualEntity existingManual = optionalManual.get();

                // Update only the provided fields
                if (updatedFields.containsKey("title")) {
                    existingManual.setTitle((String) updatedFields.get("title"));
                }
                if (updatedFields.containsKey("description")) {
                    existingManual.setDescription((String) updatedFields.get("description"));
                }
                if (updatedFields.containsKey("module_category")) {
                    // Assuming module_category is an Integer in PracticalManualEntity
                    existingManual.setModuleCategory(Long.valueOf((Integer) updatedFields.get("module_category")));
                }
                if (updatedFields.containsKey("updated_by")) {
                    // Assuming updated_by is an Integer in PracticalManualEntity
                    existingManual.setUpdatedBy((Integer) updatedFields.get("updated_by"));
                }

                // Set the manualId and updatedAt from the existing manual
                existingManual.setManualId(manualId);
                existingManual.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                PracticalManualEntity savedManual = practicalManualRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Practical manual updated successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Practical manual not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the practical manual.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel deletePracticalManual(Long manualId, Map<String, Object> deletedFields) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                PracticalManualEntity existingManual = optionalManual.get();

                // Set deleted_at and deleted_by
                existingManual.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                if (deletedFields.containsKey("deleted_by")) {
                    // Assuming deleted_by is an Integer in PracticalManualEntity
                    existingManual.setDeletedBy((Integer) deletedFields.get("deleted_by"));
                }

                PracticalManualEntity savedManual = practicalManualRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Practical manual deleted successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Practical manual not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting the practical manual.");
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
    public ResponseModel getPracticalManualById(Long manualId) {
        ResponseModel responseModel = new ResponseModel();
        Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage("Practical manual retrieved successfully.");
            responseModel.setData(optionalManual.get());
        } else {
            responseModel.setCode(404); // Not Found
            responseModel.setMessage("Practical manual not found.");
        }
        return responseModel;
    }

    @Override
    public Resource downloadManualPdf(Long manualId) {
        Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            PracticalManualEntity manual = optionalManual.get();
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
            throw new EntityNotFoundException("Practical manual not found");
        }
    }
}

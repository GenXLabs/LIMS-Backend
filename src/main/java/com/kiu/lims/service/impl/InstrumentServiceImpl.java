package com.kiu.lims.service.impl;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.InstrumentRepository;
import com.kiu.lims.service.InstrumentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private final InstrumentRepository instrumentRepository;

    @Override
    public ResponseModel getAllInstrument() {
        ResponseModel responseModel = new ResponseModel();
        List<InstrumentEntity> instrumentEntityList = instrumentRepository.findAll();;

        if (instrumentEntityList.isEmpty()) {
            responseModel.setCode(204); // No Content
            responseModel.setMessage("No instrument found.");
        } else {
            responseModel.setCode(200);
            responseModel.setMessage("data retrieved successfully.");
            responseModel.setData(instrumentEntityList);
        }

        return responseModel;
    }

    @Override
    public ResponseModel createInstrument(InstrumentEntity manual, MultipartFile file) {
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
            InstrumentEntity savedManual = instrumentRepository.save(manual);

            responseModel.setCode(201); // Created
            responseModel.setMessage(" created successfully.");
            responseModel.setData(savedManual);
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while creating the instrument.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }


    @Override
    public ResponseModel updateInstrument(Long manualId, Map<String, Object> updatedFields){
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<InstrumentEntity> optionalManual = instrumentRepository.findById(manualId);
            if (optionalManual.isPresent()) {
            InstrumentEntity existingManual = optionalManual.get();

                // Update only the provided fields
                if (updatedFields.containsKey("title")) {
                    existingManual.setTitle((String) updatedFields.get("title"));
                }
                if (updatedFields.containsKey("description")) {
                    existingManual.setDescription((String) updatedFields.get("description"));
                }

                if (updatedFields.containsKey("updated_by")) {
                    // Assuming updated_by is an Integer in PracticalManualEntity
                    existingManual.setUpdateBy((Integer) updatedFields.get("updated_by"));
                }

                // Set the manualId and updatedAt from the existing manual
                existingManual.setManualId(manualId);
                existingManual.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                InstrumentEntity savedManual = instrumentRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage(" updated successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage(" not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating .");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel deleteInstrument(Long manualId, Map<String, Object> deletedFields) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<InstrumentEntity> optionalManual = instrumentRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                InstrumentEntity existingManual = optionalManual.get();

                // Set deleted_at and deleted_by
                existingManual.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                if (deletedFields.containsKey("deleted_by")) {
                    // Assuming deleted_by is an Integer in PracticalManualEntity
                    existingManual.setDeletedBy((Integer) deletedFields.get("deleted_by"));
                }

                InstrumentEntity savedManual = instrumentRepository.save(existingManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage(" deleted successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage(" not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting .");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }
    @Override
    public ResponseModel getAllInstrumentById(Long manualId) {
        ResponseModel responseModel = new ResponseModel();
        Optional<InstrumentEntity> optionalManual = instrumentRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage(" retrieved successfully.");
            responseModel.setData(optionalManual.get());
        } else {
            responseModel.setCode(404); // Not Found
            responseModel.setMessage(" not found.");
        }
        return responseModel;
    }

    @Override
    public Resource downloadInstrumentPdf(Long manualId) {
        Optional<InstrumentEntity> optionalManual = instrumentRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            InstrumentEntity manual = optionalManual.get();
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
            throw new EntityNotFoundException("instrument not found");
        }
    }


}


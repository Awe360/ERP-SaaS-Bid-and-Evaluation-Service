package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.response.DocumentMetadata;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.DocumentMapper;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.repository.DocumentRepository;
import com.saas.projectDelivery.utility.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper documentMapper;

    public Document upload(UUID tenantId, MultipartFile file) throws IOException {
        Document document = documentMapper.toDocumentEntity(tenantId, file);
        if (document == null) {
            return null;
        }
        return repository.save(document);
    }
    public byte[] download(UUID tenantId, UUID documentId) {
        Document doc = findByTenantIdAndId(tenantId, documentId);
        return FileUtil.decompressFile(doc.getFileBytes());
    }

    public DocumentMetadata getDocumentMetadata(UUID tenantId, UUID documentId) {
        Document doc = findByTenantIdAndId(tenantId, documentId);
        return new DocumentMetadata(doc.getFileName(), doc.getFileType());
    }

    private Document findByTenantIdAndId(UUID tenantId, UUID documentId) {
        return repository.findByTenantIdAndId(tenantId, documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

}
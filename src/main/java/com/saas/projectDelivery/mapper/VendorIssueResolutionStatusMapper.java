package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.VendorIssueResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.VendorIssueResolutionStatusResponse;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.VendorIssueResolutionStatus;
import com.saas.projectDelivery.model.VendorKeyIssue;
import com.saas.projectDelivery.model.lookup.ResolutionStatus;
import com.saas.projectDelivery.repository.VendorKeyIssueRepository;
import com.saas.projectDelivery.repository.lookup.ResolutionStatusRepository;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VendorIssueResolutionStatusMapper {

    private final VendorKeyIssueRepository vendorKeyIssueRepo;
    private final ResolutionStatusRepository resolutionStatusRepo;
    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public VendorIssueResolutionStatus toEntity(UUID tenantId, VendorIssueResolutionStatusRequest req, Document document) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        VendorKeyIssue issue = vendorKeyIssueRepo.findByTenantIdAndId(tenantId, req.getVendorKeyIssueId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor key issue not found"));

        ResolutionStatus status = resolutionStatusRepo.findByTenantIdAndId(tenantId, req.getResolutionStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Resolution status not found"));

        VendorIssueResolutionStatus entity = new VendorIssueResolutionStatus();
        entity.setTenantId(tenantId);
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setContractorId(req.getContractorId());
        entity.setVendorKeyIssue(issue);
        entity.setResolutionStatus(status);
        entity.setRemark(req.getRemark());
        entity.setResolutionDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, VendorIssueResolutionStatus entity, VendorIssueResolutionStatusRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        VendorKeyIssue issue = vendorKeyIssueRepo.findByTenantIdAndId(tenantId, req.getVendorKeyIssueId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor key issue not found"));

        ResolutionStatus status = resolutionStatusRepo.findByTenantIdAndId(tenantId, req.getResolutionStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Resolution status not found"));

        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setContractorId(req.getContractorId());
        entity.setVendorKeyIssue(issue);
        entity.setResolutionStatus(status);
        entity.setRemark(req.getRemark());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public VendorIssueResolutionStatusResponse toResponse(VendorIssueResolutionStatus entity) {
        VendorIssueResolutionStatusResponse res = new VendorIssueResolutionStatusResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProjectId(entity.getProjectId());
        res.setSubProjectId(entity.getSubProjectId());
        res.setContractorId(entity.getContractorId());
        res.setVendorKeyIssueId(entity.getVendorKeyIssue().getId());
        res.setResolutionStatusId(entity.getResolutionStatus().getId());
        res.setResolutionStatusName(entity.getResolutionStatus().getStatusName());
        res.setRemark(entity.getRemark());

        if (entity.getResolutionDocument() != null) {
            Document doc = entity.getResolutionDocument();
            res.setResolutionDocumentId(doc.getId());
            res.setResolutionFileName(doc.getFileName());
            res.setResolutionFileType(doc.getFileType());
            res.setResolutionFileSize(doc.getFileSize());
        }

        return res;
    }
}
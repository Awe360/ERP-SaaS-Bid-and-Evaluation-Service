package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.VendorKeyIssueRequest;
import com.saas.projectDelivery.dto.response.VendorKeyIssueResponse;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.VendorKeyIssue;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VendorKeyIssueMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public VendorKeyIssue toEntity(UUID tenantId, VendorKeyIssueRequest req, Document document) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        VendorKeyIssue entity = new VendorKeyIssue();
        entity.setTenantId(tenantId);
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setSupplierId(req.getSupplierId());
        entity.setSupplierIssue(req.getSupplierIssue());
        entity.setDateOfClaim(req.getDateOfClaim());
        entity.setComment(req.getComment());
        entity.setVendorIssueDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, VendorKeyIssue entity, VendorKeyIssueRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setSupplierId(req.getSupplierId());
        entity.setSupplierIssue(req.getSupplierIssue());
        entity.setDateOfClaim(req.getDateOfClaim());
        entity.setComment(req.getComment());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());

    }

    public VendorKeyIssueResponse toResponse(VendorKeyIssue entity) {
        VendorKeyIssueResponse res = new VendorKeyIssueResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProjectId(entity.getProjectId());
        res.setSubProjectId(entity.getSubProjectId());
        res.setSupplierId(entity.getSupplierId());
        res.setSupplierIssue(entity.getSupplierIssue());
        res.setDateOfClaim(entity.getDateOfClaim());
        res.setComment(entity.getComment());

        if (entity.getVendorIssueDocument() != null) {
            Document doc = entity.getVendorIssueDocument();
            res.setVendorIssueDocumentId(doc.getId());
            res.setVendorIssueFileName(doc.getFileName());
            res.setVendorIssueFileType(doc.getFileType());
            res.setVendorIssueFileSize(doc.getFileSize());
        }

        return res;
    }
}
package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.ProjectClosingRequest;
import com.saas.projectDelivery.dto.response.ProjectClosingResponse;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.ProjectClosing;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectClosingMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ProjectClosing toEntity(UUID tenantId, ProjectClosingRequest req, Document document) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        ProjectClosing entity = new ProjectClosing();
        entity.setTenantId(tenantId);
        entity.setProgrammeId(req.getProgrammeId());
        entity.setProjectId(req.getProjectId());
        entity.setProjectStatus(req.getProjectStatus());
        entity.setProjectClosed(req.getProjectClosed());
        entity.setClosedDate(req.getClosedDate());
        entity.setProjectEstimateCost(req.getProjectEstimateCost());
        entity.setProjectActualCost(req.getProjectActualCost());
        entity.setRemark(req.getRemark());
        entity.setClosingDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, ProjectClosing entity, ProjectClosingRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        entity.setProgrammeId(req.getProgrammeId());
        entity.setProjectId(req.getProjectId());
        entity.setProjectStatus(req.getProjectStatus());
        entity.setProjectClosed(req.getProjectClosed());
        entity.setClosedDate(req.getClosedDate());
        entity.setProjectEstimateCost(req.getProjectEstimateCost());
        entity.setProjectActualCost(req.getProjectActualCost());
        entity.setRemark(req.getRemark());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public ProjectClosingResponse toResponse(ProjectClosing entity) {
        ProjectClosingResponse res = new ProjectClosingResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProgrammeId(entity.getProgrammeId());
        res.setProjectId(entity.getProjectId());
        res.setProjectStatus(entity.getProjectStatus());
        res.setProjectClosed(entity.isProjectClosed());
        res.setClosedDate(entity.getClosedDate());
        res.setProjectEstimateCost(entity.getProjectEstimateCost());
        res.setProjectActualCost(entity.getProjectActualCost());
        res.setRemark(entity.getRemark());

        if (entity.getClosingDocument() != null) {
            Document doc = entity.getClosingDocument();
            res.setClosingDocumentId(doc.getId());
            res.setClosingFileName(doc.getFileName());
            res.setClosingFileType(doc.getFileType());
            res.setClosingFileSize(doc.getFileSize());
        }

        return res;
    }
}
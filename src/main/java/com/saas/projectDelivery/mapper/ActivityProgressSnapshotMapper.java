package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.ActivityProgressSnapshotRequest;
import com.saas.projectDelivery.dto.response.ActivityProgressSnapshotResponse;
import com.saas.projectDelivery.model.ActivityProgressSnapshot;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActivityProgressSnapshotMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ActivityProgressSnapshot toEntity(UUID tenantId, ActivityProgressSnapshotRequest req, Document document) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        ActivityProgressSnapshot entity = new ActivityProgressSnapshot();
        entity.setTenantId(tenantId);
        entity.setProgrammeId(req.getProgrammeId());
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setActivityId(req.getActivityId());
        entity.setProgressDate(req.getProgressDate());
        entity.setCurrentProgress(req.getCurrentProgress());
        entity.setCritical(req.getCritical());
        entity.setDescription(req.getDescription());
        entity.setActivityProgressDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, ActivityProgressSnapshot entity, ActivityProgressSnapshotRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        entity.setProgrammeId(req.getProgrammeId());
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setActivityId(req.getActivityId());
        entity.setProgressDate(req.getProgressDate());
        entity.setCurrentProgress(req.getCurrentProgress());
        entity.setCritical(req.getCritical());
        entity.setDescription(req.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());

    }

    public ActivityProgressSnapshotResponse toResponse(ActivityProgressSnapshot entity) {
        ActivityProgressSnapshotResponse res = new ActivityProgressSnapshotResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProgrammeId(entity.getProgrammeId());
        res.setProjectId(entity.getProjectId());
        res.setSubProjectId(entity.getSubProjectId());
        res.setActivityId(entity.getActivityId());
        res.setProgressDate(entity.getProgressDate());
        res.setCurrentProgress(entity.getCurrentProgress());
        res.setCritical(entity.getCritical());
        res.setDescription(entity.getDescription());

        if (entity.getActivityProgressDocument() != null) {
            Document doc = entity.getActivityProgressDocument();
            res.setActivityProgressDocumentId(doc.getId());
            res.setActivityProgressFileName(doc.getFileName());
            res.setActivityProgressFileType(doc.getFileType());
            res.setActivityProgressFileSize(doc.getFileSize());
        }

        return res;
    }
}
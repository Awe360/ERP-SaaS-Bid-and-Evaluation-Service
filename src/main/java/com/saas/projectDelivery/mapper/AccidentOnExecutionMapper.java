
package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.AccidentOnExecutionRequest;
import com.saas.projectDelivery.dto.response.AccidentOnExecutionResponse;
import com.saas.projectDelivery.model.AccidentOnExecution;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.lookup.AccidentType;
import com.saas.projectDelivery.service.DocumentService;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccidentOnExecutionMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public AccidentOnExecution toEntity(UUID tenantId, AccidentOnExecutionRequest req, Document document) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());
        AccidentType accidentType = validationUtil.getAccidentTypeById(tenantId, req.getAccidentTypeId());

        AccidentOnExecution entity = new AccidentOnExecution();
        entity.setTenantId(tenantId);
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setAccidentSite(req.getAccidentSite());
        entity.setAccidentLocation(req.getAccidentLocation());
        entity.setAccidentType(accidentType);
        entity.setAccidentDate(req.getAccidentDate());
        entity.setAccidentTime(req.getAccidentTime());
        entity.setCauseOfAccident(req.getCauseOfAccident());
        entity.setDescription(req.getDescription());
        entity.setHasInsurance(req.isHasInsurance());
        entity.setInsuranceCompanyId(req.getInsuranceCompanyId());
        entity.setTreatmentStarted(req.isTreatmentStarted());
        entity.setAttachmentDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, AccidentOnExecution entity, AccidentOnExecutionRequest req) {
        validationUtil.validateProjectExists(entity.getTenantId(), req.getProjectId());
        AccidentType accidentType = validationUtil.getAccidentTypeById(tenantId, req.getAccidentTypeId());


        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setAccidentSite(req.getAccidentSite());
        entity.setAccidentLocation(req.getAccidentLocation());
        entity.setAccidentType(accidentType);
        entity.setAccidentDate(req.getAccidentDate());
        entity.setAccidentTime(req.getAccidentTime());
        entity.setCauseOfAccident(req.getCauseOfAccident());
        entity.setDescription(req.getDescription());
        entity.setHasInsurance(req.isHasInsurance());
        entity.setInsuranceCompanyId(req.getInsuranceCompanyId());
        entity.setTreatmentStarted(req.isTreatmentStarted());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());

    }

    public AccidentOnExecutionResponse toResponse(AccidentOnExecution entity) {
        AccidentOnExecutionResponse res = new AccidentOnExecutionResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProjectId(entity.getProjectId());
        res.setSubProjectId(entity.getSubProjectId());
        res.setAccidentSite(entity.getAccidentSite());
        res.setAccidentLocation(entity.getAccidentLocation());
        res.setAccidentTypeId(entity.getAccidentType().getId());
        res.setAccidentDate(entity.getAccidentDate());
        res.setAccidentTime(entity.getAccidentTime());
        res.setCauseOfAccident(entity.getCauseOfAccident());
        res.setDescription(entity.getDescription());
        res.setHasInsurance(entity.isHasInsurance());
        res.setInsuranceCompanyId(entity.getInsuranceCompanyId());
        res.setTreatmentStarted(entity.isTreatmentStarted());

        if (entity.getAttachmentDocument() != null) {
            Document doc = entity.getAttachmentDocument();
            res.setAttachmentDocumentId(doc.getId());
            res.setAttachmentFileName(doc.getFileName());
            res.setAttachmentFileType(doc.getFileType());
            res.setAttachmentFileSize(doc.getFileSize());
        }

        return res;
    }
}
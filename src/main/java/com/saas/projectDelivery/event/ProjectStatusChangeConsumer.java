//package com.saas.projectDelivery.event;
//
//import com.saas.projectDelivery.dto.eventDto.ChangeProjectStatusDto;
//import com.saas.projectDelivery.enums.Status;
//import com.saas.projectDelivery.model.Project;
//import com.saas.projectDelivery.repository.ProjectRepository;
//import com.saas.projectDelivery.repository.ProjectStatusHistoryRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class ProjectStatusChangeConsumer {
//
//    private final ProjectRepository projectRepository;
//    private final ProjectStatusHistoryRepository projectStatusHistoryRepository;
//
//    @RabbitListener(queues = "change-project-status-queue")
//    public void handleProjectStatusChange(ChangeProjectStatusDto event) {
//        log.info("TEST CONSUMER RECEIVED event: Project {} | {} → {}",
//                event.getProjectId(),
//                event.getPreviousStatus(),
//                event.getNewStatus());
//
//        projectStatusHistoryRepository.findByTenantIdAndId(event.getTenantId(), event.getProjectId())
//                .ifPresentOrElse(
//                        project -> {
//                            Status newStatus = Status.valueOf(event.getNewStatus());
//                            project.setNewStatus(newStatus.toString());
//                            projectStatusHistoryRepository.save(project);
//                            log.info("TEST: Successfully updated project {} to status {}",
//                                    event.getProjectId(), newStatus);
//                        },
//                        () -> log.info("TEST: Project {} not found in this service (expected in test mode) – event ignored safely",
//                                event.getProjectId())
//                );
//    }
//}




//package com.saas.projectDelivery.event;
//
//import com.saas.projectDelivery.config.RabbitMQProperties;
//import com.saas.projectDelivery.dto.eventDto.ChangeProjectStatusDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class ProjectStatusChangeConsumer {
//
//    private final RabbitMQProperties properties;
//
//    // You can inject services here if needed, e.g.:
//    // private final ProjectService projectService;
//    // private final ProjectStatusHistoryRepository historyRepo;
//
//    @RabbitListener(queues = "#{rabbitMQProperties.changeProjectStatusQueue()}")
//    public void handleProjectStatusChange(ChangeProjectStatusDto dto) {
//        log.info("Received project status change event | Project ID: {} | Tenant: {} | {} → {}",
//                dto.getProjectId(),
//                dto.getTenantId(),
//                dto.getPreviousStatus(),
//                dto.getNewStatus());
//
//        // Examples:
//        // - Update project status in DB
//        // - Trigger notifications
//        // - Sync with other services
//        // - Audit logging
//
//        // Example placeholder logic:
//        // projectService.updateProjectStatus(dto.getProjectId(), dto.getNewStatus());
//
//        log.info("Successfully processed project status change event for project {}", dto.getProjectId());
//    }
//}
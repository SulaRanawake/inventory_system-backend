package com.inventory.service;

import com.inventory.entity.AuditAction;
import com.inventory.entity.AuditLog;
import com.inventory.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void log(
            AuditAction action,
            String entityName,
            Long entityId,
            String performedBy,
            String description
    ) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setPerformedBy(performedBy);
        log.setDescription(description);

        auditLogRepository.save(log);
    }
}

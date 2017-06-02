package com.starter.model.envers;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditingRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {

        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;

        String userName = "System";

        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                userName = authentication.getName();
            }
        }

        auditedRevisionEntity.setUser(userName);
    }
}

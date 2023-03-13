package anton.example.watermelon.dto;


import anton.example.watermelon.entity.AbstractAuditable;

import java.io.Serializable;

public record AuditEntity(
        UserActionType actionType,
        AbstractAuditable entity
) implements Serializable { }

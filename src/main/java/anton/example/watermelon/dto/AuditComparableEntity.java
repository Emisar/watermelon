package anton.example.watermelon.dto;


import anton.example.watermelon.entity.AbstractAuditable;

public record AuditComparableEntity(
        UserActionType actionType,
        AbstractAuditable oldEntity,
        AbstractAuditable newEntity
) { }

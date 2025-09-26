package com.eva.event_service.mapper;

import com.eva.event_service.dto.EventRequestDto;
import com.eva.event_service.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    Event toEntity(EventRequestDto eventRequestDto);
}

package com.tiago.boliche.model.frame;

import com.tiago.boliche.entity.Frame;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFrameMapper {

    FrameResponse toResponse(Frame frame);
}

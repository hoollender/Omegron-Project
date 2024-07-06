package com.omegron.model.enums.converters;

import com.omegron.model.enums.TransmissionTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransmissionTypeEnumConverter implements Converter<String, TransmissionTypeEnum> {

    @Override
    public TransmissionTypeEnum convert(String source) {
        return TransmissionTypeEnum.valueOf(source.replaceAll(" ", ""));
    }
}
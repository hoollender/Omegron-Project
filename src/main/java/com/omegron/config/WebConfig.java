package com.omegron.config;

import com.omegron.model.enums.converters.ManufacturersEnumConverter;
import com.omegron.model.enums.converters.TransmissionTypeEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ManufacturersEnumConverter manufacturersEnumConverter;
    private final TransmissionTypeEnumConverter transmissionTypeEnumConverter;

    public WebConfig(ManufacturersEnumConverter manufacturersEnumConverter, TransmissionTypeEnumConverter transmissionTypeEnumConverter) {
        this.manufacturersEnumConverter = manufacturersEnumConverter;
        this.transmissionTypeEnumConverter = transmissionTypeEnumConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(manufacturersEnumConverter);
        registry.addConverter(transmissionTypeEnumConverter);
    }
}
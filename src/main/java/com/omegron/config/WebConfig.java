package com.omegron.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omegron.model.enums.converters.ManufacturersEnumConverter;
import com.omegron.model.enums.converters.TransmissionTypeEnumConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;
    private final ManufacturersEnumConverter manufacturersEnumConverter;
    private final TransmissionTypeEnumConverter transmissionTypeEnumConverter;
    private final LocaleChangeInterceptor localeChangeInterceptor;

    public WebConfig(ObjectMapper objectMapper, ManufacturersEnumConverter manufacturersEnumConverter, TransmissionTypeEnumConverter transmissionTypeEnumConverter, LocaleChangeInterceptor localeChangeInterceptor) {
        this.objectMapper = objectMapper;
        this.manufacturersEnumConverter = manufacturersEnumConverter;
        this.transmissionTypeEnumConverter = transmissionTypeEnumConverter;
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(manufacturersEnumConverter);
        registry.addConverter(transmissionTypeEnumConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }
}
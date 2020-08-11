package com.opentext.adf.common.config;

import com.opentext.adf.common.exception.AdfException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.UUID;

/**
 * Beans defined for Common Config.
 *
 * @author opentext
 */
@Configuration
public class AdfConfig {
    /**
     * Gets a logger in Spring created constructor.
     *
     * @param injectionPoint Spring injectionPoint
     * @return Logger
     */
    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }

    /**
     * Gets a message source based on classpath:i18n/messages.
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Gets LocalValidatorFactoryBean to be used in spring context.
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * Returns a new instance of class if it has visible default constructor.
     *
     * @param clazz class
     * @param <T>   type of class
     * @return object of class
     */
    public <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new AdfException("Error in creating new instance", ex);
        }
    }

    /**
     * This method returns an uuid.
     * @return uuid
     */
    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}

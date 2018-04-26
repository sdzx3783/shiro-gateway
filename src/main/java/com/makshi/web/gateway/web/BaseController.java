package com.makshi.web.gateway.web;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    private MessageSource messageSource;

    protected String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String key, Object[] args, String defaultMsg) {
        return messageSource.getMessage(key, args, defaultMsg, LocaleContextHolder.getLocale());
    }
}

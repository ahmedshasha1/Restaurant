package com.resturant.service.bundlemessage;

import com.resturant.dto.exception.BundleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleMessageService {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    public void BundleMessagesService(@Qualifier("messages") ResourceBundleMessageSource messageSource){
        this.messageSource = messageSource;
    }

    public static BundleMessage getMessages(String key){
        return new BundleMessage(
                messageSource.getMessage(key, null, new Locale("en")),
                messageSource.getMessage(key, null, new Locale("ar"))
        );
    }
}

package com.kiu.lims.event.listener;

import com.kiu.lims.event.RegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

    }
}

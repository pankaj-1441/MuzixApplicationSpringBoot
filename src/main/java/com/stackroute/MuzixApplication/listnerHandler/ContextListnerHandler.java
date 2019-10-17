package com.stackroute.MuzixApplication.listnerHandler;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.service.TrackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextListnerHandler implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TrackServices trackServices;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Applivation Context Refreshes" + event.getSource());
        Track track=new Track();
        track.setTrackName("ApplicationListner");
        track.setTrackComments("Generated by contextRefreshedEvent");

        try {
            trackServices.saveTrack(track);
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
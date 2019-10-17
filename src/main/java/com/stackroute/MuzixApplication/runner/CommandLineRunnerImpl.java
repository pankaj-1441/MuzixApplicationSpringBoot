package com.stackroute.MuzixApplication.runner;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.service.TrackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private TrackServices trackServices;
    @Override
    public void run(String... args) throws Exception {
        Track track=new Track();
        track.setTrackName("CommandLineRunner");
        track.setTrackComments("Gnererated by CommandLineRunner");

        trackServices.saveTrack(track);

    }
}

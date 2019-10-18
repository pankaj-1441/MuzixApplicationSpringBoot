package com.stackroute.MuzixApplication.runner;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.service.TrackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.properties")
public class ApplicationRunnerHandlerImpl implements ApplicationRunner {
    @Autowired
    private TrackServices trackServices;
    @Autowired
    private Environment env;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Track track=new Track();
        track.setTrackId(2);
        track.setTrackName(env.getProperty("Track.trackName"));
        track.setTrackComments(env.getProperty("Track.trackComments"));

        trackServices.saveTrack(track);

    }
}

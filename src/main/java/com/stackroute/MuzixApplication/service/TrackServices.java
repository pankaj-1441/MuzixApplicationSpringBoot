package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;

import java.util.List;

 public interface TrackServices {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks() throws TrackNotFoundException;
    public  Track updateTrackComment(Track track) throws TrackNotFoundException;
    public Track deleteTrack(Track track) throws TrackNotFoundException;

}

package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackServicesImpl implements TrackServices {
   private TrackRepository trackRepository;
    @Autowired
    public TrackServicesImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("User already exists.");
        }
        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("User already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrackComment(Track track) throws TrackNotFoundException{
        if(!trackRepository.existsById(track.getTrackId())){
            throw new TrackNotFoundException("Track not found to be deleted");
        }
        Track tracktoUpdate=trackRepository.getOne(track.getTrackId());
        tracktoUpdate.setTrackComments(track.getTrackComments());
        trackRepository.save(tracktoUpdate);
        return tracktoUpdate;
    }

    @Override
    public void deleteTrack(Track track) throws TrackNotFoundException{
        if(!trackRepository.existsById(track.getTrackId())){
            throw new TrackNotFoundException("Track not found to be deleted");
        }
        trackRepository.delete(track);

    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {
        List<Track> tracks=trackRepository.getTrackByName(name);
        if(tracks.size()==0){
            throw new TrackNotFoundException("No track was found");
        }
        return tracks;
    }


}

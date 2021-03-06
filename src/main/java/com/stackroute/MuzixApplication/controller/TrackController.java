package com.stackroute.MuzixApplication.controller;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import com.stackroute.MuzixApplication.service.TrackServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="/api/v1/")
public class TrackController {
    @Autowired
    //@Qualifier("dummyTrackService")
    private TrackServices trackServices;

    public TrackController(TrackServices trackServices) {
        this.trackServices = trackServices;
    }

    @ApiOperation(value = "Saves the entered track into the database")
    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;

        trackServices.saveTrack(track);
        responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "Finds all the tracks in the database")
    @GetMapping("tracks")
    public  ResponseEntity<?> getAllUsers() throws TrackNotFoundException{
        ResponseEntity responseEntity;

        responseEntity = new ResponseEntity<List<Track>>(trackServices.getAllTracks(), HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Updates the given track in the database")
    @PutMapping("track")
    public ResponseEntity<?> updateTrackComment(@RequestBody Track track) throws TrackNotFoundException {
        ResponseEntity responseEntity;

        trackServices.updateTrackComment(track);
        responseEntity=new ResponseEntity<String>("Successfully Updated Comment",HttpStatus.ACCEPTED);

        return  responseEntity;
    }

    @ApiOperation(value = "Deletes a track in the database")
    @DeleteMapping("track")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track) throws TrackNotFoundException {
        ResponseEntity responseEntity;

        trackServices.deleteTrack(track);
        responseEntity=new ResponseEntity<String>("Successfully Deleted", HttpStatus.GONE);

        return  responseEntity;
    }


    @ApiOperation(value = "Finds the entered track in the database")
    @GetMapping("track/{name}")
    public  ResponseEntity<?> getTrackByName(@PathVariable String name) throws TrackNotFoundException {
        ResponseEntity responseEntity;

        responseEntity = new ResponseEntity<List<Track>>(trackServices.getTrackByName(name), HttpStatus.OK);

        return responseEntity;
    }
}

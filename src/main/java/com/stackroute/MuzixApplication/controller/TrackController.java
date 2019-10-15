package com.stackroute.MuzixApplication.controller;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import com.stackroute.MuzixApplication.service.TrackServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
public class TrackController {
    @Autowired
    private TrackServices trackServices;

    public TrackController(TrackServices trackServices) {
        this.trackServices = trackServices;
    }

    @ApiOperation(value = "Saves the entered track into the database")
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackServices.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Finds all the tracks in the database")
    @GetMapping("/get")
    public  ResponseEntity<?> getAllUsers(){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(trackServices.getAllTracks(), HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Updates the given track in the database")
    @PutMapping("/update")
    public ResponseEntity<?> updateTrackComment(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackServices.updateTrackComment(track);
            responseEntity=new ResponseEntity<String>("Successfully Updated Comment",HttpStatus.OK);
        }
        catch (TrackNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }

    @ApiOperation(value = "Deletes a track in the database")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
           trackServices.deleteTrack(track);
           responseEntity=new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
        }
        catch (TrackNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }


    @ApiOperation(value = "Finds the entered track in the database")
    @GetMapping("/getByName/{name}")
    public  ResponseEntity<?> getTrackByName(@PathVariable String name){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(trackServices.getTrackByName(name), HttpStatus.OK);
        }
        catch (TrackNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}

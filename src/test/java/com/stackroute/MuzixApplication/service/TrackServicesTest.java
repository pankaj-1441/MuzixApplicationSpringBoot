package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServicesTest {

        private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServicesImpl trackServices;
    List<Track> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackName("Sunflower");
        track.setTrackId(101);
        track.setTrackComments("post malone");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackServices.saveTrack(track);
        Assert.assertEquals(track,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
      
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedUser = trackServices.saveTrack(track);
        System.out.println("savedUser" + savedUser);
        Assert.assertEquals(track,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void getAllUser() throws TrackNotFoundException {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackServices.getAllTracks();
        Assert.assertEquals(list,userlist);
    }





}

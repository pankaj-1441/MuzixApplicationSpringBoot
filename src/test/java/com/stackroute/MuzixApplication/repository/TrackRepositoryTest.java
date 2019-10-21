package com.stackroute.MuzixApplication.repository;


import com.stackroute.MuzixApplication.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(101);
        track.setTrackName("khairiyat");
        track.setTrackComments("Romantic");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
     trackRepository.save(track);
     Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(101,fetchUser.getTrackId());

    }

    @Test
    public void testSaveUserFailure(){
        Track testUser = new Track(101,"khairiyat","Romantic");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testUser, track);
    }

    @Test
    public void testGetAllUser(){
        Track u = new Track(101,"khairiyat","Romantic");
        Track u1 = new Track(12,"Rockstar","post malone");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("khairiyat",list.get(0).getTrackName());




    }


}

package com.stackroute.MuzixApplication.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.service.TrackServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackServices trackServices;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setTrackId(0);
        track.setTrackName("Bad Liar");
        track.setTrackComments("Rocking");
        list = new ArrayList();
        list.add(track);
    }

    @Test
    public void saveUser() throws Exception {
        when(trackServices.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
      @Test
     public void saveUserFailure() throws TrackAlreadyExistsException {
        when(trackServices.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
          try {
              mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
              .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                      .andExpect(MockMvcResultMatchers.status().isConflict())
                      .andDo(MockMvcResultHandlers.print());
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

    @Test
    public void getAllUser() throws Exception {
        when(trackServices.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}

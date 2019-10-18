package com.stackroute.MuzixApplication.repository;

import com.stackroute.MuzixApplication.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    //@Query(value = "select * from TRACK where TRACK_NAME=?1", nativeQuery = true)
    public List<Track> getTrackByName(String name);
}

package com.tunesmusic.repository;

import com.tunesmusic.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    @Query(value = "select * from Artist order by follower desc LIMIT 5" ,nativeQuery = true)
    List<Artist> getList5Artist();

    @Query(value = "select * from user_following where user_id = ?1",nativeQuery = true)
    List<Artist> getArtistByUserId(Long userId);

    @Query(value = "select * from artist where id_user = ?1",nativeQuery = true)
    Artist getArtistByIdUser(Long idUser);

    @Query(value = "select * from artist where artist_name like %?1%",nativeQuery = true)
    List<Artist> findArtistByArtistName(String artistName);


}

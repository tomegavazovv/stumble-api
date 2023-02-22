package com.example.stumble.repositories;

import com.example.stumble.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "WITH Distance AS (" +
            "  SELECT *, acos(sin(radians(:Lat)) * sin(radians(lat)) + cos(radians(:Lat)) * cos(radians(lat)) * cos(radians(:Lon) - radians(lon))) * 6371 AS Distance" +
            "  FROM users WHERE id != :id  )" +
            "SELECT * " +
            "FROM Distance " +
            "LEFT JOIN blocked_users bu ON Distance.ID = bu.blocked_user_id AND bu.user_id = :id " +
            "WHERE bu.blocked_user_id IS NULL AND Distance <= 1500000000000000 " +
            "ORDER BY Distance",
            nativeQuery = true)
    List<User> findNearbyUsers(@Param("id") long id, @Param("Lat") double lat, @Param("Lon") double Lon);
}

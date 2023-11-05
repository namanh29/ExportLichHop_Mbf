package com.example.shinnytest.repository;

import com.example.shinnytest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    @Query(value = "SELECT * FROM user e " +
            "WHERE (:statusId = -1 or (:statusId > 0 and :statusId = e.status_id ))" +
            "AND (:positionId = -1 or (:positionId >0 and :positionId = e.position_id ))"+
            "AND (:departmentId=-1 or (:departmentId >0 and :departmentId = e.department_id))"+
            "AND (:username = '' or :username is null  or (:username > null and :username = e.name))"
            , nativeQuery = true)
    List<User> listAll(@Param("statusId") Long statusId,
                       @Param("positionId") Long positionId,
                       @Param("departmentId") Long departmentId,
                       @Param("username") String username
            );
}

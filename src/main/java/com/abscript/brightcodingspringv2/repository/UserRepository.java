package com.abscript.brightcodingspringv2.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abscript.brightcodingspringv2.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    @Query(value = "select * from users",nativeQuery = true)
    Page<UserEntity> findAllUsers(org.springframework.data.domain.Pageable pageableRequest);
    //@Query(value = "select * from users u WHERE (u.first_name=?1 OR u.last_name=?1) AND email_verification_status=?2",nativeQuery = true)
    //Page<UserEntity> findAllUsersByCreteria(org.springframework.data.domain.Pageable pageableRequest,String search, int status);
    
    @Query(value = "select * from users u WHERE (u.first_name=:search OR u.last_name=:search) AND email_verification_status=:status",nativeQuery = true)
    Page<UserEntity> findAllUsersByCreteria(org.springframework.data.domain.Pageable pageableRequest,@Param("search") String search,@Param("status") int status);
    
}

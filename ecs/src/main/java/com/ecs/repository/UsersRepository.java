package com.ecs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecs.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>,PagingAndSortingRepository<Users,Integer> {

}

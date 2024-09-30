package com.manvitha.database_per_tenant.repository;

import com.manvitha.database_per_tenant.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}

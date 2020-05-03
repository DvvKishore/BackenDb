package com.example.backenddb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backenddb.pojo.CountryPojo;
@Repository
public interface CountryDao extends JpaRepository<CountryPojo, Integer>{

}

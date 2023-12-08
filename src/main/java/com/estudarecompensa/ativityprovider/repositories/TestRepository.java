package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudarecompensa.ativityprovider.entities.Test;

// this class is a interface for our repository
// we needs extends this class to JPARepository<User, Long> , passed the class entitie and the type of primary key
public interface TestRepository extends JpaRepository<Test, Long>
{
    // we do not need  implements the class because the JPARepository has a default implementation to
    // this interface
    
    
}
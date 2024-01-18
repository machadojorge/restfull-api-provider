package com.estudarecompensa.ativityprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public interface AnaliticDaoRepository extends JpaRepository<AnaliticDao, Long> {
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.simplon.springboot.simplecrud2.repository;

import co.simplon.springboot.simplecrud2.model.Film;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vanessa
 */
public interface FilmRepository extends JpaRepository<Film, Long>{
    
}

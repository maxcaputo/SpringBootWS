/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.dao;

import com.acn.nemo.entity.Regions;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author massimo
 */
public interface RegionDao extends CrudRepository<Regions, Long> {
    
    /**
     * Retrive All region by Name
     * @param regionName
     * @return List {Regions}
     */
    
    //Query JPQL
    //@Query(name = "Regions.findByRegionName", value = "SELECT r FROM Regions r WHERE r.regionName = ?1")
    //Query Native
    @Query(name = "Regions.findByRegionName", value = "SELECT * FROM Regions r WHERE r.REGION_NAME = ?1", nativeQuery = true)
    public List<Regions> getRegionName(String regionName); 
    
}

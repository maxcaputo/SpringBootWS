/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.services;

import com.acn.nemo.entity.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acn.nemo.dao.RegionDao;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 *
 * @author massimo
 */
@Service
public class RegionService {

    @Autowired
    private RegionDao regionDao;

    /**
     * Restituisce la lista di tutte le Regions
     * @return 
     */
    public Iterable<Regions> getAllRegion() {
        return regionDao.findAll();
    }
    

    /**
     * Data la primary-key (id)
     * Ritorna la Regions corrispondente se esiste
     * @param id
     * @return 
     */
    public Regions getSingleRegion(String id) {
        Optional<Regions> region = regionDao.findById(Long.valueOf(id));
        
        if ( region.isPresent()){
            return region.get();
        }
        return null;
    }

    /**
     * Crea una Regions a partire dal Body della request
     * @param regions 
     */
    public void createRegions(Regions regions) {
        regionDao.save(regions);
    }
    
    public boolean verifyRegions(Regions regions){
        return regionDao.existsById(regions.getRegionId());
    }
    
    /**
     * Data la primary-key id aggiorna la corrispondente Region 
     * @param regions
     * @param id 
     * @return  
     */
    public Regions updateRegionsForId(Regions regions, String id) {
        if( regionDao.existsById(Long.valueOf(id))){
            return regionDao.save(regions);
        }
        return null;
    }

    /**
     * Data la primary-key id cancella la corrispondente Region 
     * @param id 
     * @return  
     */
    public boolean deleteRegionForId(String id) {
        
        if( regionDao.existsById(Long.valueOf(id)) ){
            regionDao.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }
    
    
    /**
     * 
     * @param region 
     */
    public boolean deleteRegion(Regions region){
        if( regionDao.existsById(region.getRegionId())){
            regionDao.delete(region);
            return true;
        }
        return false;
    }
    
    private PageRequest gotoPage(int page){
        PageRequest request = PageRequest.of(page, 1, Sort.Direction.DESC, "regionId");
        return request;
    }
   
    public List<Regions> getRegionByName(String regionName) {
       return regionDao.getRegionName(regionName);
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acn.nemo.controller;

import com.acn.nemo.entity.Regions;
import com.acn.nemo.services.RegionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author massimo
 */
@RestController
@RequestMapping("/ws")
public class RegionController {
    
    @Autowired
    private RegionService serviceRegion;
    
    //@RequestMapping(method = RequestMethod.GET, value = "/regions/all")
    @GetMapping(path = "/regions/all")
    public @ResponseBody ResponseEntity<List<Regions>> getAllRegions(){
         List<Regions> listRegions = (List<Regions>) serviceRegion.getAllRegion();
         if( listRegions.isEmpty()){
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         
         listRegions.forEach(System.out :: println);
          return new ResponseEntity<>(listRegions, HttpStatus.OK);
         
    }
    
    @GetMapping(path ="/regions/{id}")
    public @ResponseBody ResponseEntity<Regions> getRegionsId(@PathVariable("id") String id){
        Regions regions = serviceRegion.getSingleRegion(id);
        if( regions == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(regions,HttpStatus.OK);
    }
    
    
     @GetMapping(path ="/regions/name/{name}")
    public @ResponseBody ResponseEntity<List<Regions>> getRegionsByName(@PathVariable("name") String regionName){
        List<Regions> list = serviceRegion.getRegionByName(regionName);
       
        if( list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    
    @PostMapping(path = "/regions")
    public ResponseEntity<Void> createRegions(@Valid @RequestBody Regions regions){
        
        if( serviceRegion.verifyRegions(regions) ){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        serviceRegion.createRegions(regions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping(path="/regions/{id}")
    public ResponseEntity<Void> updateRegions(@Valid @RequestBody Regions regions, @PathVariable("id") String id){
        if( serviceRegion.updateRegionsForId(regions,id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.UPGRADE_REQUIRED);
    }
    
    @DeleteMapping(path="/regions/{id}")
    public ResponseEntity<Void> deleteRegionsForId(@PathVariable("id") String id){
        if ( serviceRegion.deleteRegionForId(id) ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
    
    @DeleteMapping(path="/regions/region")
    public ResponseEntity<Void> deleteRegion(@RequestBody Regions region){
        try{
            if( serviceRegion.deleteRegion(region)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);                
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

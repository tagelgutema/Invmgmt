package com.inv.invmgmt.settings.repositories;


import com.inv.invmgmt.settings.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository  extends JpaRepository<Region,Integer>  {


    @Query(value= "select r  from  Region r where " +
            "r.regionName LIKE %?1% or r.regionDescription LIKE %?1%")
    List<Region> searchByRegion(String keyword);


  /*  @Query(value="SELECT r FROM Region r WHERE CONCAT(r.regionId or r.regionName or r.regionDescription) LIKE %?1%")
    List<Region> searchByRegion(String keyword);
*/
}


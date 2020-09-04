package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Region;
import com.example.eatgo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionSerivce;

    @GetMapping("/regions")
    public List<Region> list(){
        List<Region> regions = regionSerivce.getRegions();

        return regions ;
    }
}

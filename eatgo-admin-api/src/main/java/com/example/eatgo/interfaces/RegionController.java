package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Region;
import com.example.eatgo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/regions")
    public ResponseEntity<?> create(
            @RequestBody Region resource
    ) throws URISyntaxException {
        String name = resource.getName();

        Region region = regionSerivce.addRegion(name);

        String uri = "/regions/" + region.getId();
        return ResponseEntity.created(new URI(uri)).body("{}");
    }
}

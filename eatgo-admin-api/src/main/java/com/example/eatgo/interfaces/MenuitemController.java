package com.example.eatgo.interfaces;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.service.MenuitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuitemController {

    @Autowired
    private MenuitemService menuitemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){

        menuitemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }
}
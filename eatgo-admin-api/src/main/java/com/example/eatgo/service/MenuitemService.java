package com.example.eatgo.service;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  MenuitemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuitemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        // TODO : bulk Update
        for(MenuItem menuItem : menuItems) {
            update(restaurantId, menuItem);
        }
    }

    public void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            // TODO : delete
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }

        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItems(Long restaurantId){
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }
}

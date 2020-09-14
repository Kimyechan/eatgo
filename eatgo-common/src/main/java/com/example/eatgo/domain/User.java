package com.example.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Long level;

    @NotNull
    private String password;

    private Long restaurantId;

    public boolean isAdmin() {
        return level >= 100L;
    }

    public boolean isActive() {
        return this.level > 0;
    }

    public void deactive() {
        this.level = 0L;
    }

    public Boolean isRestaurantOwner() {
        return this.level == 50L;
    }

    public void setRestaurantId(Long restaurantId) {
        this.level = 50L;
        this.restaurantId = restaurantId;
    }
}

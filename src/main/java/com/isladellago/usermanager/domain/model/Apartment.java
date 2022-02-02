package com.isladellago.usermanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apartment")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class Apartment {

    @Id
    @Column(name = "apartment_id")
    private String apartmentId;

    @OneToMany(mappedBy = "apartment")
    private List<User> users;
}

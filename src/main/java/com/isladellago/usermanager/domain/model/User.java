package com.isladellago.usermanager.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "creation_date")
    @JsonIgnore
    private final Date creationDate = new Date();

    @Transient
    @JsonIgnore
    private boolean enabled = true;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}

package com.soundlabz.invoices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundlabz.invoices.security.UserAuthority;
import com.soundlabz.invoices.security.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(updatable = false)
    private Long id;

    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Transient
    private String newPassword;

    @Transient
    private long expires;

    @NotNull
    private boolean accountExpired;

    @NotNull
    private boolean accountLocked;

    @NotNull
    private boolean credentialsExpired;

    @NotNull
    private boolean accountEnabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserAuthority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private Set<Client> client;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private UserCompany userCompany;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pk.user")
    @JsonIgnore
    public Set<UserCurrency> userCurrencies = new HashSet<UserCurrency>(0);

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, Date expires) {
        this.username = username;
        this.expires = expires.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getNewPassword() {
        return newPassword;
    }

    @JsonProperty
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Set<UserRole> getRoles() {
        Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
        if (authorities != null) {
            for (UserAuthority authority : authorities) {
                roles.add(UserRole.valueOf(authority));
            }
        }
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        for (UserRole role : roles) {
            grantRole(role);
        }
    }

    public void grantRole(UserRole role) {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        authorities.add(role.asAuthorityFor(this));
    }

    private void revokeRole(UserRole role) {
        if (authorities != null) {
            authorities.remove(role.asAuthorityFor(this));
        }
    }

    public boolean hasRole(UserRole role) {
        return authorities.contains(role.asAuthorityFor(this));
    }

    //implementations from usedetails class
    @Override
    @JsonIgnore
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !accountEnabled;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }

    public UserCompany getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(UserCompany userCompany) {
        this.userCompany = userCompany;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + getUsername();
    }

    public Set<UserCurrency> getUserCurrencies() {
        return userCurrencies;
    }

    public void setUserCurrencies(Set<UserCurrency> userCurrencies) {
        this.userCurrencies = userCurrencies;
    }
}

package com.Applicatoin_Blog_demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId;
    private String UserName;
    private String UserEmail;

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", Password='" + Password + '\'' +
                ", About='" + About + '\'' +
                '}';
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> autorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return autorities;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return this.UserEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public User() {
    }

    private String Password;

    public User(Long userId, String userName, String userEmail, String password, String about) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        Password = password;
        About = about;
    }

    private String About;

    //mapping one to many relation with Post


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    public List<Post> posts=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns=@JoinColumn(name = "User",referencedColumnName = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "Role",referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
}

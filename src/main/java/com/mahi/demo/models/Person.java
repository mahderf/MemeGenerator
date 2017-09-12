package com.mahi.demo.models;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER_DATA")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @Column(name="email",nullable = false,unique = true)
    @Email
    private String email;
    @NotEmpty
    @Column(name="first_name",nullable = false)
    private String firstName;
    @NotEmpty
    @Column(name="password",nullable = false)
    private String password;
    @NotEmpty
    @Column(name="last_name", nullable = false)
    private  String lastName;

    @Column(name="enabled")
    private boolean enabled;
    @NotEmpty
    @Column(name="username",unique = true, nullable = false)
    private String username;
//    @NotEmpty
//    @Column(nullable = false)
//    private String roleselect;


    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"))
    private Collection<PersonRole> personRoles;

    @OneToMany(cascade = CascadeType.ALL)
    //Include this to join a column to the memes table,otherwise an (unnecessary) join table will be created.
    @JoinColumn(name="person_id")
    private Set<Memes> memelist;
    public Person(){

        this.personRoles =new HashSet<PersonRole>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<PersonRole> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(Collection<PersonRole> personRoles) {
        this.personRoles = personRoles;
    }

    public Set<Memes> getMemelist() {
        return memelist;
    }

    public void setMemelist(Set<Memes> memelist) {
        this.memelist = memelist;
    }

    public void addRole(PersonRole rl)
    {

        personRoles.add(rl);
    }

    public void addMemes(Memes me)
    {
        this.memelist.add(me);
    }
}

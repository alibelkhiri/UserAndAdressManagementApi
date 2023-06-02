package com.abscript.brightcodingspringv2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;



@Entity(name="groups")
public class GroupeEntity implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name",length=30)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "groups_users",joinColumns = {@JoinColumn(name="groups_id")},inverseJoinColumns = {@JoinColumn(name="user_id")})
    private Set<UserEntity> users =new HashSet<>();
    
}

package com.example.a1084081.oriondemoapp.model;

import java.io.Serializable;

/**
 * Created by 1084081 on 5/1/17.
 */

public class User implements Serializable{

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

public Integer getId() {
        return id;
        }

public String getName() {
        return name;
        }

public String getUsername() {
        return username;
        }

public String getEmail() {
        return email;
        }

public Address getAddress() {
        return address;
        }

public String getPhone() {
        return phone;
        }

public String getWebsite() {
        return website;
        }

public Company getCompany() {
        return company;
        }




public void setId(Integer id) {
        this.id = id;
        }

public void setName(String name) {
        this.name = name;
        }

public void setUsername(String username) {
        this.username = username;
        }

public void setEmail(String email) {
        this.email = email;
        }

public void setAddress(Address address) {
        this.address = address;
        }

public void setPhone(String phone) {
        this.phone = phone;
        }

public void setWebsite(String website) {
        this.website = website;
        }

public void setCompany(Company company) {
        this.company = company;
        }
}

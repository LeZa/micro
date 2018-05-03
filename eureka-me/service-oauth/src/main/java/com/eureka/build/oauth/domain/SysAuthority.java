package com.eureka.build.oauth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public  class SysAuthority implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;
}

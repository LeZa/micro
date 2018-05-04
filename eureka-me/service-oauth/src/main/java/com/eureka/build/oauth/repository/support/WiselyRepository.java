
package com.eureka.build.oauth.repository.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;



@NoRepositoryBean
public interface WiselyRepository<E, PK extends Serializable> extends JpaRepository<E, PK> {

}

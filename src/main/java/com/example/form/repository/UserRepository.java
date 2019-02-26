package com.example.form.repository;

import com.example.form.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByUsername(User username);

    /**
     * Normal bir Java uygulamasında, yukarıdaki methodun implementasyonunu yapmamız gerekirdi.
     * Spring Data JPA sayesinde bunu yapmamıza gerek kalmıyor, o bizim için bu implementasyonu,
     * method ismine göre kendisi yaratıyor
     */
}

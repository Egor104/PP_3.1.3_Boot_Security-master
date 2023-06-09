package ru.kata.spring.boot_security.demo.services.userDetails;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
// UserDetailsService — это интерфейс который используется для того, чтоб получить данные юзера.
// UserDetailsService, используется чтобы создать UserDetails объект путем реализации
// единственного метода этого интерфейса loadUserByUsername(String username)

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        try {
            Hibernate.initialize(user.getRoles());
        } catch (HibernateException ex) {
            System.out.println("Ошибка инициализации ролей");
        }
        return user;
    }
}

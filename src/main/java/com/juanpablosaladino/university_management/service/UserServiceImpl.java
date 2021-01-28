package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Role;
import com.juanpablosaladino.university_management.model.User;
import com.juanpablosaladino.university_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) throws Exception {
        if (checkUserEmailIsAvaible(user) && checkPasswordIsValid(user)) {
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User does not exist"));
        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {
        User foundUser = getUserById(user.getId());
        mapUser(user, foundUser);
        return userRepository.save(foundUser);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        User userFound = getUserById(id);
        userRepository.delete(userFound);
    }

    private boolean checkUserEmailIsAvaible(User user) throws Exception {
        Optional<User> userFound = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (userFound.isPresent()) {
            throw new Exception("Email in use");
        }
        return true;
    }

    private boolean checkPasswordIsValid(User user) throws Exception {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new Exception("Passwords are diferents");
        }
        return true;
    }

    protected void mapUser(User from, User to) {
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }

/*    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }*/
}

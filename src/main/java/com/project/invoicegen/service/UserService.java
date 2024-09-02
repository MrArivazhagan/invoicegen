package com.project.invoicegen.service;

import com.project.invoicegen.model.User;
import com.project.invoicegen.repository.CameraFormRepository;
import com.project.invoicegen.repository.ReverseSensorFormRepository;
import com.project.invoicegen.repository.UserRepository;
import com.project.invoicegen.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final FormRepository formRepository;
    private final CameraFormRepository cameraFormRepository;

    private final ReverseSensorFormRepository reverseSensorFormRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, CameraFormRepository cameraFormRepository,
                       ReverseSensorFormRepository reverseSensorFormRepository,
                       PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
//        this.formRepository = formRepository;
        this.cameraFormRepository = cameraFormRepository;
        this.reverseSensorFormRepository = reverseSensorFormRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String createToken(String username, long userId) {
        System.out.println(username + " create token");
        return jwtUtil.generateToken(username, userId);
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println(user.getUsername() + " userservice");
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public long getIdByUsername(String username)
    {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getId();
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Delete all forms associated with the user
//        formRepository.deleteAll(user.getForms());
        cameraFormRepository.deleteAll(user.getCameraForms());
        reverseSensorFormRepository.deleteAll(user.getReverseSensorForms());
        // Delete the user
        userRepository.delete(user);
    }
}

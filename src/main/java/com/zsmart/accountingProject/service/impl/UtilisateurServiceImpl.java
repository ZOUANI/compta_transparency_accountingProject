package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.dao.UtilisateurDao;
import com.zsmart.accountingProject.service.facade.RoleService;
import com.zsmart.accountingProject.service.facade.UtilisateurService;
import com.zsmart.accountingProject.service.security.JwtUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.Util;
import com.zsmart.accountingProject.ws.rest.converter.RoleConverter;
import com.zsmart.accountingProject.ws.rest.vo.AuthResponse;
import com.zsmart.accountingProject.ws.rest.vo.RoleVo;
import com.zsmart.accountingProject.ws.rest.vo.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleConverter roleConverter;


    @Override
    public Utilisateur save(Utilisateur utilisateur) {

        Utilisateur res = utilisateurDao.findByEmail(utilisateur.getEmail()).orElse(null);
        if (res == null) {
            utilisateurDao.save(utilisateur);
            return utilisateur;
        } else {
            return res;
        }
    }

    @Override
    public Utilisateur findById(Long id) {
        try {
            return utilisateurDao.getOne(id);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public int saveWithRoles(Utilisateur user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
                || user.getPassword().isEmpty()) {
            return -1;
        }
        UserDetails loadedUser = loadUserByUsername(user.getUsername());
        if (loadedUser != null) {
            return -2;
        } else if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            user.getAuthorities().forEach(r -> {
                Role myRole = roleService.save(r);
                r.setId(myRole.getId());
            });
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        utilisateurDao.save(user);

        return 0;
    }

    @Override
    public AuthResponse authentificate(String login, String pass) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, pass));
        } catch (Exception e) {
            return null;
        }
        Utilisateur userDetails = (Utilisateur) loadUserByUsername(login);
        int societe = 0;
        if (Util.instanceOf(userDetails, Adherant.class)) {
            societe = ((Adherant) utilisateurDao.findByEmail(userDetails.getEmail()).orElseThrow()).getSocietes().size();
        } else if (Util.instanceOf(userDetails, Comptable.class)) {
            societe = ((Comptable) utilisateurDao.findByEmail(userDetails.getEmail()).orElseThrow()).getSocietes().size();

        }
        List<RoleVo> roles = roleConverter.toVo(new ArrayList<>(userDetails.getAuthorities()));
        return new AuthResponse(JwtUtil.generateToken(userDetails),
                NumberUtil.toString(userDetails.getId()), userDetails.getNom(), userDetails.getEmail(), roles, societe);
    }

    @Override
    public int signUp(SignupRequest user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null
                || user.getPassword().isEmpty()) {
            return -1;
        }
        Utilisateur loadedUser = utilisateurDao.findByEmail(user.getEmail()).orElse(null);
        if (loadedUser != null) {
            return -2;
        } else if (user.getRole() != null && !user.getRole().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            for (String role : user.getRole()
            ) {
                if (role.equals("COMPTABLE")) {
                    Role roleComptable = roleService.findByName("ROLE_COMPTABLE");
                    roles.add(roleComptable);
                    Comptable comptable = new Comptable();
                    comptable.setNom(user.getFirstName());
                    comptable.setEmail(user.getEmail());
                    comptable.setPrenom(user.getLastName());
                    comptable.setPassword(passwordEncoder.encode(user.getPassword()));
                    comptable.setAuthorities(roles);

                    utilisateurDao.save(comptable);
                    return 0;
                }
            }

        }
        List<Role> roles = new ArrayList<>();
        Role adherentRole = roleService.findByName("ROLE_ADHERENT");
        roles.add(adherentRole);
        Adherant adherant = new Adherant();
        adherant.setNom(user.getFirstName());
        adherant.setEmail(user.getEmail());
        adherant.setPrenom(user.getLastName());
        adherant.setPassword(passwordEncoder.encode(user.getPassword()));
        adherant.setAuthorities(roles);
        utilisateurDao.save(adherant);
        return 0;
    }

    @Override
    public int adminsignUp(SignupRequest user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null
                || user.getPassword().isEmpty()) {
            return -1;
        }
        Utilisateur loadedUser = utilisateurDao.findByEmail(user.getEmail()).orElse(null);
        if (loadedUser != null) {
            return -2;
        } else {
            List<Role> roles = new ArrayList<>();
            Role roleComptable = roleService.findByName("ROLE_ADMIN");
            roles.add(roleComptable);
            Admin admin = new Admin();
            admin.setNom(user.getFirstName());
            admin.setEmail(user.getEmail());
            admin.setPrenom(user.getLastName());
            admin.setPassword(passwordEncoder.encode(user.getPassword()));
            admin.setAuthorities(roles);

            utilisateurDao.save(admin);
            return 0;
        }
    }

    @Override
    public int delete(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return -1;

        } else {
            utilisateurDao.delete(utilisateur);
            return 1;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        return utilisateurDao.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + s));
    }
}

/**
 * @author: ntwari egide
 * @description: user service interface
 */

package com.kalpix.downloader.backend.services;

import com.kalpix.downloader.backend.dtos.UserDto;
import com.kalpix.downloader.backend.models.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.management.relation.RoleNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
public interface UserServices {

    List<User> findAll();

    User findById(UUID id);

    User signupUser(UserDto signupUser) throws RoleNotFoundException, MessagingException, UnsupportedEncodingException;

    User updateUser(UserDto updateUser, UUID id) throws RoleNotFoundException;

    User deleteUser(UUID id);


}

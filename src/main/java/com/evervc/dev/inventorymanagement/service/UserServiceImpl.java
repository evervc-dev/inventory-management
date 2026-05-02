package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.mapper.UserMapper;
import com.evervc.dev.inventorymanagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    @Override
    public BaseResponseDto findAll(Pageable pageable) {
        Page<User> users = userRepository.findByEnabledTrue(pageable);

        Page<UserResponseDto> userPage = users.map(UserMapper::toDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                userPage
        );
    }

    @Transactional
    @Override
    public void remove(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("El usuario con ID [" + id + "] no existe.")
        );
        user.setEnabled(false);
        userRepository.delete(user);
    }
}

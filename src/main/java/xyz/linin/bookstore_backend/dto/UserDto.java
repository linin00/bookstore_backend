package xyz.linin.bookstore_backend.dto;

import lombok.Data;
import xyz.linin.bookstore_backend.constants.Role;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    private Role role;
}

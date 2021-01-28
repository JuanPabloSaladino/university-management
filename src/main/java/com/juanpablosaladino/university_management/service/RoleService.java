package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Role;

public interface RoleService {

    Iterable<Role> getRoles();

    Role getRoleByName(String name);

}

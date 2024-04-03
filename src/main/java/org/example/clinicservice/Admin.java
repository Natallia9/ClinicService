package org.example.clinicservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clinicservice.entity.enums.AdminRole;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private UUID adminId;

    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;

    @OneToMany(mappedBy = "admins")
    private List<User> admins;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) && adminRole == admin.adminRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminRole);
    }
}

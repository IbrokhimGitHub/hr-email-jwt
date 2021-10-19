package uz.pdp.hremailjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee implements UserDetails {
    public Employee(@Size(min = 3, max = 50) String firstName, @Size(min = 3, max = 50) String lastName, @Email String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }

    @Id
    @GeneratedValue
    private UUID uuid;

    @Size(min = 3,max = 50)
    @Column(nullable = false,length = 50)
    private String firstName;

    @Size(min = 3,max = 50)
    @Column(nullable = false,length = 50)
    private String lastName;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    @ManyToMany
    private Set<Role> roles;

    private String emailCode;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true; //bu user blocklanmagan
    private boolean credentialsNonExpired = true; //bu user sroki o'tmagan
    private boolean enabled; //bu user yoniqmi

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

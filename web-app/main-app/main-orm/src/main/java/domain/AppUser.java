package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AppUser extends BaseEntity {

    @Lob
    private byte[] avatar;

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    @Column(length = 254, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 20)
    private String username;

    @Column(name = "password_hash", length = 60)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority")})
    @Fetch(FetchMode.SUBSELECT)
    private Set<Authority> authorities = new HashSet<>();


    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean accountNonExpired;

    @Column(nullable = false)
    private Boolean accountNonLocked;

    @Column(nullable = false)
    private Boolean credentialNonExpired;

    private String activationKey;

    private String resetKey;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername());
    }
}

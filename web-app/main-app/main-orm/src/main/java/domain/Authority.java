package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Authority{

    @Id
    @Column(length = 16)
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority oAuthority = (Authority) o;
        return oAuthority.authority.equals(this.authority);
    }


    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}

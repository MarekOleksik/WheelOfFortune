package pl.marekjava;

import javax.persistence.*;

@Entity
public class Passwords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String password;

    public Passwords() {
    }

    public Passwords(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return password;
    }
}

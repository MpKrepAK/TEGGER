package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.marketplace.server.model.enums.EGender;
import org.marketplace.server.model.enums.EPermission;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String login;
    private String password;

    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Set<Product> productsForSale = new HashSet<>();

    @ElementCollection(targetClass = EPermission.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "permission")
    private Set<EPermission> permissions = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Complaint> complaints = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "user_genders", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "gender")
    private EGender gender;

}

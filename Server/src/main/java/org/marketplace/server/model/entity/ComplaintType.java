package org.marketplace.server.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "complaint_types")
@Getter
@Setter
@NoArgsConstructor
public class ComplaintType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Complaint> complaints = new HashSet<>();

    public ComplaintType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

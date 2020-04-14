package com.conatuseus.oppalol.domain.member;

import com.conatuseus.oppalol.domain.base.BaseEntity;
import com.conatuseus.oppalol.domain.summonerregistration.SummonerRegistration;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SummonerRegistration> summonerRegistrations = new ArrayList<>();

    @Builder
    public Member(final String email, final String name, final String password, final Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}

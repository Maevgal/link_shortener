package com.maevgal.link_shortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "links")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Link {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String link;
    @Column(unique = true)
    private String shortLink;
    private int count;
}

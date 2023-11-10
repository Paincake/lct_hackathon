package com.example.lct_hackathon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="business_point")
@Data
public class BusinessPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cards_and_materials_delivered")
    private boolean cardsAndMaterialsDelivered;

    @Column(name = "added_yesterday")
    private boolean addedYesterday;

    @Column(name = "days_since_card_release")
    private int daysSinceCardRelease;

    @Column(name = "approved_requests")
    private int approvedRequests;

    @Column(name = "released_cards")
    private int releasedCards;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    
}

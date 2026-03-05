package com.csc340.local_harvest_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produce_boxes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduceBox {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boxId;

  @ManyToOne
  @JoinColumn(name = "farm_id", nullable = false)
  @JsonIgnoreProperties("produceBoxes")
  private Farm farm;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Season season;

  @Column(columnDefinition = "TEXT")
  private String produce;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false)
  private Integer capacity;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BoxStatus status;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "produceBox", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties({ "produceBox", "customer.subscriptions" })
  private List<Subscription> subscriptions;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

  public enum BoxStatus {
    PUBLISHED, ACTIVE, INACTIVE, ARCHIVED
  }

 public  enum Season {
    SPRING, SUMMER, FALL, WINTER
  }
}

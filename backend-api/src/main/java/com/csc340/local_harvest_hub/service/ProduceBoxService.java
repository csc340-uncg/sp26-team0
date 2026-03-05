package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.ProduceBox;
import com.csc340.local_harvest_hub.entity.ProduceBox.BoxStatus;
import com.csc340.local_harvest_hub.entity.ProduceBox.Season;
import com.csc340.local_harvest_hub.repository.ProduceBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

@Service
public class ProduceBoxService {

    @Autowired
    private ProduceBoxRepository produceBoxRepository;

    public ProduceBox createProduceBox(ProduceBox produceBox) {
        return produceBoxRepository.save(produceBox);
    }

    public Optional<ProduceBox> getProduceBoxById(Long id) {
        return produceBoxRepository.findById(id);
    }

    public List<ProduceBox> getAllProduceBoxes() {
        return produceBoxRepository.findAll();
    }

    public List<ProduceBox> getProduceBoxesByFarmId(Long farmId) {
        return produceBoxRepository.findByFarmId(farmId);
    }

    public List<ProduceBox> getProduceBoxesByStatus(String status) {
        return produceBoxRepository.findByStatus(status);
    }

    public ProduceBox updateProduceBox(Long id, ProduceBox produceBoxDetails) {
        return produceBoxRepository.findById(id).map(box -> {
            box.setTitle(produceBoxDetails.getTitle());
            box.setDescription(produceBoxDetails.getDescription());
            box.setSeason(produceBoxDetails.getSeason());
            box.setProduce(produceBoxDetails.getProduce());
            box.setPrice(produceBoxDetails.getPrice());
            box.setCapacity(produceBoxDetails.getCapacity());
            box.setStatus(produceBoxDetails.getStatus());
            return produceBoxRepository.save(box);
        }).orElseThrow(() -> new RuntimeException("ProduceBox not found"));
    }

    public void deleteProduceBox(Long id) {
        produceBoxRepository.deleteById(id);
    }

    public List<ProduceBox> filterProduceBoxes(BoxStatus status, Season season, BigDecimal maxPrice) {
        Specification<ProduceBox> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (season != null) {
                predicates.add(criteriaBuilder.equal(root.get("season"), season));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return produceBoxRepository.findAll(spec);
    }
}

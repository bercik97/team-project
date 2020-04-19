package com.letswork.api.app.category.domain;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.shared.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<AdvertisementEntity> advertisements;
}

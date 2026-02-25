package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {}
package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c ORDER BY c.position")
    public List<Category> findAllByOrderByName();
}

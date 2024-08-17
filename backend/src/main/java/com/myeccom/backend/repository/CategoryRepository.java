package com.myeccom.backend.repository;

import com.myeccom.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Category findByName(String name);


    @Query("select c from Category c "+
            " WHERE c.name=:name AND c.parentCategory.name=:parentCategory")
    public Category findByNameAndParentCategory(@Param("name") String name, @Param("parentCategory") String parentCategory);
}

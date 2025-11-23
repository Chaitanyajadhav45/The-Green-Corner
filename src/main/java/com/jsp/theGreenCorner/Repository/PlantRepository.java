package com.jsp.theGreenCorner.Repository;

import com.jsp.theGreenCorner.Entity.Plant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
	
	@Query("select p from Plant p where p.rating >=:low and p.rating <=:high")
	public List<Plant> findByRating (double low,double high);
	
	@Query("select p from Plant p where p.rating =:rating")
	public List<Plant> findByRating5 (double rating);
}

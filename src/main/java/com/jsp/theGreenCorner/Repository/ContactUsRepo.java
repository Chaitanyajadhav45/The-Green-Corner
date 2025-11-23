package com.jsp.theGreenCorner.Repository;

import com.jsp.theGreenCorner.Entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Long> {
}

package bg.fmi.uni.inventorysystem.repository;

import bg.fmi.uni.inventorysystem.model.ClubMember;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Integer> {
    // JpaRepository provides basic CRUD methods
}

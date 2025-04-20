package bg.fmi.uni.inventorysystem.service;

import bg.fmi.uni.inventorysystem.dto.ClubMemberDto;
import bg.fmi.uni.inventorysystem.model.ClubMember;
import bg.fmi.uni.inventorysystem.repository.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;

    @Autowired
    public ClubMemberService(ClubMemberRepository clubMemberRepository) {
        this.clubMemberRepository = clubMemberRepository;
    }

    public List<ClubMemberDto> getAllMembers() {
        return clubMemberRepository.findAll().stream()
                .map(ClubMemberDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ClubMemberDto> getMemberById(Integer id) {
        return clubMemberRepository.findById(id).map(ClubMemberDto::fromEntity);
    }

    public ClubMemberDto createMember(ClubMemberDto dto) {
        ClubMember member = ClubMember.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phone())
                .build();
        ClubMember saved = clubMemberRepository.save(member);
        return ClubMemberDto.fromEntity(saved);
    }

    public Optional<ClubMemberDto> updateMember(Integer id, ClubMemberDto dto) {
        Optional<ClubMember> opt = clubMemberRepository.findById(id);
        if (opt.isPresent()) {
            ClubMember member = opt.get();
            member.setFirstName(dto.firstName());
member.setLastName(dto.lastName());
            member.setEmail(dto.email());
            ClubMember updated = clubMemberRepository.save(member);
            return Optional.of(ClubMemberDto.fromEntity(updated));
        }
        return Optional.empty();
    }

    public boolean deleteMember(Integer id) {
        if (clubMemberRepository.existsById(id)) {
            clubMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

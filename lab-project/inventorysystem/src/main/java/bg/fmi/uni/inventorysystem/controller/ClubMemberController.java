package bg.fmi.uni.inventorysystem.controller;

import bg.fmi.uni.inventorysystem.dto.ClubMemberDto;
import bg.fmi.uni.inventorysystem.service.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/club-members")
public class ClubMemberController {
    private final ClubMemberService clubMemberService;

    @Autowired
    public ClubMemberController(ClubMemberService clubMemberService) {
        this.clubMemberService = clubMemberService;
    }

    @GetMapping
    public List<ClubMemberDto> getAllMembers() {
        return clubMemberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMemberDto> getMemberById(@PathVariable Integer id) {
        return clubMemberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClubMemberDto> createMember(@RequestBody ClubMemberDto dto) {
        ClubMemberDto created = clubMemberService.createMember(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubMemberDto> updateMember(@PathVariable Integer id, @RequestBody ClubMemberDto dto) {
        return clubMemberService.updateMember(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        if (clubMemberService.deleteMember(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

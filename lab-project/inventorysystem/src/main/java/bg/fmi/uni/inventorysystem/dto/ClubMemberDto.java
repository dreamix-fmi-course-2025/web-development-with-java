package bg.fmi.uni.inventorysystem.dto;

import bg.fmi.uni.inventorysystem.model.ClubMember;

public record ClubMemberDto(
    Integer id,
    String name,
    String email,
    String phone
) {
    public static ClubMemberDto fromEntity(ClubMember member) {
        String fullName = member.getFirstName() + " " + member.getLastName();
        return new ClubMemberDto(
            member.getId(),
            fullName,
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}

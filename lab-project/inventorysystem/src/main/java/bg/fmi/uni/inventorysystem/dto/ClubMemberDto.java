package bg.fmi.uni.inventorysystem.dto;

import bg.fmi.uni.inventorysystem.model.ClubMember;

public record ClubMemberDto(
    Integer id,
    String firstName,
    String lastName,
    String email,
    String phone
) {

    public static ClubMemberDto fromEntity(ClubMember member) {
        return new ClubMemberDto(
            member.getId(),
            member.getFirstName(),
            member.getLastName(),
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}

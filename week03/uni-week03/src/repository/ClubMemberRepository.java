package repository;

import model.ClubMember;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClubMemberRepository {
    private static Map<Integer, ClubMember> memberTable = new HashMap<>();

    public void addMember(ClubMember member) {
        memberTable.put(member.getId(), member);
    }

    public Optional<ClubMember> getMemberById(Integer id) {
        return Optional.ofNullable(memberTable.get(id));
    }
}

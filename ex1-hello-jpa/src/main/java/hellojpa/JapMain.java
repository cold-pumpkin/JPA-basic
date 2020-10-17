package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.List;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 트랜잭션 마다 생성하기

        EntityTransaction tx = em.getTransaction();  // 트랜잭션 받아오기
        tx.begin();

        try {
            // 팀 생성
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 멤버 생성
            Member member = new Member();
            member.setUsername("chanho");

            // 팀에 멤버 속하게 하기
            member.changeTeam(team);  // 연관관계 편의 메소드 추가 : team에도 member 추가
            em.persist(member);

            em.flush();
            em.clear();

            // 멤버의 팀 조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam name = " + findTeam.getName());

            tx.commit();  // flush - commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

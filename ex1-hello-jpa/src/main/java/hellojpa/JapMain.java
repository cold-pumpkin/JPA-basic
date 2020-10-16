package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            tx.commit();  // flush - commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

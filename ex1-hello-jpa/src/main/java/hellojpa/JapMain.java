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
            Member member = em.find(Member.class, 10L);
            member.setName("Philip Park");  // 영속 상태 - dirty checking 가능

            em.detach(member);  // 준영속 상태  -> commit해도 update 동작 안함

            System.out.println("========================");

            tx.commit();  // flush - commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

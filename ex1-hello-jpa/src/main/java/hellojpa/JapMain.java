package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // ** 회원 등록 ** //
        EntityManager em = emf.createEntityManager(); // 트랜잭션 마다 생성하기

        EntityTransaction tx = em.getTransaction();  // 트랜잭션 받아오기
        tx.begin();

        try {
            Member member = new Member();
            member.setId(2L);
            member.setName("Chulsoo");

            em.persist(member); // save

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

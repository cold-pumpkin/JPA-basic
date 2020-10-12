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
            Member findMember = em.find(Member.class, 2L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class) // JPQL
                                    .setFirstResult(1)
                                    .setMaxResults(10)
                                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

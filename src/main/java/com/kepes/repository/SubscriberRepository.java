package com.kepes.repository;

import com.kepes.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//public interface SubscriberRepository extends CrudRepository<Subscription, Long> {
@Repository
public interface SubscriberRepository extends JpaRepository<Subscription, Long> {
    /**
     * findBy: Tells Spring to look up something in the database.
     * UserId: Tells Spring to traverse the 'user' field in Subscriber, and then access its idUser field.
     */
    List<Subscription> findByUserIdUser(String userId);
    List<Subscription> findByEventIdEvent(Long eventId);
    Optional<Subscription> findByUserIdUserAndEventIdEvent(String userId, Long eventId);
}

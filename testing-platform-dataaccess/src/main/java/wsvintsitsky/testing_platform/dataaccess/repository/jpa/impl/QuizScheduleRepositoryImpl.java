package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.QuizScheduleRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

@SuppressWarnings("unused")
@Repository
public class QuizScheduleRepositoryImpl extends AbstractDaoImpl<QuizSchedule, Long> implements QuizScheduleRepository {

	protected QuizScheduleRepositoryImpl() {
		super(QuizSchedule.class);
	}

	@Override
	public List<QuizSchedule> findAvailableSchedulesByUserProfile(UserProfile userProfile) {
		EntityManager entityManager = getEntityManager();
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<QuizSchedule> query = cb.createQuery(QuizSchedule.class).distinct(true);
//		Root<QuizSchedule> quizScheduleRoot = query.from(QuizSchedule.class);
//
//		quizScheduleRoot.fetch(QuizSchedule_.quiz, JoinType.LEFT);
//		quizScheduleRoot.fetch(QuizSchedule_.pupilGroup, JoinType.LEFT);
//		
//		Join<QuizSchedule, PupilGroup> quizScheduleToPupilGroup = quizScheduleRoot.join(QuizSchedule_.pupilGroup,
//				JoinType.LEFT);
//		
//		Join<PupilGroup, UserProfile> pupilGroupToUserProfile = quizScheduleToPupilGroup.join(PupilGroup_.userProfiles,
//				JoinType.LEFT);
//
//		List<Predicate> predicates = new ArrayList<Predicate>();
//
		
//		predicates.add(cb.greaterThan(quizScheduleRoot.get(QuizSchedule_.availableTo), calendar));
//		predicates.add(cb.lessThan(quizScheduleRoot.get(QuizSchedule_.availableFrom), calendar));
//
//		predicates.add(cb.equal(pupilGroupToUserProfile.get(UserProfile_.id), userProfile.getId()));
//
//		Subquery<Long> subQuery = query.subquery(Long.class);
//		Root<Result> resultRoot = subQuery.from(Result.class);
//		subQuery.select(resultRoot.get(Result_.quiz).get(Quiz_.id))
//				.where(cb.equal(resultRoot.get(Result_.userProfile).get(UserProfile_.id), userProfile.getId()));
//		Predicate notCompletedPredicate = cb.not(cb.in(quizScheduleRoot.get(QuizSchedule_.quiz).get(Quiz_.id)).value(subQuery));
//		predicates.add(notCompletedPredicate);
//
//		query.select(quizScheduleRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//
//		TypedQuery<QuizSchedule> q = entityManager.createQuery(query);
		
		
//		return q.getResultList();
		Query namedQuery = entityManager.createNamedQuery("QuizSchedule.findAvailableForProfile");
		namedQuery.setParameter("currentDate", calendar);
		namedQuery.setParameter("userProfileId", userProfile.getId());
		@SuppressWarnings("unchecked")
		List<QuizSchedule> quizSchedules = namedQuery.getResultList();
		return quizSchedules;
	}

}

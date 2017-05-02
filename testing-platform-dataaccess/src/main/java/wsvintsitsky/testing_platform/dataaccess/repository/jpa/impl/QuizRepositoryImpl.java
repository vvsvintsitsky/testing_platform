package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.QuizRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

@Repository
public class QuizRepositoryImpl extends AbstractDaoImpl<Quiz, Long> implements QuizRepository {

	protected QuizRepositoryImpl() {
		super(Quiz.class);
	}

	@Override
	public List<Quiz> findUncompletedQuizzesForUser(UserProfile userProfile) {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Quiz> query = cb.createQuery(Quiz.class).distinct(true);
		Root<Quiz> quizRoot = query.from(Quiz.class);

		Join<Quiz, QuizSchedule> quizToQuizSchedule = quizRoot.join(Quiz_.quizSchedules, JoinType.LEFT);

		Join<QuizSchedule, PupilGroup> quizScheduleToPupilGroup = quizToQuizSchedule.join(QuizSchedule_.pupilGroup,
				JoinType.LEFT);

		Join<PupilGroup, UserProfile> pupilGroupToUserProfile = quizScheduleToPupilGroup.join(PupilGroup_.userProfiles,
				JoinType.LEFT);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		predicates.add(cb.greaterThan(quizToQuizSchedule.get(QuizSchedule_.availableTo), calendar));
		predicates.add(cb.lessThan(quizToQuizSchedule.get(QuizSchedule_.availableFrom), calendar));

		predicates.add(cb.equal(pupilGroupToUserProfile.get(UserProfile_.id), userProfile.getId()));

		Subquery<Long> subQuery = query.subquery(Long.class);
		Root<Result> resultRoot = subQuery.from(Result.class);
		subQuery.select(resultRoot.get(Result_.quiz).get(Quiz_.id))
				.where(cb.equal(resultRoot.get(Result_.userProfile).get(UserProfile_.id), userProfile.getId()));
		Predicate notCompletedPredicate = cb.not(cb.in(quizRoot.get(Quiz_.id)).value(subQuery));
		predicates.add(notCompletedPredicate);

		query.select(quizRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		TypedQuery<Quiz> q = entityManager.createQuery(query);
		return q.getResultList();
	}

}

package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz_;

public class QuestionFilter extends AbstractFilter<Question> {

	private static final long serialVersionUID = 1L;

	private boolean isFetchAnswers;
	private Quiz quiz;
	private Long questionId;
	
	public boolean isFetchAnswers() {
		return isFetchAnswers;
	}
	
	public void setFetchAnswers(boolean isFetchAnswers) {
		this.isFetchAnswers = isFetchAnswers;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public void setFetching(Root<Question> from) {
		if (isFetchAnswers)
			from.fetch(Question_.answers, JoinType.LEFT);
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<Question> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (quiz != null) {
			predicateList.add(quizIdPredicate(cb, from));
		}
		if (questionId != null) {
			predicateList.add(questionIdPredicate(cb, from));
		}
		
		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<Question> query, Root<Question> from) {
		
	}
	
	private Predicate quizIdPredicate(CriteriaBuilder cb, Root<Question> from) {
		return cb.equal(from.get(Question_.quiz).get(Quiz_.id), quiz.getId());
	}
	
	private Predicate questionIdPredicate(CriteriaBuilder cb, Root<Question> from) {
		return cb.equal(from.get(Question_.id), questionId);
	}

}

package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question_;

public class AnswerFilter extends AbstractFilter<Answer> {

	private static final long serialVersionUID = 1L;
	
	private Question question;
	
	private boolean fetchQuestion;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean setFetchQuestion() {
		return fetchQuestion;
	}

	public void setFetchQuestion(boolean fetchQuestion) {
		this.fetchQuestion = fetchQuestion;
	}

	@Override
	public void setFetching(Root<Answer> from) {
		if(fetchQuestion) {
			from.fetch(Answer_.question);
		}
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<Answer> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if(question != null) {
			predicateList.add(questionIdPredicate(cb, from));
		}
		
		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<Answer> query, Root<Answer> from) {
		// TODO Auto-generated method stub
		
	}

	private Predicate questionIdPredicate(CriteriaBuilder cb, Root<Answer> from) {
		return cb.equal(from.get(Answer_.question).get(Question_.id), question.getId());
	}
	
}

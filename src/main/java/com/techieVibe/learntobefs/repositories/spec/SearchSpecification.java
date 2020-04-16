package com.techieVibe.learntobefs.repositories.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922669161712580128L;

	private SearchCriteria searchCriteria;

	private List<SearchCriteria> list;

	public SearchSpecification() {
		this.list = new ArrayList<>();
	}

	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}

	public SearchSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		System.out.println("search spec predicate called >>>");
		list.forEach(i -> {
			System.out.println("list data >> : " + i);
		});
		// create a new predicate list
		List<Predicate> predicates = new ArrayList<>();

		// add add criteria to predicates
		for (SearchCriteria criteria : list) {
			query.distinct(true);
			if (criteria.getOperator().equals(SearchOperator.GREATER_THAN)) {
				predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperator().equals(SearchOperator.LESS_THAN)) {
				predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperator().equals(SearchOperator.GREATER_THAN_EQUAL)) {
				predicates
						.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperator().equals(SearchOperator.LESS_THAN_EQUAL)) {
				predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperator().equals(SearchOperator.NOT_EQUAL)) {
				predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperator().equals(SearchOperator.EQUAL)) {
				predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperator().equals(SearchOperator.MATCH)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase() + "%"));
			} else if (criteria.getOperator().equals(SearchOperator.MATCH_END)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						criteria.getValue().toString().toLowerCase() + "%"));
			} else if (criteria.getOperator().equals(SearchOperator.MATCH_START)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase()));
			} else if (criteria.getOperator().equals(SearchOperator.IN)) {
				predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
			} else if (criteria.getOperator().equals(SearchOperator.NOT_IN)) {
				predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
			}
		}

		return builder.and(predicates.toArray(new Predicate[0]));
	}

}

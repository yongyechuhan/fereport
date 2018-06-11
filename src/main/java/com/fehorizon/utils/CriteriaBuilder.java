package com.fehorizon.utils;

import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

public class CriteriaBuilder {

	private Criteria criteria;

	private Example example;

	private CriteriaBuilder(Class<?> clz) {
		this.example = new Example(clz);
		Criteria criteria = example.createCriteria();
		this.criteria = criteria;
	}

	public static CriteriaBuilder instance(Class<?> clz) {
		return new CriteriaBuilder(clz);
	}

	public Criteria getCriteria() {
		return this.criteria;
	}

	public Example getExample() {
		return this.example;
	}

	public CriteriaBuilder addCondition(String condition) {
		if (isNotEmptyString(condition)) {
			criteria.andCondition(condition);
		}
		return this;
	}

	public CriteriaBuilder addEq(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andEqualTo(property, value);
		}
		return this;
	}
	public CriteriaBuilder addNoEq(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andNotEqualTo(property, value);
		}
		return this;
	}

	public CriteriaBuilder addGt(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andGreaterThan(property, value);
		}
		return this;
	}

	public CriteriaBuilder addGe(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andGreaterThanOrEqualTo(property, value);
		}
		return this;
	}

	public CriteriaBuilder addLt(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andLessThan(property, value);
		}
		return this;
	}

	public CriteriaBuilder addLe(String property, Object value) {
		if (isNotEmptyString(value)) {
			criteria.andLessThanOrEqualTo(property, value);
		}
		return this;
	}

	public CriteriaBuilder addBetween(String property, Object from, Object to) {
		if (null != from && null != to) {
			criteria.andBetween(property, from, to);
		}
		if (null != from && null == to) {
			addGt(property, from);
		}
		if (null == from && null != to) {
			addLe(property, to);
		}
		return this;
	}

	public CriteriaBuilder addIn(String property, List<Object> values) {
		if (CollectionUtils.isNotEmpty(values)) {
			criteria.andIn(property, values);
		}
		return this;
	}

	public CriteriaBuilder addIsNull(String property) {
		this.criteria.andIsNull(property);
		return this;
	}

	public CriteriaBuilder addIsNotNull(String property) {
		this.criteria.andIsNotNull(property);
		return this;
	}

	public CriteriaBuilder addLikeAny(String property, String value) {
		if (StringUtils.isNotBlank(value)) {
			criteria.andLike(property, "%" + value + "%");
		}
		return this;
	}

	public CriteriaBuilder addLikeStart(String property, String value) {
		if (StringUtils.isNotBlank(value)) {
			criteria.andLike(property, value + "%");
		}
		return this;
	}

	public CriteriaBuilder addLikeEnd(String property, String value) {
		if (StringUtils.isNotBlank(value)) {
			criteria.andLike(property, "%" + value);
		}
		return this;
	}

	public CriteriaBuilder withPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return this;
	}

	// public CriteriaBuilder addOr(String property, Object lValue, Object
	// rValue) {
	// criteria.andCondition("(" + property + "='" + lValue + "' or " + property
	// + "='" + rValue + "')");
	//
	// return this;
	// }
	//
	// public CriteriaBuilder addOr(String propertyOne, Object valueOne, String
	// propertyTwo, Object valueTwo) {
	//
	// criteria.andCondition(propertyOne + "=" + valueOne + " or " + propertyTwo
	// + "=" + valueTwo);
	//
	// return this;
	// }

	private boolean isNotEmptyString(Object obj) {
		return !(null == obj || (String.class.equals(obj.getClass()) && StringUtils.isBlank(String.valueOf(obj))));
	}
}

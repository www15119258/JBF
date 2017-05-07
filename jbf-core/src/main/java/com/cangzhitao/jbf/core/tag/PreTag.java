package com.cangzhitao.jbf.core.tag;

import org.htmlparser.tags.CompositeTag;

public class PreTag extends CompositeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1374057683681564722L;

	private static final String[] mIds = { "pre" };
	private static final String[] mEndTagEnders = { "head" };

	public String[] getIds() {
		return (mIds);
	}

	public String[] getEndTagEnders() {
		return (mEndTagEnders);
	}

	public String[] getEnders() {
		return super.getEnders();
	}
}
